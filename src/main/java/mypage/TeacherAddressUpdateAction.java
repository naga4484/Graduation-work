package mypage;


import bean.Teacheraccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherAddressUpdateAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession();
    	String teacherId = request.getParameter("teacher_id");
    	String newAddress = request.getParameter("new_address");
    	String confirmAddress = request.getParameter("confirm_address");

//    	メールアドレスのチェック
    	if(!newAddress.equals(confirmAddress)) {
    		request.setAttribute("errorMessage","メールアドレスが一致しません");
    		return "upd_tch_add.jsp";
    	}
    	
    	AccountDAO dao =new AccountDAO();
    	dao.tch_upd_add(teacherId, newAddress);
    	
    	request.setAttribute("successMessage", "メールアドレスが更新されました。");
    	Teacheraccount account = dao.teacher_search_id(teacherId);
//		 セッション内容を更新する
		 session.setAttribute("account", account);
       return "change_top.jsp";  // 更新後、ログインページに遷移
    }
}
