<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<head>
<meta charset="UTF-8">
<title>My page</title>
</head>
<div class="mypage_top">
	<body>
		<h1>マイページ</h1>
		<c:choose>
			<c:when test="${account.account_kind == '教師'}">
				<p>教師アカウントです</p>
			</c:when>
			<c:when test="${account.account_kind == '学生'}">
				<p>学生アカウントです</p>
			</c:when>
			<c:otherwise>
				<p>ユーザータイプが不明です。</p>
			</c:otherwise>
		</c:choose>

		<div class="mypage_status">
			<table border="1">

				<c:choose>
					<c:when test="${account.account_kind == '教師'}">
						<tr>
							<td>教師ID</td>
							<td>${account.teacher_id}</td>
						</tr>
					</c:when>
					<c:when test="${account.account_kind == '学生'}">
						<tr>
							<td>学生ID</td>
							<td>${account.student_id}</td>
						</tr>
					</c:when>
				</c:choose>
				<tr>
					<td>名前</td>
					<td>${account.name}</td>
				</tr>
				<tr>
					<td>クラス</td>
					<td>${account.class_id}</td>
				</tr>
				<tr>
					<td>ニックネーム</td>
					<td>${account.nickname}</td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td>${account.address}</td>
					<!-- ↓アドレス情報の格納 -->
					<input type="hidden" name="address" value="${account.address}">
				</tr>
				
			</table>
			<a href="change_top.jsp" class="button-link">
				<button type="button">マイページを編集</button>
			</a> <br> <a href="../account/Logout.action"><p>ログアウト</p></a> <br>
			<a href="../common/top.jsp">戻る</a>
		</div>
	</body>
</div>
<%@ include file="../footer.jsp"%>
