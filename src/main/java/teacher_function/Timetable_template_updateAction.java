package teacher_function;

import java.util.List;

import bean.Teacheraccount;
import bean.Timetable_template;
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
			
			String template_id=request.getParameter("template_id");
			TimetableDAO dao = new TimetableDAO();
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			List<Timetable_template> timetablelist = dao.timetable_template_id(template_id);
			
			session.setAttribute("timetable_edit", timetablelist);
			return "timetable_template_edit.jsp";
		}
	}