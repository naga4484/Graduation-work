package group;

import java.util.List;

import bean.Group;
import bean.User_id;
import dao.GroupDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_file_upload_datailAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			User_id user_id = (User_id)session.getAttribute("user");
			
			String group_id=request.getParameter("group_id");
			
			GroupDAO dao=new GroupDAO();
			List<Group> file_list = dao.search_group_file(group_id);
			
			session.setAttribute("select_id", group_id);
			session.setAttribute("file_list", file_list);
			return "group_file_upload.jsp";
	}
}