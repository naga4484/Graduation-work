package mypage;

import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherNicknameUpdateAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String newNickname = request.getParameter("new_nickname");
		 String teacherId=request.getParameter("teacher_id");
		 
		 AccountDAO dao=new AccountDAO();
		 
		 dao.tch_upd_nick(teacherId,newNickname);
		 
		 request.setAttribute("successMessage", "ニックネームが更新されました。"
		 		+ "次回ログイン時に適応されます");
		 return "change_top.jsp";
	 }

}
