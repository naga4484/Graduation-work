package teacher_function;

import java.util.List;

import bean.Studentaccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Semester_result_classAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			List<Studentaccount> semester_result_student_list=null;
			
			AccountDAO dao=new AccountDAO();
			semester_result_student_list= dao.student_search_class(class_id);
			
			if(semester_result_student_list.size() > 0) {
				session.setAttribute("semester_result_student_list", semester_result_student_list);
				return "semester_result.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "semester_result.jsp";
		}
	}