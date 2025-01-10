package teacher_function;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import bean.Submissions;
import bean.Teacheraccount;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Submissions_sended_file_downloadAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

		    int submissions_id=Integer.parseInt(request.getParameter("submissions_id"));
			String path=request.getParameter("path");
			String student_id=request.getParameter("student_id");
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			
			SubmissionsDAO dao=new SubmissionsDAO();
			Submissions sub = dao.distinctsubmissions_id(submissions_id);
			
			String file_path = sub.getSave_path() + "/" + path;
			
			File file = new File(request.getServletContext().getRealPath("") + File.separator + file_path);
			 
			 System.out.println(request.getServletContext().getRealPath("") + File.separator + file_path);
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
			return"";
		}
	}