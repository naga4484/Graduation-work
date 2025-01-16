<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワードリセット</title>
<link rel="stylesheet" type="text/css" href="../css/account.css">
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
</head>
<body>
    <div class="main_content">
        <div class="password_reset">
            <h1>パスワードリセット</h1>

            <form action="PasswordReset.action" method="post">
                <p>${errorMessage}</p>
                <p>メールアドレス：</p>
                <input type="email" name="email" required="required"> <br><br>
                <button type="submit">送信</button>
            </form>

            <a href="" onclick="window.history.back(); return false;">戻る</a>
        </div>
    </div>
</body>
</html>
