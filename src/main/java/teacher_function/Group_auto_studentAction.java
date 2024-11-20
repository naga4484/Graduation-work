package teacher_function;

import java.util.List;

import bean.Studentaccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_auto_studentAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			session.removeAttribute("reader_flag");

			String class_id=request.getParameter("class_id");
			
			AccountDAO dao = new AccountDAO();
			List<Studentaccount> st_list = dao.student_search_class(class_id);
			
			if(st_list.size() > 0) {
				session.setAttribute("st_list", st_list);
				return "group_auto_create.jsp";
			}
			
			
			request.setAttribute("none_error", "検索結果に一致する学生が居ません");
			return "group_auto_create.jsp";
		}
	}