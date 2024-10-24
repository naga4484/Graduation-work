package account;

import dao.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ResendVerificationCodeAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = (String) request.getSession().getAttribute("email");
        
        if (email != null) {
            AccountDAO dao = new AccountDAO();
            String newVerificationCode = generateVerificationCode();
            dao.store_verification_code(email, newVerificationCode);
            sendVerificationCodeByEmail(email, newVerificationCode);  // メール送信の実装
            request.setAttribute("successMessage", "確認コードを再送信しました。");
            return "reset_password_code.jsp";  // 確認コード入力ページに戻る
        } else {
            request.setAttribute("errorMessage", "メールアドレスが見つかりません。最初からやり直してください。");
            return "reset_password_email.jsp";  // エラーメッセージと共にメールアドレス入力ページに戻る
        }
    }

    // 確認コードを生成するメソッド（簡易な生成例）
    private String generateVerificationCode() {
        return String.valueOf((int)(Math.random() * 10000));  // 4桁の確認コード生成
    }

    // メールで確認コードを送信するメソッド（仮実装）
    private void sendVerificationCodeByEmail(String email, String verificationCode) {
        // メール送信のロジック（実際の実装は外部ライブラリを使う）
        System.out.println("確認コード " + verificationCode + " を " + email + " に再送信しました。");
    }
}
