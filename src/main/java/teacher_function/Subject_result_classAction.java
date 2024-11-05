package teacher_function;

import java.util.List;

import bean.Studentaccount;
import bean.Subject;
import dao.AccountDAO;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Subject_result_classAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			List<Studentaccount> result_student_list=null;
			
			AccountDAO dao=new AccountDAO();
			result_student_list= dao.student_search_class(class_id);
			
			SubjectDAO sdao=new SubjectDAO();
			List<Subject> class_subject = sdao.getclasssubject(class_id);
			
			if(result_student_list.size() > 0) {
				session.setAttribute("subject_result_student_list", result_student_list);
				session.setAttribute("subject_result_list", class_subject);
				return "subject_result.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "subject_result.jsp";
		}
	}