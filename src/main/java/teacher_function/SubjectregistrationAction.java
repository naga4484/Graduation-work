package teacher_function;

import java.util.List;

import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String subject_id=request.getParameter("subject_id");
			String subject_name=request.getParameter("subject_name");
			int total_unit=Integer.parseInt(request.getParameter("total_unit"));
			String class_id=request.getParameter("class_id");
			
			
			SubjectDAO no_dao=new SubjectDAO();
			List<Subject> s=no_dao.subject_search_no(subject_id);
			
			if (s.size() != 0) {
				request.setAttribute("subject_id_duplication_error", "科目IDが重複しています");
				request.setAttribute("entered_name", subject_name);
				request.setAttribute("entered_subject_id", subject_id);
				return "subject_registration.jsp";
			}

			SubjectDAO dao=new SubjectDAO();
			int line=dao.subject_registration(subject_id ,class_id, subject_name,total_unit);
			
			if(line>0) {
				return "Subjectlisttop.action";
			}
			
			return "subject_list.jsp";
		}
	}