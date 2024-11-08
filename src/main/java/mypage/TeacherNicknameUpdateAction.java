package mypage;

import bean.Teacheraccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherNicknameUpdateAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session=request.getSession();
		 String newNickname = request.getParameter("new_nickname");
		 String teacherId=request.getParameter("teacher_id");
		 
		 AccountDAO dao=new AccountDAO();
		 
		 dao.tch_upd_nick(teacherId,newNickname);
		 
		 request.setAttribute("successMessage", "ニックネームが更新されました。");
		 
		 Teacheraccount account = dao.teacher_search_id(teacherId);
		 session.setAttribute("account", account);
		 return "change_top.jsp";
	 }

}
