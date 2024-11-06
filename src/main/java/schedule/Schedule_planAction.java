package schedule;

import java.util.List;

import bean.Calendar;
import bean.User_id;
import dao.CalendarDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Schedule_planAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String selectdate = (String)session.getAttribute("selectedDate");
			User_id user_id = (User_id)session.getAttribute("user");
			
			CalendarDAO dao = new CalendarDAO();
			List<Calendar> cal_list = dao.calender_list(user_id.getUser_id(), selectdate);
			
			session.setAttribute("cal_list", cal_list);
			return "schedule_plan.jsp";
		}
	}