package mypage;

import bean.Studentaccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentAddressUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession();
    	String studentId = request.getParameter("student_id");
    	String newAddress = request.getParameter("new_address");
    	String confirmAddress = request.getParameter("confirm_address");
    
    	
//    	メールアドレスのチェック
    	if(!newAddress.equals(confirmAddress)) {
    		request.setAttribute("errorMessage","メールアドレスが一致しません");
    		return "upd_stu_add.jsp";
    	}
    	
    	AccountDAO dao =new AccountDAO();
    	
    	 // 新しいメールアドレスが既に登録されているかを確認
    	if (dao.isEmailRegistered(newAddress)) {
            request.setAttribute("errorMessage", "このメールアドレスは既に使用されています");
            return "upd_stu_add.jsp";
        }
    	dao.stu_upd_add(studentId, newAddress);
    	
    	request.setAttribute("successMessage", "メールアドレスが更新されました。");
    	Studentaccount account = dao.student_search_id(studentId);
//		 セッション内容を更新する
		 session.setAttribute("account", account);
       return "change_top.jsp";  // 更新後、ログインページに遷移
    }

}
