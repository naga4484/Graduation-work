<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<title>メールアドレス変更</title>
</head>

<body>
	<div class="main_content">
		<div class="update_add">
			<h1>メールアドレス変更</h1>
				<form actino="StudentAddressUpdate.action"method="post">
					<input type="hidden" name="student_id"id="student_id"value="${account.student_id }">
					
					<p>${errorMessage }</p>
					<label>新しいメールアドレスを入力</label>
					<input type="email" name="new_addres"required="required">
					<br><br>
					<label>新しいメールアドレスを確認</label>
					<input type="email" name="confirm_addres"required="required">
					<br><br>
					
					<button type="submit">変更</button>
				</form>
		</div>
	
	<a href="change_top.jsp">戻る</a>
	</div>

</body>
<%@ include file="../footer.jsp"%>