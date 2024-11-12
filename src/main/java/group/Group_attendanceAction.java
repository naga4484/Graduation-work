package group;

import bean.User_id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_attendanceAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id)session.getAttribute("user");
			
			
			return "group_attendance.jsp";
	}
}