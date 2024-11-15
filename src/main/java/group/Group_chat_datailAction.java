package group;

import java.util.List;

import bean.Group;
import bean.User_id;
import dao.GroupDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_chat_datailAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			User_id user_id = (User_id)session.getAttribute("user");
			
			String group_id=request.getParameter("group_id");
			
			
			GroupDAO dao=new GroupDAO();
			List<Group> chat_list = dao.search_group_chat_id(group_id);
			
			session.setAttribute("select_id", group_id);
			session.setAttribute("chat_list", chat_list);
			return "group_chat.jsp";
	}
}