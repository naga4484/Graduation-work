package teacher_function;

import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Submissions_detailAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			int submissions_id=Integer.parseInt(request.getParameter("submissions_id"));
			SubmissionsDAO sdao=new SubmissionsDAO();
			Submissions distinctsubmissions_id = sdao.distinctsubmissions_id(submissions_id);
			
			session.setAttribute("distinctsubmissions_id", distinctsubmissions_id);
			return "submissions_detail.jsp";
		}
	}