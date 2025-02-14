package teacher_function;

import java.util.List;

import bean.Teacheraccount;
import bean.Timetable_template;
import bean.User_id;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_template_updateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}
			
			String template_name=request.getParameter("template_name");
			TimetableDAO dao = new TimetableDAO();
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			List<Timetable_template> timetablelist = dao.timetable_template_name(template_name);
			
			session.setAttribute("timetable_edit", timetablelist);
			session.setAttribute("template_name", template_name);
			return "timetable_template_edit.jsp";
		}
	}