package teacher_function;

import java.util.List;

import bean.Studentaccount;
import bean.Submissions;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubmissionsregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			
			String submission_name=request.getParameter("submission_name");
			SubmissionsDAO sdao=new SubmissionsDAO();
			List<Submissions> distinctsubmissions = sdao.distinctsubmissions(submission_name);
			
			if(distinctsubmissions.size()>0) {
				request.setAttribute("distinct_error", "提出物名が重複しています");
				return "submissions_registration.jsp";
			}
			String subject_id=request.getParameter("subject_id");
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String fulldata = year + "年" +month + "月" + date + "日";
			String save_path = "../submissions_files/" + submission_name;
			List<Studentaccount> submissionsstudent = (List<Studentaccount>) session.getAttribute("submissionsstudent");
			
			for(Studentaccount i : submissionsstudent) {
				String Flag=request.getParameter(i.getStudent_id());
				if(Flag==null) {
					Flag="";
				}
				
				if(Flag.equals("〇")==true) {
					int line = sdao.submissions_registration(submission_name, save_path , fulldata, i.getClass_id() ,subject_id);
					break;
				}
			}
			int submissions_id = sdao.id_search(submission_name);
			
			for(Studentaccount i : submissionsstudent) {
				String Flag=request.getParameter(i.getStudent_id());
				if(Flag==null) {
					Flag="";
				}
				
				if(Flag.equals("〇")==true) {
					int line = sdao.submissions_alignment(submissions_id, i.getStudent_id());
				}
			}
			
			session.removeAttribute("submissionsstudent");
			return "submissions_registration_success.jsp";
		}
	}