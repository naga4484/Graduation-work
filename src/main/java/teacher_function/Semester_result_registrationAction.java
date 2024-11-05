package teacher_function;

import java.util.List;

import bean.Class_num;
import bean.Result;
import bean.Teacheraccount;
import dao.ClassDAO;
import dao.ResultDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Semester_result_registrationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String class_id=request.getParameter("class_id");
			String semester=request.getParameter("semester");
			String comment=request.getParameter("comment");
			String select_con=request.getParameter("select_con");
			String evaluation=request.getParameter("evaluation");
			Teacheraccount account = (Teacheraccount)session.getAttribute("account");
			String result_num=request.getParameter("result");
			if(result_num == "" ||result_num == null) {
				result_num = "0";
			}
			int result=Integer.parseInt(result_num);
			ClassDAO cdao=new ClassDAO();
			Class_num class_num = cdao.get_class(class_id);

			ResultDAO dao=new ResultDAO();
			List<Result> result_nums = dao.getSemester_result(select_con, semester, class_num.getGrade());
			if(result_nums.size() > 0) {
				dao.semester_result_update(select_con, semester, result, evaluation, comment,class_num.getGrade());
				session.setAttribute("reg_mes", "学生ID「"+select_con +"」の成績情報の更新が完了しました");
				return "semester_result.jsp";
			}
			int line = dao.semester_result_registration(select_con, class_id, semester, result, evaluation, comment, account.getTeacher_id(),class_num.getGrade());
			session.setAttribute("reg_mes", "学生ID「"+select_con +"」の成績情報の登録が完了しました");
			return "semester_result.jsp";
		}
	}