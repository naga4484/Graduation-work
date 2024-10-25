<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>お問い合わせ</title>
    <link rel="stylesheet" type="text/css" href="../css/inquiry.css">
</head>
<body>
    <div class="main_content">
        <h1>お問い合わせ</h1>

        <!-- 送信完了メッセージを表示 -->
        <p style="color: green;">${message}</p>
        
        <!-- エラーメッセージを表示 -->
        <p style="color: red;">${errorMessage}</p>

        <form action="Inquiry.action" method="post">
            <h3>問い合わせ内容</h3>
            <label><input type="checkbox" name="inquiryType" value="不具合"> 不具合</label>
            <label><input type="checkbox" name="inquiryType" value="機能要望"> 機能要望</label>
            <label><input type="checkbox" name="inquiryType" value="その他"> その他</label>
            <br><br>
            
            <h3>質問詳細</h3>
            <textarea name="details" rows="5" cols="40" required="required"></textarea>
            <br><br>

            <button type="submit">送信</button>
        </form>

        <a href="login_top.jsp">戻る</a>
    </div>
</body>
</html>