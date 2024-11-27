package teacher_function;

import java.util.List;

import bean.Subject;
import bean.Teacheraccount;
import bean.User_id;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectregistrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			String subject_id=request.getParameter("subject_id");
			String subject_name=request.getParameter("subject_name");
			String total_unit_num = request.getParameter("total_unit");
			if(total_unit_num == "" ||total_unit_num == null) {
				total_unit_num = "0";
			}
			int total_unit=Integer.parseInt(total_unit_num);
			String class_id=request.getParameter("class_id");
			String subject_color=request.getParameter("subject_color");
			
			
			SubjectDAO no_dao=new SubjectDAO();
			List<Subject> s=no_dao.subject_search_no(subject_id);
			
			if (s.size() != 0) {
				request.setAttribute("subject_id_duplication_error", "科目IDが重複しています");
				request.setAttribute("entered_name", subject_name);
				request.setAttribute("entered_subject_id", subject_id);
				return "subject_registration.jsp";
			}

			SubjectDAO dao=new SubjectDAO();
			int line=dao.subject_registration(subject_id ,class_id, subject_name,total_unit,subject_color);
			// 入力の検証ロジック
			if (subject_id.length() > 5) {
			    request.setAttribute("errorMessage", "科目IDは5文字以下で入力してください。");
			    return "subject_registration.jsp"; // エラーページを返す、または同じページに戻る
			}
			if (subject_name.length() > 30) {
			    request.setAttribute("errorMessage", "科目名は30文字以下で入力してください。");
			    return "subject_registration.jsp"; // 入力ページに戻る
			}
			if(line>0) {
				Teacheraccount account = (Teacheraccount) session.getAttribute("account");
				List<Subject> class_subject = dao.getclasssubject(account.getClass_id());
				session.setAttribute("class_subject", class_subject);
				return "Subjectlisttop.action";
			}
			
			return "subject_list.jsp";
		}
	}