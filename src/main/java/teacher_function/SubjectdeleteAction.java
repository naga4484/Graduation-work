package teacher_function;

import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectdeleteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			
			String subject_id=request.getParameter("subject_id");
			
			SubjectDAO dao=new SubjectDAO();
			int line = dao.delete_subject(subject_id);
			
			if(line>0) {
				request.setAttribute("delete_mes", "科目コード「" + subject_id + "」の削除が完了しました");
				return "Subjectlisttop.action";
			}
			
			return "subject_list.jsp";
		}
	}