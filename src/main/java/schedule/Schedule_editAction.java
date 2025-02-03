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
			List<String> time_list = new ArrayList<>();;
			for(int i = 0;i < list_size;i++) {
				String time = request.getParameter(i + "_time_" + user_id.getUser_id());
				String[] parts = time.split(":");
		        String formattedTime = parts[0] + "時" + parts[1] + "分";
		        time_list.add(formattedTime);
			}
			
			//変更のために一度全削除
			line = dao.cal_del(user_id.getUser_id(), selectdate);
			
			//変更のための新規登録
			for(int i = 0;i < list_size;i++) {
				String content = request.getParameter(i + "_content_" + user_id.getUser_id());
				line = dao.cal_reg(user_id.getUser_id(), selectdate, time_list.get(i),content);
			}
			
			List<Calendar> cal_list = dao.calender_list(user_id.getUser_id(), selectdate);
			
			request.setAttribute("edit_mes", "変更が完了いたしました");
			session.setAttribute("cal_list", cal_list);
			return "schedule_plan.jsp";
		}
	}