package teacher_function;

import java.util.List;

import bean.Studentaccount;
import bean.Submissions;
import dao.AccountDAO;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Submissions_result_classAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			List<Studentaccount> result_student_list=null;
			
			AccountDAO dao=new AccountDAO();
			result_student_list= dao.student_search_class(class_id);
			
			SubmissionsDAO sdao=new SubmissionsDAO();
			List<Submissions> class_submissions = sdao.distinctsubmissions_class(class_id);
			
			if(result_student_list.size() > 0) {
				session.setAttribute("submissions_result_student_list", result_student_list);
				session.setAttribute("submissions_result_list", class_submissions);
				return "submissions_result.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "submissions_result.jsp";
		}
	}