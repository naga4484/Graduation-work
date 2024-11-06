<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<title>ニックネーム変更</title>
</head>
<body>
	<div class="main_content">
		<div class="update_nickname">
			<h1>ニックネーム変更</h1>

			<form action="TeacherNicknameUpdate.action" method="post">
				<input type="hidden" name="teacher_id" id="teacher_id"
					value="${account.teacher_id }"> <input type="text"
					name="new_nickname" required="required">
				<p>※この情報は、「ちりつもサービス」内で公開されます</p>
				<p>メールアドレス、電話番号などの個人情報や</p>
				<p>個人の特定につながる情報は入力しないでください</p>

				<button type="submit">変更</button>
			</form>

		</div>
		<a href="change_top.jsp">戻る</a>
	</div>



</body>
<%@ include file="../footer.jsp"%>