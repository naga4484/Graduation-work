package my_management;

import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class My_submissions_management_updateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id)session.getAttribute("user");
			
			//提出物情報の取得
			int submissions_id=Integer.parseInt(request.getParameter("submissions_id"));
			SubmissionsDAO dao = new SubmissionsDAO();
			Submissions my_send_submissions = dao.submissions_alignment_list(submissions_id, user_id.getStudent_id());
			
			session.setAttribute("my_send_submissions", my_send_submissions);
			return "my_submissions.jsp";
	}
}