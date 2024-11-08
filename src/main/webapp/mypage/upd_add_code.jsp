<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<meta charset="UTF-8">
<title>email変更-確認コード入力</title>
</head>
<body>
	<div class="main_content">
		<div class="add_reset">
			<h1>メールアドレス変更</h1>
			<p>${successMessage}</p>
			<p>${errorMessage }</p>
			
			<from action="AddressCodeVerify.action"method="post">
				<p>確認コード:</p>
				<input type="text"name="verification_code"required="required">
				<br>
				<a href="ResendVerificationCode.action">確認コードを再送信</a><br>
			</from>
			<br><br>
		</div>
		<a href="change_top.jsp">戻る</a>
	</div>

</body>
<%@ include file="../footer.jsp"%>