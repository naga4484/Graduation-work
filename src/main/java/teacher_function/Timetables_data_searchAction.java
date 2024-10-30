package teacher_function;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bean.Teacheraccount;
import bean.Timetable;
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

			String class_id=request.getParameter("class_id");
			String data=request.getParameter("data_num");
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			TimetableDAO dao=new TimetableDAO();
			List<Timetable> timetable_change_List = dao.timetable_data_select(class_id, account.getTeacher_id(), data);
			
			LocalDate now_data = LocalDate.parse(data + "01日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
			int Month_of_days = now_data.lengthOfMonth(); 
			
			
			session.setAttribute("timetable_change_List", timetable_change_List);
			session.setAttribute("Month_of_days", Month_of_days);
			session.setAttribute("data_num", data);
			session.setAttribute("timetable_update_class_id", class_id);
			return "timetable_management.jsp";
		}
	}