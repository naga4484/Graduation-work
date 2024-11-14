package mypage;

//教師のメールアドレスの確認action


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
    	
    	 // 新しいメールアドレスが既に登録されているかを確認
    	if (dao.isEmailRegistered(newAddress)) {
            request.setAttribute("errorMessage", "このメールアドレスは既に使用されています");
            return "upd_tch_add.jsp";
        }
//    	メールアドレスを更新する処理
    	dao.tch_upd_add(teacherId, newAddress);
    	
    	request.setAttribute("successMessage", "メールアドレスが更新されました。");
    	Teacheraccount account = dao.teacher_search_id(teacherId);
//		 セッション内容を更新する
		 session.setAttribute("account", account);
       return "change_top.jsp";  // 更新後、ログインページに遷移
    }
}
