package teacher_function;

import java.util.List;

import bean.Result;
import bean.User_id;
import dao.ResultDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Result_searchAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String class_id=request.getParameter("class_id");
			String result_kind=request.getParameter("result_kind");
			
			
			
			ResultDAO dao=new ResultDAO();
			
			
			if(result_kind.equals("semester") == true) {
				List<Result> search_result_list = dao.get_semester_result_class(class_id);
				request.setAttribute("semester_result_list", search_result_list);
				return "result_check.jsp";
			}
			else if(result_kind.equals("subject") == true) {
				String subject_id=request.getParameter("subject_id");
				List<Result> search_result_list = dao.getSubject_result_all(subject_id);
				request.setAttribute("subject_result_list", search_result_list);
				return "result_check.jsp";
			}
			else if(result_kind.equals("submissions") == true) {
				String submissions_id=request.getParameter("submissions_id");
				List<Result> search_result_list = dao.getSubmissions_result_all(submissions_id);
				request.setAttribute("submissions_result_list", search_result_list);
				return "result_check.jsp";
				
			}
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "result_check.jsp";
		}
	}