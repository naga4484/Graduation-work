package group;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import bean.User_id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_file_downloadAction extends Action {

    private static final String FILE_DIRECTORY = "/uploads/共有用";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User_id user_id = (User_id) session.getAttribute("user");

        String group_id = request.getParameter("group_id");
        String fileName = request.getParameter("name");
        
        // セキュリティ対策: ファイル名を正規化
        fileName = fileName.replace("..", "");
        
        File file = new File(request.getServletContext().getRealPath("") + File.separator + FILE_DIRECTORY, fileName);
        if (file.exists()) {
            String mimeType = request.getServletContext().getMimeType(file.getPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            
            response.reset();
            String encodedFileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
            response.setContentLength((int) file.length());
            
            

            try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
                 OutputStream outStream = response.getOutputStream()) {
                
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                outStream.flush();
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading or writing file");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        }
        
        return "";
    }
}
