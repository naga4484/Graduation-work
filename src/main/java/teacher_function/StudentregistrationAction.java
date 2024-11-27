package teacher_function;

import java.util.List;

import bean.Studentaccount;
import bean.User_id;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String student_id=request.getParameter("student_id");
			String password="password";
			String class_id=request.getParameter("class_id");
			String name=request.getParameter("name");
			
			AccountDAO no_dao=new AccountDAO();
			List<Studentaccount> s=no_dao.student_search_no(student_id);
			
			if (s.size() != 0) {
				request.setAttribute("student_id_duplication_error", "学生IDが重複しています");
				request.setAttribute("entered_name", name);
				request.setAttribute("entered_student_id", student_id);
				request.setAttribute("entered_class_id", class_id);
				return "student_registration.jsp";
			}

			AccountDAO dao=new AccountDAO();
			int line=dao.student_registration(student_id, password,class_id,name);
			
			if(line>0) {
				return "student_registration.jsp";
			}
			return "student_registration.jsp";
		}
	}