package teacher_function;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bean.Studentaccount;
import dao.AttendanceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendanceNotregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			List<Studentaccount> not_attendancestudent = (List<Studentaccount>) session.getAttribute("not_attendancestudent");
			
			AttendanceDAO dao = new AttendanceDAO();
			List<String> id_list = dao.getattendanceid();
			Date date = new Date();
	        String sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
			
			
			for(Studentaccount i : not_attendancestudent) {
				String Flag=request.getParameter(i.getStudent_id());
				if(Flag.equals("公欠")==true) {
					int line = dao.attendance_update(sdf, i.getStudent_id(), "3", "");
				}
				else if(Flag.equals("病欠")==true) {
					int line = dao.attendance_update(sdf, i.getStudent_id(), "2", "");				
				}
				else if(Flag.equals("遅刻")==true) {
					int line = dao.attendance_update(sdf, i.getStudent_id(), "4", "");
				}
				else if(Flag.equals("その他")==true) {
					int line = dao.attendance_update(sdf, i.getStudent_id(), "0", "");
				}
				
			}
			
			session.removeAttribute("not_attendancestudent");
			session.removeAttribute("attendancsstudentlist");
			request.setAttribute("attendance_finish", "登録が完了いたしました");
			return "attendance.jsp";
		}
	}
