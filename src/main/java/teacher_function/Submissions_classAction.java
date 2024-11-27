package teacher_function;

import java.util.List;

import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Submissions_classAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String class_id=request.getParameter("class_id");
			
			SubmissionsDAO sdao=new SubmissionsDAO();
			List<Submissions> distinctsubmissions_class = sdao.distinctsubmissions_class(class_id);
			
			if(distinctsubmissions_class.size() == 0) {
				request.setAttribute("none_error", "対象のクラスには課題が設定されていません");
			}
			
			session.setAttribute("distinctsubmissions_class", distinctsubmissions_class);
			return "submissions_confirmation.jsp";
		}
	}