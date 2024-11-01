package teacher_function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacheraccount;
import bean.Timetable_template;
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

			String templateName = request.getParameter("templateName");
			TimetableDAO dao = new TimetableDAO();
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			List<Timetable_template> timetablelist = dao.distinct_timetable_template(account.getTeacher_id());
			String id_num = Integer.toString(timetablelist.size() + 1);
			int line = 0;
			
			Map<Integer, String> itemSettings = new HashMap<>();
	        for (int i = 1; i <= 4; i++) {
	            String subjectName = request.getParameter("itemSettings[" + i + "][name]");
	            if (subjectName != null && !subjectName.isEmpty()) {
	                itemSettings.put(i, subjectName);
	                line = dao.timetable_template_registration(id_num, templateName,subjectName, Integer.toString(i),account.getTeacher_id());
	            }else{
	            	line = dao.timetable_template_registration(id_num, templateName,null, Integer.toString(i),account.getTeacher_id());
	            }
	        }
			return "Timetable_template_set.action";
		}
	}