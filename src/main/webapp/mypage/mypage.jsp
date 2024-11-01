<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>

<head>
    <meta charset="UTF-8">
    <title>My page</title>
</head>
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
    
    <br>
    <p>${account.name }さん。こんにちは</p>
		<a href="../account/Logout.action"><p>ログアウト</p></a>
</body>
<%@ include file="../footer.jsp" %>
