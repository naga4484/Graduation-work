package teacher_function;

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
			
			request.setAttribute("delete_mes", "テンプレートID「" + template_name + "」の削除が完了しました");
			return "Timetable_template_set.action";
		}
	}