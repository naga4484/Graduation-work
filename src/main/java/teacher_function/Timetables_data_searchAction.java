package teacher_function;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bean.Teacheraccount;
import bean.Timetable;
import bean.Timetable_template;
import bean.User_id;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetables_data_searchAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String class_id=request.getParameter("class_id");
			String data=request.getParameter("data_num");
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			TimetableDAO dao=new TimetableDAO();
			List<Timetable> timetable_change_List = dao.timetable_data_select(class_id, account.getTeacher_id(), data);
			
			LocalDate now_data = LocalDate.parse(data + "01日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
			int Month_of_days = now_data.lengthOfMonth();
			List<Timetable_template> timetable_template_teacher = dao.timetable_template(account.getTeacher_id());
			
			
			session.setAttribute("timetable_change_List", timetable_change_List);
			session.setAttribute("Month_of_days", Month_of_days);
			session.setAttribute("data_num", data);
			session.setAttribute("timetable_update_class_id", class_id);
			session.setAttribute("timetable_template_teacher", timetable_template_teacher);
			return "timetable_management.jsp";
		}
	}