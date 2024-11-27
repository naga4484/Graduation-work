package account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Error_functionAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();		
			
			request.setAttribute("error_mes", "ログイン状態が切れています。再ログインしてください。");
			return "../account/error_function.jsp";
		}
	}