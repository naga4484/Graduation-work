package teacher_function;

import java.util.List;

import bean.Studentaccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendanceregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			List<Studentaccount> attendancsstudentlist = (List<Studentaccount>) session.getAttribute("attendancsstudentlist");
			
			for(Studentaccount i : attendancsstudentlist) {
				
			}
			return "attendance.jsp";
		}
	}