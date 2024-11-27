package teacher_function;

import java.util.List;

import bean.Studentaccount;
import bean.User_id;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubmissionsstudentAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String class_id=request.getParameter("class_id");
			String name=request.getParameter("student_name");
			List<Studentaccount> submissionsstudent=null;
			
			AccountDAO dao=new AccountDAO();
			if(name.equals("") == true) {
				submissionsstudent = dao.student_search_class(class_id);
			}
			else{
				submissionsstudent = dao.student_search_name(name);
			}
			
			if(submissionsstudent.size() > 0) {
				session.setAttribute("submissionsstudent", submissionsstudent);
				return "submissions_registration.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "submissions_registration.jsp";
		}
	}