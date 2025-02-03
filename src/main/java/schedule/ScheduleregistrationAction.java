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
			String schedule_datetime = request.getParameter("schedule_datetime");
			String[] parts = schedule_datetime.split(":");
	        String formattedTime = parts[0] + "時" + parts[1] + "分";
			
			CalendarDAO dao = new CalendarDAO();
			
			int line = dao.cal_reg(user_id.getUser_id(),selectdate, formattedTime, schedule_content);
			
			request.setAttribute("comp_mes", "データの登録が完了しました");
			return "Schedule_plan.action";
			
		}
	}