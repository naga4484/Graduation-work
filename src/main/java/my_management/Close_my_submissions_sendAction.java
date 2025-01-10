package my_management;

import bean.User_id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Close_my_submissions_sendAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}
			
			session.removeAttribute("my_submissions");
			session.removeAttribute("my_send_submissions");

			
			return "my_submissions.jsp";
	}
}