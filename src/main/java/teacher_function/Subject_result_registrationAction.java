package teacher_function;

import java.util.List;

import bean.Result;
import bean.Teacheraccount;
import dao.ResultDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Subject_result_registrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			String subject_id=request.getParameter("subject_id");
			String comment=request.getParameter("comment");
			String select_con=request.getParameter("select_con");
			String evaluation=request.getParameter("evaluation");
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			String result_num=request.getParameter("result");
			if(result_num == "" ||result_num == null) {
				result_num = "0";
			}
			int result=Integer.parseInt(result_num);

			ResultDAO dao=new ResultDAO();
			List<Result> result_nums = dao.getSubject_result(select_con, subject_id);
			if(result_nums.size() > 0) {
				
				int line = dao.subject_result_update(select_con, subject_id, result, evaluation, comment);
				session.setAttribute("reg_mes", "学生ID「"+select_con +"」の成績情報の更新が完了しました");
				return "subject_result.jsp";
			}
			int line = dao.subject_result_registration(select_con, class_id, subject_id, result, evaluation, comment, account.getTeacher_id());
			session.setAttribute("reg_mes", "学生ID「"+select_con +"」の成績情報の登録が完了しました");
			return "subject_result.jsp";
		}
	}