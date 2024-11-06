package mypage;

import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentNicknameUpdateAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String newNickname = request.getParameter("new_nickname");
		 String studentId=request.getParameter("student_id");
		 
		 AccountDAO dao=new AccountDAO();
		 
		 dao.stu_upd_nick(studentId,newNickname);
		 
		 request.setAttribute("successMessage", "ニックネームが更新されました。\n次回ログイン時に適応されます");
		 return "change_top.jsp";
	 }

}