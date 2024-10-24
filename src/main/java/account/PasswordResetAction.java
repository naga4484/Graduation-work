package account;

import java.util.Properties;

import dao.AccountDAO;
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

public class PasswordResetAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");

        // メールアドレスの存在確認
        AccountDAO dao = new AccountDAO();
        boolean emailExists = dao.isEmailRegistered(email);
        
        if (emailExists) {
            // 6桁の確認コードを生成してセッションに保存
            String verificationCode = generateVerificationCode();
            request.getSession().setAttribute("verificationCode", verificationCode);
            request.getSession().setAttribute("email", email);  // メールアドレスもセッションに保存
            
            // 確認コードをメールで送信
            sendVerificationCodeByEmail(email, verificationCode);

            request.setAttribute("successMessage", "確認コードを送信しました。");
            return "reset_password_code.jsp";  // 確認コード入力ページに遷移
        } else {
            request.setAttribute("errorMessage", "登録されていないメールアドレスです。");
            return "reset_password_email.jsp";  // エラーメッセージと共に元のページに戻る
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
        final String password = "pcsi erxm cogb hxfa"; // 送信元のGmailパスワード

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
            message.setSubject("確認コードの送信");
            message.setText("確認コードはこちら: " + verificationCode);

            // メールを送信
            Transport.send(message);

            System.out.println("確認コード " + verificationCode + " を " + recipientEmail + " に送信しました。");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
