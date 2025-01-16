<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワードリセット - 新しいパスワード設定</title>
<link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
	<div class="main_content">
		<div class="password_reset">
			<h1>パスワードリセット</h1>

			<form action="TchPasswordUpdate.action" method="post">
				<p>${errorMessage}</p>
				<!-- パスワードが条件を満たさない場合のエラーメッセージ表示 -->
				<p>新規パスワード：</p>
				<input type="password" name="new_password" required="required">
				<p>パスワードを再入力：</p>
				<input type="password" name="confirm_password" required="required">
				<br>
				<br>
				<button type="submit">送信</button>
			</form>

			<a href="" onclick="window.history.back(); return false;">戻る</a>
			<!-- 戻るリンク -->
		</div>
	</div>
</body>
</html>
