package Mypage;

import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class NicknameUpdateAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
			String newNickname = request.getParameter("new_nickname");
			
			AccountDAO dao = new AccountDAO();
	}

}
