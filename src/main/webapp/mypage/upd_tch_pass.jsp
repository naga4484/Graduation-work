<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<title>パスワード変更</title>
</head>
<body>
	<div class="main_content">
		<div class="update_pass">
			<h1>パスワード変更</h1>

			<form action="TeacherPasswordUpdate.action" method="post">
				<input type="hidden" name="teacher_id"value="${account.teacher_id }">
					
					<p>${errorMessage}</p>
					<label>新しいパスワード</label>
					<input type="password"name="new_password" required="required">
					<br>
					<br>
					<label>新しいパスワードを確認</label>
					<input type="password"name="confirm_password"required="required">
				
				<br>
				<br>
				<button type="submit">変更</button>
			</form>

		</div>
		<br>
		<a href="change_top.jsp">戻る</a>
	</div>



</body>
<%@ include file="../footer.jsp"%>