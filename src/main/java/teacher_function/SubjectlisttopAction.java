package teacher_function;

import java.util.List;

import bean.Subject;
import bean.Teacheraccount;
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

			
			SubjectDAO dao=new SubjectDAO();
			List<Subject> subject=dao.getallsubject();
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			List<Subject> class_subject = dao.getclasssubject(account.getClass_id());
			session.setAttribute("class_subject", class_subject);
			
			return "subject_list.jsp";
		}
	}