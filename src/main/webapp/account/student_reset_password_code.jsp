<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワードリセット - 確認コード入力</title>
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
<link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
	<div class="main_content">
		<div class="password_reset">
			<h1>パスワードリセット</h1>

			<!-- 再送信完了メッセージを表示  -->
			<p>${successMessage}</p>
			<p>${errorMessage}</p>
			<!-- 確認コードが正しくない場合のエラーメッセージ表示 -->

			<form action="StuPasswordCodeVerify.action" method="post">
				<p>確認コード：</p>
				<input type="text" name="verification_code" required="required">
				<br> <a href="ResendVerificationCode.action">確認コードを再送信</a> <br>
				<button type="submit">送信</button>
			</form>

			<!-- 確認コード再送信リンク -->
			<br> <br> <a href=""
				onclick="window.history.back(); return false;">戻る</a>
			<!-- 戻るリンク -->
		</div>
	</div>
</body>
</html>
