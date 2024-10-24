<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
	<div class="main_content">

		<title>パスワードリセット</title>
		<div class="password_reset">
			<h1>パスワードリセット</h1>

			<form action="PasswordReset.action" method="post">
				<p>${errorMessage}</p>
				<!-- メールアドレスが登録されていない場合のエラーメッセージ表示 -->
				<p>メールアドレス：</p>
				<input type="email" name="email" required="required"> <br>
				<br>
				<button type="submit">送信</button>
			</form>


			<a href="login_top.jsp">戻る</a>
			<!-- 戻るリンク -->
		</div>
	</div>
</body>
</html>
