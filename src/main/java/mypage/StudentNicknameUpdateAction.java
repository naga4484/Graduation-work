package mypage;

import bean.Studentaccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentNicknameUpdateAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session=request.getSession();
		 String newNickname = request.getParameter("new_nickname");
		 String studentId=request.getParameter("student_id");
		 
		 
		 AccountDAO dao=new AccountDAO();
		 
		 dao.stu_upd_nick(studentId,newNickname);
//		 成功時のメッセージ
		 request.setAttribute("successMessage", "ニックネームが更新されました。");

		 Studentaccount account = dao.student_search_id(studentId);
//		 セッション内容を更新する
		 session.setAttribute("account", account);
		 return "change_top.jsp";
	 }

}