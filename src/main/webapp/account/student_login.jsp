<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生ログイン画面</title>
    <!-- CSSに関しては、機能ファルダごとにCSSを分けている -->
    <link rel="stylesheet" type="text/css" href="../css/top.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/teacher.css">
    <link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
<div class="main_content">
    <div class="login">
        <h1>学生ログイン</h1>
        <form action="Studentlogin.action" method="post">
            <p>${login_error}</p> <!-- タグの閉じ忘れを修正 -->
            <p>学生ID：</p>
            <input type="text" required="required" name="student_id">
            <p>パスワード：</p>
            <input type="password" required="required" name="password" id="password">
            <label for="showPassword">
                <input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
                パスワードを表示する
            </label>
            <a href="reset_password_email.jsp">パスワードリセットを行う</a>
            <button type="submit">ログイン</button>
        </form>
    </div>
</div>
<script>
function togglePasswordVisibility() {
    let passwordInput = document.getElementById("password");
    let showPasswordCheckbox = document.getElementById("showPassword");
    if (showPasswordCheckbox.checked) {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}
</script>
<%@ include file="../footer.jsp" %>
</body>
</html>
