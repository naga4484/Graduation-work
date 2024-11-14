package mypage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class PasswordCodeVerifyAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションから確認コードを取得
        String sessionVerificationCode = (String) request.getSession().getAttribute("verificationCode");
     // ユーザー入力の確認コードを取得
        String userInputCode = request.getParameter("verification_code");
        // アカウントの種類を取得
        String accountKind = request.getParameter("kind");
        
     // セッション内の確認コードとユーザーの入力を比較
        if (sessionVerificationCode != null && sessionVerificationCode.equals(userInputCode)) {
            // アカウントの種類に基づいて遷移先を選択
            if ("学生".equals(accountKind)) {
                return "upd_stu_pass_R.jsp";  // 学生のアドレス変更ページ
            } else if ("教師".equals(accountKind)) {
                return "upd_tch_pass_R.jsp";  // 教師のアドレス変更ページ
            } else {
                request.setAttribute("errorMessage", "無効なアカウントの種類です。");
                return "error.jsp";  // エラー時のページ
            }
        } else {
            // 確認コードが一致しない場合
            request.setAttribute("errorMessage", "確認コードが正しくありません。");
            return "reset_pass_code.jsp";  // 確認コード入力ページ
        }
    }
    
}
