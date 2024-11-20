<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">


<head>
<meta charset="UTF-8">
<title>My page</title>
</head>
	<div class="main_content">
	<body>
	  <div class="mypage_top">
		<h1>マイページ</h1>
	  </div><br><br><br>
		<div class="mypage_status">
<table class="profile-table" border="1">
    <tr class="top-row">
        <c:choose>
            <c:when test="${account.account_kind == '教師'}">
                <td>教師ID</td>
            </c:when>
            <c:when test="${account.account_kind == '学生'}">
                <td>学生ID</td>
            </c:when>
        </c:choose>
        <td>名前</td>
        <td>クラス</td>
        <td>ニックネーム</td>
        <td>メールアドレス</td>
    </tr>
    <tr class="bottom-row">
        <c:choose>
            <c:when test="${account.account_kind == '教師'}">
                <td>${account.teacher_id}</td>
            </c:when>
            <c:when test="${account.account_kind == '学生'}">
                <td>${account.student_id}</td>
            </c:when>
        </c:choose>
        <td>${account.name}</td>
        <td>${account.class_id}</td>
        <td>${account.nickname}</td>
        <td>${account.address}</td>
    </tr>
</table>

<!-- アドレス情報の隠しフィールド -->
<input type="hidden" name="address" value="${account.address}">

		</table>
	</div>
			<div class="button-link">
			    <a href="change_top.jsp">
				<button type="button">マイページを編集</button></a>
				<a href="../account/Logout.action"><button class="logout-button">ログアウト</button></a> 		        
			</div> 
			<div class="back_button">
               <a href="../common/top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a>
            </div> 
		</div>
	</body>

<%@ include file="../footer.jsp"%>
