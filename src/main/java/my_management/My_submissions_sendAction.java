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
public class My_submissions_sendAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id)session.getAttribute("user");
			Submissions submissions = (Submissions)session.getAttribute("my_submissions");
			SubmissionsDAO dao = new SubmissionsDAO();
			
			// アップロードディレクトリのパスを取得
	        String uploadPath = request.getServletContext().getRealPath("") + File.separator + submissions.getSave_path();
	        
	        try {
	            // フォームでアップロードされたファイルを処理
	            for (Part part : request.getParts()) {
	                String fileName = extractFileName(part); // ファイル名を取得
	                if (fileName != null && !fileName.isEmpty()) {
	                    String filePath = uploadPath + File.separator + fileName;
	                    try (InputStream fileContent = part.getInputStream()) {
	                        Files.copy(fileContent, new File(filePath).toPath());
	                    }
	                    int line = dao.submissions_alignment_flags(submissions.getSubmissions_id(),user_id.getStudent_id(),fileName);
	                    List<Submissions> my_submissions_list = dao.submissions_my_management(user_id.getStudent_id());
	    	        	session.setAttribute("my_submissions_list", my_submissions_list);
	                    System.out.println("最新ファイル " + fileName + " がアップロードされました: " + filePath);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
			
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