<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSSに関しては、機能ファルダごとにCSSを分けている -->
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
<link rel="stylesheet" type="text/css" href="../css/account.css">

</head>
<body>
	<div class="main_content">

		<title>学生ログイン画面</title>
		<div class="login">
			<h1>学生ログイン</h1>

			<form action="Studentlogin.action" method="post">
				<p>${login_error}
				<p>
				<p>学生ID：</p>
				<input type="text" required="required" name="student_id">
				<p>パスワード：</p>
				<input type="text" required="required" name="password">


				<button type="submit">ログイン</button>
			</form>
		</div>
	</div>
</body>
<%@include file="../footer.jsp"%>