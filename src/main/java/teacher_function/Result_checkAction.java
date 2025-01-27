package teacher_function;
import java.util.List;

import bean.Subject;
import bean.Submissions;
import bean.User_id;
import dao.SubjectDAO;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Result_checkAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			
			SubmissionsDAO submissionsdao = new SubmissionsDAO();
			SubjectDAO subjectdao = new SubjectDAO();
			List<Submissions> submissions_list = submissionsdao.getsubmissions();
			List<Subject> subject_list = subjectdao.getallsubject();
			
			
			session.setAttribute("submissions_list", submissions_list);
			session.setAttribute("subject_list", subject_list);
			return "result_check.jsp";
		}
	}