package mypage;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class AddressResendAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = (String) request.getSession().getAttribute("email");

        if (email != null) {
            // 新しい6桁の確認コードを生成してセッションに保存 
            String newVerificationCode = generateVerificationCode();
            request.getSession().setAttribute("verificationCode", newVerificationCode);
            
            // 確認コードをメールで再送信
            sendVerificationCodeByEmail(email, newVerificationCode);

            // 再送信完了メッセージを設定（画面遷移せずにメッセージを表示）
            request.setAttribute("successMessage", "確認コードを再送信しました。");
            return "upd_add_code.jsp";  // 確認コード入力ページのままにする
        } else {
            request.setAttribute("errorMessage", "メールアドレスが見つかりません。最初からやり直してください。");
            return "reset_password_email.jsp";  // エラーメッセージと共にメールアドレス入力ページに戻る
        }
    }

    // 6桁の確認コードを生成するメソッド
    private String generateVerificationCode() {
        return String.format("%06d", (int)(Math.random() * 1000000));  // 6桁の確認コード生成
    }

    // メールで確認コードを送信するメソッド
    private void sendVerificationCodeByEmail(String recipientEmail, String verificationCode) {
        // GmailのSMTPサーバー設定
        final String username = "k62670044@gmail.com"; // 送信元のGmailアドレス
        final String password = "pcsi erxm cogb hxfa"; // 送信元のGmailパスワード（セキュリティ上の理由からは環境変数などで管理するのが望ましい）

        // SMTPサーバー設定プロパティ
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLSを有効にする

        // 認証とセッションの作成
        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // メールの内容を作成
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // 送信元アドレス
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail) // 受信者アドレス
            );
            message.setSubject("確認コードの再送信");
            message.setText("再送信された確認コードはこちら: " + verificationCode);

            // メールを送信
            Transport.send(message);

            System.out.println("確認コード " + verificationCode + " を " + recipientEmail + " に再送信しました。");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
