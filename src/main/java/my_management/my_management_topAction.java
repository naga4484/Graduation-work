package my_management;

import bean.Studentaccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class my_management_topAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			Studentaccount account = (Studentaccount)session.getAttribute("account");
			return "my_management.jsp";
	}
}