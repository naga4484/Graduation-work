package teacher_function;

import java.util.List;

import bean.Subject;
import bean.Teacheraccount;
import bean.User_id;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectlisttopAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			
			SubjectDAO dao=new SubjectDAO();
			List<Subject> subject=dao.getallsubject();
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			List<Subject> class_subject = dao.getclasssubject(account.getClass_id());
			session.setAttribute("class_subject", class_subject);
			
			return "subject_list.jsp";
		}
	}