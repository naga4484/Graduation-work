package teacher_function;

import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Submissions_detail_deleteAction extends Action {
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
			int line = sdao.delete_submission(submissions_id);
			
			if(line>0) {
				request.setAttribute("delete_mes", "科目コード「" + submissions_id + "」の削除が完了しました");
				session.removeAttribute("distinctsubmissions_class");
				return "submissions_confirmation.jsp";
			}
			
			return "submissions_confirmation.jsp";
		}
	}