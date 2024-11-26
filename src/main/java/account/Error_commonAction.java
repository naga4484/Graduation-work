package account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Error_commonAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			String error_mes = (String)session.getAttribute("error_mes");
			
			if(error_mes == null) {
				error_mes = "ログイン状態が切れています。再度ログインしてください";
				return "Logout.action";
			}
			
			request.setAttribute("error_mes", error_mes);
			return "error_common.jsp";
		}
	}