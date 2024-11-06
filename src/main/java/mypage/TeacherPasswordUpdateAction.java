package mypage;

import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherPasswordUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");
        String teacherId=request.getParameter("teacher_id");

        // パスワードのバリデーション
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "パスワードが一致しません。");
            return "upd_tch_pass.jsp";
        } else if (!isValidPassword(newPassword)) {
            request.setAttribute("errorMessage", "8字以上、数字、大文字、小文字をそれぞれ1文字以上使用してください。");
            return "upd_tch_pass.jsp";
        }

        // パスワード更新処理
        AccountDAO dao = new AccountDAO();
        dao.update_password_tch(teacherId, newPassword);
        
        request.setAttribute("successMessage", "パスワードが更新されました。");
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
