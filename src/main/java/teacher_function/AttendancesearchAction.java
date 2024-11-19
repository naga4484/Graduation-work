package teacher_function;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bean.Attendance;
import bean.Studentaccount;
import dao.AccountDAO;
import dao.AttendanceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendancesearchAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			String name=request.getParameter("student_name");
			Date date = new Date();
	        String sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
			List<Studentaccount> attendancsstudentlist=null;
			List<Attendance> samplelist=null;
			
			AccountDAO dao=new AccountDAO();
			AttendanceDAO adao=new AttendanceDAO();
			if(name.equals("") == true) {
				attendancsstudentlist = dao.student_search_class(class_id);
				samplelist=adao.class_search(class_id, sdf);
			}
			else{
				attendancsstudentlist = dao.student_search_name(name);
				samplelist=adao.name_search(name, sdf);
			}
			
			if(attendancsstudentlist.size() > 0) {
				session.setAttribute("attendancsstudentlist", attendancsstudentlist);
				if(samplelist.size() > 0) {
					session.setAttribute("samplelist", samplelist);
				}
				return "attendance.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "attendance.jsp";
		}
	}