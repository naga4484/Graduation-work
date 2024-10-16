package teacher_function;

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
			
			AccountDAO dao=new AccountDAO();
			
			
			
			if(line>0) {
				return "student_registration.jsp";
			}
			return "student_registration.jsp";
		}
	}