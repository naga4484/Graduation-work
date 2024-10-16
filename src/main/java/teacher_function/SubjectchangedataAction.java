package teacher_function;

import java.util.List;

import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectchangedataAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			
			String subject_name=request.getParameter("subject_id");
			int total_unit=Integer.parseInt(request.getParameter("total_unit"));
			
			SubjectDAO dao=new SubjectDAO();
			List<Subject> subject=dao.getallsubject();
			
			
			
			
			
			return "subject_list.jsp";
		}
	}