package schedule;

import java.util.ArrayList;
import java.util.List;

import bean.Calendar;
import bean.User_id;
import dao.CalendarDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Schedule_editAction extends Action {
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
			int list_size = Integer.parseInt(request.getParameter("list_size"));
			CalendarDAO dao = new CalendarDAO();
			int line = 0;
			
			//変更内容の重複を確認する
			//変更情報をリストにまとめる
			List<String> hour_list = new ArrayList<>();;
			List<String> minute_list = new ArrayList<>();;
			for(int i = 0;i < list_size;i++) {
				String hour = request.getParameter(i + "_hour_" + user_id.getUser_id());
				if(hour.length() == 2 && hour.substring(0,1).equals("0")) {
					hour = hour.substring(1);
				}
				hour_list.add(hour);
				String minute = request.getParameter(i + "_minute_" + user_id.getUser_id());
				if(minute.length() == 2 && minute.substring(0,1).equals("0")) {
					minute = minute.substring(1,2);
				}
				minute_list.add(minute);
			}
			
			//変更のために一度全削除
			line = dao.cal_del(user_id.getUser_id(), selectdate);
			
			//変更のための新規登録
			for(int i = 0;i < list_size;i++) {
				String content = request.getParameter(i + "_content_" + user_id.getUser_id());
				String hour = hour_list.get(i);
				if(hour.length() == 1) {
					hour = "0" + hour;
				}
				String minute = minute_list.get(i);
				if(minute.length() == 1) {
					minute = "0" + minute;
				}
				String data = hour + "時" + minute + "分";
				line = dao.cal_reg(user_id.getUser_id(), selectdate, data,content);
			}
			
			List<Calendar> cal_list = dao.calender_list(user_id.getUser_id(), selectdate);
			
			request.setAttribute("edit_mes", "変更が完了いたしました");
			session.setAttribute("cal_list", cal_list);
			return "schedule_plan.jsp";
		}
	}