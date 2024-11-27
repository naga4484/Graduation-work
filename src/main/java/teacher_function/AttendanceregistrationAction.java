package teacher_function;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Studentaccount;
import bean.User_id;
import dao.AttendanceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendanceregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}
			
			
			session.removeAttribute("not_attendancestudent");
			List<Studentaccount> attendancsstudentlist = (List<Studentaccount>) session.getAttribute("attendancsstudentlist");
			
			AttendanceDAO dao = new AttendanceDAO();
			List<String> id_list = dao.getattendanceid();
			int list_size = id_list.size();
			String id_num = Integer.toString(list_size + 1);
			Date date = new Date();
	        String sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
	        List<Studentaccount> not_attendancestudent = new ArrayList<>();
			
			
			for(Studentaccount i : attendancsstudentlist) {
				String Flag=request.getParameter(i.getStudent_id());
				if(Flag==null) {
					Flag="";
				}
				
				if(Flag.equals("出席")==true) {
					boolean g = dao.student_search(i.getStudent_id(),sdf);
					
					if(g == false) {
						int line = dao.attendance_registration(id_num, i.getStudent_id(), "1", sdf, i.getClass_id(), "");
					}
					else if(g == true) {
						int line = dao.attendance_update(sdf, i.getStudent_id(), "1", "");
					}
				}
				else {
					boolean g = dao.student_search(i.getStudent_id(),sdf);
					
					if(g == false) {
						int line = dao.attendance_registration(id_num, i.getStudent_id(), "1", sdf, i.getClass_id(), "");
					}
					else if(g == true) {
						int line = dao.attendance_update(sdf, i.getStudent_id(), "1", "");
					}
					not_attendancestudent.add(i);
				}
			}
			
			if(not_attendancestudent.size() > 0) {
				session.setAttribute("not_attendancestudent", not_attendancestudent);
				return "attendance.jsp";
			}
			request.setAttribute("attendance_finish", "登録が完了いたしました");
			session.removeAttribute("attendancsstudentlist");
			return "attendance.jsp";
		}
	}