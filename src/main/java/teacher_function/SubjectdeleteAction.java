package teacher_function;

import java.util.List;

import bean.Subject;
import bean.Teacheraccount;
import dao.SubjectDAO;
import dao.TimetableDAO;
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
			
			//科目削除時に自動的に削除されてしまうので、無理矢理更新する
			TimetableDAO tdao = new TimetableDAO();
			int num = tdao.timetable_update(subject_id);
			num = tdao.timetable_template_update(subject_id);
			
			SubjectDAO dao=new SubjectDAO();
			int line = dao.delete_subject(subject_id);
			
			
			Teacheraccount account = (Teacheraccount) session.getAttribute("account");
			List<Subject> class_subject = dao.getclasssubject(account.getClass_id());
			session.setAttribute("class_subject", class_subject);
			
			if(line>0) {
				request.setAttribute("delete_mes", "科目コード「" + subject_id + "」の削除が完了しました");
				return "Subjectlisttop.action";
			}
			
			return "subject_list.jsp";
		}
	}