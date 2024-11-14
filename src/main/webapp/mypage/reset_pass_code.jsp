<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<meta charset="UTF-8">
<title>password変更-確認コード入力</title>
</head>
<body>
    <div class="main_content">
        <div class="pass_reset">
            <h1>パスワード変更</h1>
            <p>${successMessage}</p>
            <p>${errorMessage}</p>
            
            <form action="PasswordCodeVerify.action" method="post">
                <input type="hidden" name="kind" value="${account.account_kind }">
                <p>確認コードを入力してください</p>
                <p>確認コード:</p>
                <input type="text" name="verification_code" required="required">
                <br>
                <a href="PasswordResend.action">確認コードを再送信</a><br>
                <button type="submit">送信</button>
            </form>
            <br><br>
        </div>
        <a href="change_top.jsp">戻る</a>
    </div>
</body>
<%@ include file="../footer.jsp"%>
