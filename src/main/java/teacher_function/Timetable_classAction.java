package teacher_function;

import java.util.List;

import bean.Teacheraccount;
import bean.Timetable;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_classAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			TimetableDAO dao=new TimetableDAO();
			List<Timetable> timetableList = dao.timetable_class(class_id, account.getTeacher_id());
			String id_num = Integer.toString(dao.timetable_distinct().size() + 1); 
			List<String> timetable_select= dao.timetable_select(class_id, account.getTeacher_id());
			
			//時間割機能の初期設定
			if(timetableList.size() == 0) {
				int line=dao.time_Initial_settings(id_num,class_id,account.getTeacher_id());
				timetableList = dao.timetable_class(class_id, account.getTeacher_id());
				timetable_select= dao.timetable_select(class_id, account.getTeacher_id());
				session.setAttribute("timetableList", timetableList);
				session.setAttribute("timetable_select", timetable_select);
				session.setAttribute("Flag", "ok");
				return "timetable_management.jsp"; 
				
			}
			
			session.setAttribute("timetableList", timetableList);
			session.setAttribute("timetable_select", timetable_select);
			session.setAttribute("Flag", "ok");
			return "timetable_management.jsp";
		}
	}