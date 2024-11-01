package teacher_function;

import java.util.List;

import bean.Teacheraccount;
import bean.Timetable_template;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_template_setAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			TimetableDAO dao = new TimetableDAO();
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			List<Timetable_template> timetablelist = dao.distinct_timetable_template(account.getTeacher_id());
			
			session.setAttribute("timetablelist", timetablelist);
			return "timetable_template_list.jsp";
		}
	}