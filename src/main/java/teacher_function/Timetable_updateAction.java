package teacher_function;

import bean.Teacheraccount;
import bean.User_id;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_updateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String class_id=(String)session.getAttribute("timetable_update_class_id");
			String data=(String)session.getAttribute("data_num");
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			int Month_of_days=(Integer)session.getAttribute("Month_of_days");
			TimetableDAO dao=new TimetableDAO();
			int line=0;
			for(int i = 1;i <= Month_of_days;i++) {
				String today_data;
				if(i < 10) {
					today_data = data + "0" + i + "日";
				}else{
					today_data = data + i + "日";
				}
				for(int j = 1;j <= 4;j++) {
					String today_data_time = today_data + j;
					String subject_id=request.getParameter(today_data_time);
					if(subject_id.equals("Notset") == true) {
						line=dao.timetable_update(null, Integer.toString(j), account.getTeacher_id(),class_id,today_data);
					}
					else {
						line=dao.timetable_update(subject_id, Integer.toString(j), account.getTeacher_id(),class_id,today_data);
					}
				}
			}
			session.removeAttribute("timetableList");
			session.removeAttribute("timetable_select");
			session.removeAttribute("timetable_change_List");
			session.removeAttribute("Month_of_days");
			session.removeAttribute("data_num");
			session.removeAttribute("timetable_update_class_id");
			request.setAttribute("success_mes", "時間割の登録が完了しました。");
			return "timetable_management.jsp";
		}
	}