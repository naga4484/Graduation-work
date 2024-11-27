package teacher_function;

import bean.Teacheraccount;
import bean.User_id;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String teacher_id=request.getParameter("teacher_id");
			String password="password";
			String class_id=request.getParameter("class_id");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			
			AccountDAO no_dao=new AccountDAO();
			Teacheraccount s=no_dao.teacher_search_id(teacher_id);
			
			if (s != null) {
				request.setAttribute("teacher_id_duplication_error", "教師IDが重複しています");
				request.setAttribute("entered_name", name);
				request.setAttribute("entered_teacher_id", teacher_id);
				request.setAttribute("entered_class_id", class_id);
				request.setAttribute("entered_email", email);
				return "teacher_registration.jsp";
			}

			AccountDAO dao=new AccountDAO();
			int line=dao.teacher_registration(teacher_id, password,class_id,name,email);
			
			request.setAttribute("comp_mes", "登録が完了いたしました");
			return "teacher_registration.jsp";
		}
	}