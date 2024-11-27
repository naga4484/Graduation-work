package teacher_function;

import java.util.List;

import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Submissions_detail_changeAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}
			
			String submission_name=request.getParameter("submission_name");
			int submissions_id=Integer.parseInt(request.getParameter("submissions_id"));
			SubmissionsDAO sdao=new SubmissionsDAO();
			List<Submissions> distinctsubmissions = sdao.distinctsubmissions(submission_name,submissions_id);
			
			if(distinctsubmissions.size()>1) {
				request.setAttribute("distinct_error", "提出物名が重複しています");
				return "submissions_detail.jsp";
			}
			String subject_id=request.getParameter("subject_id");
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String fulldata = year + "年" +month + "月" + date + "日";
			String save_path = "../submissions_files/" + submission_name;
			
		
			int line = sdao.change_submissions(submissions_id, submission_name, save_path, fulldata, subject_id);
					
			
			request.setAttribute("change_mes","「"+submission_name+"」の変更が完了しました");
			session.removeAttribute("distinctsubmissions_class");
			return "submissions_confirmation.jsp";
		}
	}