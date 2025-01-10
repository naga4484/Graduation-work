package schedule;

import bean.User_id;
import dao.CalendarDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ScheduleregistrationAction extends Action {
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
			String schedule_content = request.getParameter("schedule_content");
			String schedule_hour = request.getParameter("hour");
			if(schedule_hour.length() == 1) {
				schedule_hour = "0" + schedule_hour;
			}
			String schedule_minute = request.getParameter("minute");
			if(schedule_minute.length() == 1) {
				schedule_minute = "0" + schedule_minute;
			}
			String schedule_time = schedule_hour + "時" + schedule_minute + "分";
			
			CalendarDAO dao = new CalendarDAO();
			
			int line = dao.cal_reg(user_id.getUser_id(),selectdate, schedule_time, schedule_content);
			
			request.setAttribute("comp_mes", "データの登録が完了しました");
			return "Schedule_plan.action";
			
		}
	}