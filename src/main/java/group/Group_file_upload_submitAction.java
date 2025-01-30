package group;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import bean.Group;
import bean.User_id;
import dao.GroupDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tool.Action;

@MultipartConfig(
    maxFileSize = 104857600, // 最大ファイルサイズ: 100MB
    maxRequestSize = 524288000, // 最大リクエストサイズ: 500MB
    fileSizeThreshold = 0 // ファイルのサイズの閾値（0は即時保存）
)
public class Group_file_upload_submitAction extends Action {

    private static final String UPLOAD_DIR = "uploads/共有用";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User_id user_id = (User_id) session.getAttribute("user");
        String group_id = request.getParameter("group_id");

        GroupDAO dao = new GroupDAO();
        List<Group> file_list = dao.search_group_file(group_id);
        Group group_ob = dao.search_group_id(group_id);

        // アップロードディレクトリのパスを取得
        String uploadPath = System.getProperty("user.dir") + File.separator + UPLOAD_DIR
                + File.separator + group_ob.getGroup_name();
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 親ディレクトリも含めて作成
        }
        try {
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.isEmpty()) {
                    // ファイル名にタイムスタンプを付与
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                    String filePath = uploadPath + File.separator + uniqueFileName;

                    try (InputStream fileContent = part.getInputStream()) {
                        Files.copy(fileContent, new File(filePath).toPath());
                    }

                    dao.group_file_insert(group_id, uniqueFileName, user_id.getUser_id());
                    file_list = dao.search_group_file(group_id);
                    System.out.println("ファイル " + uniqueFileName + " がアップロードされました: " + filePath);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("エラー内容: " + e.getMessage());
            session.setAttribute("dis_err_mes", "アップロード中にエラーが発生しました");
            return "group_file_upload.jsp";
        }

        session.setAttribute("file_list", file_list);
        return "group_file_upload.jsp";
    }

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
