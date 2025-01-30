package my_management;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tool.Action;

@MultipartConfig(
		maxFileSize = 104857600,  // 最大ファイルサイズ: 100MB
	    maxRequestSize = 524288000,  // 最大リクエストサイズ: 500MB
	    fileSizeThreshold = 0  // ファイルのサイズの閾値（0は即時保存）
)
public class My_submissions_send_updateAction extends Action {
	
    // タイムスタンプを付けたファイル名を生成するメソッド
    private String generateTimestampedFileName(String originalFileName) {
        // 現在のタイムスタンプを生成（yyyyMMdd_HHmmss_SSS形式）
        String timestamp = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        
        // 元のファイル名の拡張子を取り出す
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex != -1) {
            fileExtension = originalFileName.substring(dotIndex);
        }
        
        // タイムスタンプを付けた新しいファイル名を作成
        return timestamp + "_" + originalFileName.substring(0, dotIndex) + fileExtension;
    }

	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session = request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id) session.getAttribute("user");
			Submissions submissions = (Submissions) session.getAttribute("my_send_submissions");
			SubmissionsDAO dao = new SubmissionsDAO();
			Submissions my_submissions = dao.distinctsubmissions_id(submissions.getSubmissions_id());
			
			// アップロードディレクトリのパスを取得
	        String uploadPath = System.getProperty("user.dir") + File.separator + my_submissions.getSave_path();
	        
	        //既存のファイルの削除
	        File file = new File(uploadPath + "/" + submissions.getSubmissions_my_name());
	        file.delete(); 
	        
	        try {
	            // フォームでアップロードされたファイルを処理
	            for (Part part : request.getParts()) {
	                String fileName = extractFileName(part); // ファイル名を取得
	                if (fileName != null && !fileName.isEmpty()) {
	                    // タイムスタンプを追加したファイル名を生成
	                    String newFileName = generateTimestampedFileName(fileName);
	                    String filePath = uploadPath + File.separator + newFileName;
	                    
	                    try (InputStream fileContent = part.getInputStream()) {
	                        Files.copy(fileContent, new File(filePath).toPath());
	                    }
	                    
	                    // データベースに新しいファイル名を登録
	                    int line = dao.submissions_alignment_flags(submissions.getSubmissions_id(), user_id.getStudent_id(), newFileName);
	                    List<Submissions> my_submissions_list = dao.submissions_my_management(user_id.getStudent_id());
	    	        	session.setAttribute("my_submissions_list", my_submissions_list);
	                    System.out.println("最新ファイル " + newFileName + " がアップロードされました: " + filePath);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
			
	        session.removeAttribute("my_send_submissions");
	        session.removeAttribute("my_submissions");
			return "my_submissions.jsp";
	}
	
	// ヘッダーからファイル名を取得するメソッド
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("Content-Disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}
