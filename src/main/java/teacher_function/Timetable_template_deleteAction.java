package teacher_function;

import java.util.List;

import bean.Timetable_template;
import bean.User_id;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_template_deleteAction extends Action {
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
			int line = dao.timetable_template_delete(template_name);
			
			List<Timetable_template> timetable_template_teacher = dao.timetable_template(select_user_id.getTeacher_id());
	        session.setAttribute("timetable_template_teacher", timetable_template_teacher);
			
			request.setAttribute("delete_mes", "テンプレートID「" + template_name + "」の削除が完了しました");
			return "Timetable_template_set.action";
		}
	}