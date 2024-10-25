package inquiry;

import java.util.Properties;

import dao.AccountDAO;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class InquiryAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ログインしているユーザーの情報をセッションから取得
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId"); // ユーザーID
        String accountKind = (String) session.getAttribute("accountKind"); // 学生か教師の情報
        String senderName = (String) session.getAttribute("userName"); // ユーザー名

        // デバッグ用にセッション情報をログ出力
        System.out.println("User ID: " + userId);
        System.out.println("Account Kind: " + accountKind);
        System.out.println("User Name: " + senderName);

        // データベースからメールアドレスを取得
        AccountDAO dao = new AccountDAO();
        String senderEmail = dao.getEmailByUserId(userId, accountKind);

        // デバッグ用に取得したメールアドレスをログ出力
        System.out.println("Sender Email: " + senderEmail);

        // バリデーション: ユーザー情報が正しくない場合
        if ((userId == null || userId.trim().isEmpty()) || (accountKind == null || accountKind.trim().isEmpty())) {
            request.setAttribute("errorMessage", "送信者情報が無効です。もう一度ログインしてください。");
            return "inquiry_form.jsp";
        }

        // フォームからの問い合わせ内容を取得
        String[] inquiryTypes = request.getParameterValues("inquiryType");
        String details = request.getParameter("details");

        // バリデーション: 問い合わせ項目が選択されていない、または質問詳細が未入力の場合
        if ((inquiryTypes == null || inquiryTypes.length == 0) || (details == null || details.trim().isEmpty())) {
            request.setAttribute("errorMessage", "問い合わせ内容と質問詳細は必須です。");
            return "inquiry_form.jsp";
        }

        // メールアドレスが取得できていない場合
        if (senderEmail == null || senderEmail.trim().isEmpty()) {
            request.setAttribute("errorMessage", "送信者のメールアドレスが取得できませんでした。もう一度ログインしてください。");
            return "inquiry_form.jsp";
        }

        // メール内容を構成
        StringBuilder inquiryContent = new StringBuilder();
        inquiryContent.append("送信者: ").append(senderName).append("\n");
        inquiryContent.append("送信者のメール: ").append(senderEmail).append("\n\n");
        inquiryContent.append("問い合わせ内容: \n");

        for (String type : inquiryTypes) {
            inquiryContent.append("- ").append(type).append("\n");
        }
        inquiryContent.append("\n質問詳細:\n").append(details);

        // メール送信処理
        sendEmail(inquiryContent.toString());

        // メッセージを問い合わせページに表示
        request.setAttribute("message", "送信が完了しました。");

        return "inquiry_form.jsp";
    }

    // メール送信メソッド
    private void sendEmail(String content) throws MessagingException {
        // SMTPサーバー設定
        final String fromEmail = "k62670044@gmail.com"; // 固定送信者メールアドレス
        final String toEmail = "tianzhongcong92@gmail.com"; // 固定受信者メールアドレス
        final String username = "k62670044@gmail.com"; // Gmailアドレス
        final String password = "pcsi erxm cogb hxfa"; // アプリパスワード

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLSを有効にする

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail)); // 送信者アドレス（固定）
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // 受信者アドレス（固定）
        message.setSubject("お問い合わせ内容");
        message.setText(content);

        Transport.send(message);
    }
}
