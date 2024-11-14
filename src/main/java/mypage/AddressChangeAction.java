package mypage;

//メアドから確認コードを送信するやつ
//メアド変更関連のjspに送信する。

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

public class AddressChangeAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");

        // デバッグログ追加
        System.out.println("Password reset requested for email: " + email);

        // メールアドレスの存在確認
        AccountDAO dao = new AccountDAO();
        boolean emailExists = dao.isEmailRegistered(email);

        if (emailExists) {
            // デバッグログ追加
            System.out.println("Email found: " + email);

            dao.delete_verification_code(email);
            String verificationCode = generateVerificationCode();
            dao.store_verification_code(email, verificationCode);

            request.getSession().setAttribute("verificationCode", verificationCode);
            request.getSession().setAttribute("email", email);

            sendVerificationCodeByEmail(email, verificationCode);

            request.setAttribute("successMessage", "確認コードを送信しました。");
            return "upd_add_code.jsp";
        } else {
            // デバッグログ追加
            System.out.println("Email not found: " + email);

            request.setAttribute("errorMessage", "存在しないアカウントです");
            return "error.jsp";
        }
    }

    // 6桁の確認コードを生成するメソッド
    private String generateVerificationCode() {
        return String.format("%06d", (int)(Math.random() * 1000000));  // 6桁の確認コード生成
    }

    // メールで確認コードを送信するメソッド
    private void sendVerificationCodeByEmail(String recipientEmail, String verificationCode) {
        final String username = "k62670044@gmail.com"; // Gmailアドレス
        final String password = "pcsi erxm cogb hxfa"; // Gmailパスワード

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS有効

        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("確認コードの送信");
            message.setText("確認コードはこちら: " + verificationCode);

            Transport.send(message);
            System.out.println("確認コード " + verificationCode + " を " + recipientEmail + " に送信しました。");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
