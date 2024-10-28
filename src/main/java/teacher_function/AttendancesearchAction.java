package teacher_function;

import java.util.List;

import bean.Studentaccount;
import dao.AccountDAO;
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
			List<Studentaccount> attendancsstudentlist=null;
			
			AccountDAO dao=new AccountDAO();
			if(name.equals("") == true) {
				attendancsstudentlist = dao.student_search_class(class_id);
			}
			else{
				attendancsstudentlist = dao.student_search_name(name);
			}
			
			if(attendancsstudentlist.size() > 0) {
				session.setAttribute("attendancsstudentlist", attendancsstudentlist);
				return "attendance.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "attendance.jsp";
		}
	}