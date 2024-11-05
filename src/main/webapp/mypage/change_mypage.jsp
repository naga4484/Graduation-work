<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<h1>マイページ編集</h1>



<title>マイページ編集</title>
</head>
<body>

	<table border="1">

		<tr>
			<td><a href="change_nickname.jsp" class="button-link"><button type="button">ニックネーム変更</button></a></td>
			<td>${account.nickname}</td>
		</tr>
		<tr>
			<td><a href="change_address.jsp" class="button-link"><button type="button">メールアドレス変更</button></a></td>
			<td>${account.address}</td>
		</tr>
		
		<tr>
			<td><a href="change_pass.jsp" class="button-link"><button type="button">パスワード変更</button></a></td>
			<td>●●●●●●</td>

	</table>
	
	<a href="mypage.jsp">戻る</a>

</body>
<%@ include file="../footer.jsp"%>