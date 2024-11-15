package group;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bean.Group;
import bean.User_id;
import dao.GroupDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Chat_submitAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			User_id user_id = (User_id)session.getAttribute("user");
			String group_id=request.getParameter("group_id");
			DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH時mm分ss秒");
			DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			String chat_content=request.getParameter("chat_content");
			
			LocalDate localdate = LocalDate.now();
			LocalTime localtime = LocalTime.now();
			String chat_date = localdate.format(dateformatter) + localtime.format(timeformatter);
			
			
			
			GroupDAO dao=new GroupDAO();
			int line = dao.chat_insert(group_id, chat_content, user_id.getUser_id(), chat_date);
			List<Group> chat_list = dao.search_group_chat_id(group_id);
			
			session.setAttribute("chat_list", chat_list);
			return "group_chat.jsp";
	}
}