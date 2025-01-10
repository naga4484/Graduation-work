package teacher_function;

import java.io.File;
import java.util.List;

import bean.Studentaccount;
import bean.Submissions;
import bean.User_id;
import dao.SubmissionsDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubmissionsregistrationAction extends Action {
	
	private static final String FILE_DIRECTORY = "submissions/";
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}
			
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
			if(month.length() == 1) {
				month = "0" + month;
			}
			String date=request.getParameter("date");
			if(date.length() == 1) {
				date = "0" + date;
			}
			String fulldata = year + "年" +month + "月" + date + "日";
			String save_path = FILE_DIRECTORY + submission_name;
			
			String folderpath = request.getServletContext().getRealPath("") + File.separator + "submissions";
			File folDir = new File(folderpath);
	        if (!folDir.exists()) {
	        	folDir.mkdir();
	        }
			
			String submissionsPath = request.getServletContext().getRealPath("") + File.separator + save_path;
			// 保存ディレクトリが存在しない場合は作成
	        File subDir = new File(submissionsPath);
	        if (!subDir.exists()) {
	        	subDir.mkdir();
	        }
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
					int line = sdao.submissions_alignment(submissions_id, i.getStudent_id(),false);
				}
			}
			
			session.removeAttribute("submissionsstudent");
			return "submissions_registration_success.jsp";
		}
	}