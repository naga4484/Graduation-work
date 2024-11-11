<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<h1>マイページ編集</h1>



<title>マイページ編集</title>
</head>
<body>

	<table border="1">
		<p>${successMessage}</p>
		<br>
		<c:choose>
			<c:when test="${account.account_kind == '教師'}">


				<tr>
					<td><a href="upd_tch_nick.jsp" class="button-link"><button
								type="submit">ニックネーム変更</button></a></td>
					<td>${account.nickname}</td>
				</tr>
				<tr>
					<form action="AddressChange.action" method="post">
						<input type="hidden" name="email" id="email"
							value="${account.address }">
					<td><a href="" class="button-link"><button type="submit">メールアドレス変更</button></a></td>
					</form>
					<td>${account.address}</td>
				</tr>

				<tr>
					<td><a href="upd_tch_pass.jsp" class="button-link"><button
								type="submit">パスワード変更</button></a></td>
					<td>＊＊＊＊＊＊＊</td>
				</tr>

			</c:when>


			<c:when test="${account.account_kind == '学生'}">


				<input type="hidden" name="student_id" id="student_id"
					value="${account.student_id }">
				<tr>
					<td><a href="upd_stu_nick.jsp" class="button-link"><button
								type="submit">ニックネーム変更</button></a></td>
					<td>${account.nickname}</td>
				</tr>
				<tr>
					<form action="AddressChange.action" method="post">
						<input type="hidden" name="email" id="email"
							value="${account.address }">
					<td><a href="" class="button-link">
							<button type="submit">メールアドレス変更</button>
					</a></td>
					</form>
					<td>${account.address}</td>
				</tr>

				<tr>
					<td><a href="upd_stu_pass.jsp" class="button-link"><button
								type="submit">パスワード変更</button></a></td>
					<td>＊＊＊＊＊＊＊＊</td>
				</tr>

			</c:when>

		</c:choose>
	</table>

	<a href="mypage_top.jsp">戻る</a>

</body>
<%@ include file="../footer.jsp"%>