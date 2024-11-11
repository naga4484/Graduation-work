package my_management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bean.Attendance;
import bean.Calendar;
import bean.Studentaccount;
import bean.Submissions;
import bean.Teacheraccount;
import bean.User_id;
import dao.AttendanceDAO;
import dao.CalendarDAO;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class My_management_topAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id)session.getAttribute("user");
			
			//スケジュール取得のための処理
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String start_date = today.format(formatter);
	        String end_date = today.plusDays(7).format(formatter);
	        CalendarDAO cdao = new CalendarDAO();
	        List<Calendar> cal_date_list = cdao.my_management_schedule_date(user_id.getUser_id(), start_date, end_date);
	        List<Calendar> cal_content_list = cdao.my_management_schedule(user_id.getUser_id(), start_date, end_date);
	        session.setAttribute("my_management_date_list", cal_date_list);
	        session.setAttribute("my_management_content_list", cal_content_list);
	        
			
			//教師アカウントの処理
			if(user_id.getStudent_id() == null) {
	        	Teacheraccount account = (Teacheraccount)session.getAttribute("account");
	        	
	        	
	        }
			//学生アカウントの処理
			else if(user_id.getTeacher_id() == null) {
	        	Studentaccount account = (Studentaccount)session.getAttribute("account");
	        	
	        	//出欠日数計算のための処理
	        	AttendanceDAO adao = new AttendanceDAO();
	        	List<Attendance> at_list = adao.attendance_calculation(account.getStudent_id());
	        	float at_list_date = at_list.size();
	        	int count = 0;
	        	for(Attendance i : at_list) {
	        		if(i.getAttendance_kind_id().equals("1")) {
	        			count++;
	        		}
	        	}
	        	float attendance_management_date = (float)count / (float)at_list_date;
	        	session.setAttribute("attendance_count", count);
	        	session.setAttribute("attendance_management_date", attendance_management_date);
	        	session.setAttribute("attendance_management_date_sub", at_list.size()-count);
	        	session.setAttribute("attendance_management_list_size", at_list.size());
	        	
	        	SubmissionsDAO sdao = new SubmissionsDAO();
	        	List<Submissions> my_submissions_list = sdao.submissions_my_management(account.getStudent_id());
	        	session.setAttribute("my_submissions_list", my_submissions_list);
	        	
	        	
	        }
			return "my_management.jsp";
	}
}