<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">

<head>
    <title>マイページ編集</title>
</head>
<body>
	  <div class="mypage_top">
       <h1>マイページ編集</h1>
	  </div><br><br><br><br>
 
 <table class="profile-table" border="1">
    <tr class="top-row">
<c:choose>
    <c:when test="${account.account_kind == '教師'}">
        <td><a href="upd_tch_nick.jsp" class="button-link">ニックネーム変更</a></td>
        <td>
            <form action="AddressChange.action" method="post">
                <input type="hidden" name="email" id="email" value="${account.address}">
                <a href="upd_tch_add.jsp" class="button-link">メールアドレス変更</button>
            </form>
        </td>
        <td><a href="upd_tch_pass.jsp" class="button-link">パスワード変更</a></td>
    </c:when>
    <c:when test="${account.account_kind == '学生'}">
        <td><a href="upd_stu_nick.jsp" class="button-link">ニックネーム変更</a></td>
        <td>
            <form action="AddressChange.action" method="post">
                <input type="hidden" name="email" id="email" value="${account.address}">
                <a href="upd_stu_add.jsp" class="button-link">メールアドレス変更</button>
            </form>
        </td>
        <td><a href="upd_stu_pass.jsp" class="button-link">パスワード変更</a></td>
    </c:when>
</c:choose>

    <tr class="bottom-row">
        <c:choose>
            <c:when test="${account.account_kind == '教師'}">
            </c:when>
            <c:when test="${account.account_kind == '学生'}">
            </c:when>
        </c:choose>
        <td>${account.nickname}</td>
        <td>${account.address}</td>
        <td>＊＊＊＊＊＊＊</td>
    </tr>


      
    </tr>
</table>

<div class="common_back_button">
<a href="mypage_top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
</div>
</body>
<%@ include file="../footer.jsp" %>
