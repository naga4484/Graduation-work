<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<head>
<title>パスワード変更処理</title>
</head>
<body>
	<div class="main_content">
		<div class="update_pass">
			<h1>パスワードリセット</h1>
	
				<form action="PasswordReset.Acction">
					<label>既存のメールアドレスを入力</label>
					<input type="email" name="email"required="required">
					<br><br>
					<button type="submit">送信</button>
				</form>
		</div>
		<a href="#" onclick="history.back()">もどる</a>
	</div>

</body>
<script src="../js/mypage.js"></script>
<%@ include file="../footer.jsp"%>