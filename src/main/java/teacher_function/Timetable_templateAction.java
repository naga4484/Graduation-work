package teacher_function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacheraccount;
import bean.Timetable_template;
import bean.User_id;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_templateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String templateName = request.getParameter("templateName");
			TimetableDAO dao = new TimetableDAO();
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			List<Timetable_template> timetablelist = dao.distinct_timetable_template_name(templateName);
			if(timetablelist.size() > 0) {
				request.setAttribute("dis_mes", "テンプレート名が重複しています");
				return "timetable_template.jsp";
			}
			int line = 0;
			
			Map<Integer, String> itemSettings = new HashMap<>();
	        for (int i = 1; i <= 4; i++) {
	            String subjectName = request.getParameter("itemSettings[" + i + "][name]");
	            if (subjectName != null && !subjectName.isEmpty()) {
	                itemSettings.put(i, subjectName);
	                line = dao.timetable_template_registration(templateName,subjectName, Integer.toString(i),account.getTeacher_id());
	            }else{
	            	line = dao.timetable_template_registration(templateName,null, Integer.toString(i),account.getTeacher_id());
	            }
	        }
	        List<Timetable_template> timetable_template_teacher = dao.timetable_template(account.getTeacher_id());
	        session.setAttribute("timetable_template_teacher", timetable_template_teacher);
			return "Timetable_template_set.action";
		}
	}