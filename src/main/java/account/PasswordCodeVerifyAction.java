package account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class PasswordCodeVerifyAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sessionVerificationCode = (String) request.getSession().getAttribute("verificationCode");
        String userInputCode = request.getParameter("verification_code");

        // セッション内の確認コードとユーザーの入力を比較
        if (sessionVerificationCode != null && sessionVerificationCode.equals(userInputCode)) {
            return "reset_password_form.jsp";  // 新しいパスワードを入力するページに遷移
        } else {
            request.setAttribute("errorMessage", "確認コードが正しくありません。");
            return "reset_password_code.jsp";  // 確認コード入力ページに戻る
        }
    }
}
