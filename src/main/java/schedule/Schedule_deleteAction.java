package schedule;

import java.util.List;

import bean.Calendar;
import bean.User_id;
import dao.CalendarDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Schedule_deleteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String selectdate = (String)session.getAttribute("selectedDate");
			User_id user_id = (User_id)session.getAttribute("user");
			int calendar_id = Integer.parseInt(request.getParameter("delete_data"));
			
			CalendarDAO dao = new CalendarDAO();
			
			int line = dao.cal_del(calendar_id);
			List<Calendar> cal_list = dao.calender_list(user_id.getUser_id(), selectdate);
			
			session.setAttribute("cal_list", cal_list);
			request.setAttribute("del_mes", "データの削除が完了いたしました");
			return "schedule_edit.jsp";
		}
	}