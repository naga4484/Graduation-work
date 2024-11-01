package teacher_function;

import java.util.HashMap;
import java.util.Map;

import bean.Teacheraccount;
import dao.TimetableDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Timetable_template_editAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String templateName = request.getParameter("templateName");
			String template_id = request.getParameter("template_id");
			TimetableDAO dao = new TimetableDAO();
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			int line = 0;
			
			Map<Integer, String> itemSettings = new HashMap<>();
	        for (int i = 1; i <= 4; i++) {
	            String subjectName = request.getParameter("itemSettings[" + i + "][name]");
	            if (subjectName != null && !subjectName.isEmpty()) {
	                itemSettings.put(i, subjectName);
	                line = dao.timetable_template_update(template_id,templateName,subjectName,Integer.toString(i));
	            }else{
	            	line = dao.timetable_template_update(template_id,templateName,null,Integer.toString(i));
	            }
	        }
			return "Timetable_template_set.action";
		}
	}