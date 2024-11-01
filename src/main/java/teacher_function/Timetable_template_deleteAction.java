package teacher_function;

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
			
			String template_id=request.getParameter("template_id");
			TimetableDAO dao = new TimetableDAO();
			int line = dao.timetable_template_delete(template_id);
			
			request.setAttribute("delete_mes", "テンプレートID「" + template_id + "」の削除が完了しました");
			return "Timetable_template_set.action";
		}
	}