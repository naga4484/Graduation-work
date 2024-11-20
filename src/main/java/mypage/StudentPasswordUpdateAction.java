package mypage;

import bean.Studentaccount;
import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentPasswordUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession();
    	String Password = request.getParameter("password");
    	String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");
        String studentId=request.getParameter("student_id");

        // パスワードのバリデーション: 既存のパスワード確認 > 形式の確認 > 確認パスワードの一致
        if (!Password.equals(oldPassword)) {
            request.setAttribute("errorMessage", "既存パスワードが一致しません。");
            return "upd_stu_pass.jsp";
        } else if (!isValidPassword(newPassword)) {
            request.setAttribute("errorMessage", "8字以上、数字、大文字、小文字をそれぞれ1文字以上使用してください。");
            return "upd_stu_pass_R.jsp";
        } else if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "確認パスワードが一致しません。");
            return "upd_stu_pass_R.jsp";
        }

        // パスワード更新処理
        AccountDAO dao = new AccountDAO();
        dao.update_password_stu(studentId, newPassword);
//        学生のパスワード更新処理はパスワードリセット機能を流用したためaddressを参照してます
        
        request.setAttribute("successMessage", "パスワードが更新されました。");
        Studentaccount account = dao.student_search_id(studentId);
//		 セッション内容を更新する
		 session.setAttribute("account", account);
        return "change_top.jsp";  // 更新後、ログインページに遷移
    }

    // パスワードのバリデーションメソッド
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && 
               password.matches(".*[A-Z].*") &&  // 大文字が含まれているか
               password.matches(".*[a-z].*") &&  // 小文字が含まれているか
               password.matches(".*[0-9].*");    // 数字が含まれているか
    }
}
