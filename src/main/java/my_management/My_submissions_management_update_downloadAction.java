package my_management;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class My_submissions_management_update_downloadAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id)session.getAttribute("user");
			
			Submissions submissions = (Submissions)session.getAttribute("my_send_submissions");
			SubmissionsDAO dao = new SubmissionsDAO();
			Submissions my_submissions = dao.distinctsubmissions_id(submissions.getSubmissions_id());
			
			 File file = new File(request.getServletContext().getRealPath("") + File.separator + my_submissions.getSave_path(), submissions.getSubmissions_my_name());
			 
			 System.out.println(request.getServletContext().getRealPath("") + File.separator + my_submissions.getSave_path() + submissions.getSubmissions_my_name());
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