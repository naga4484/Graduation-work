<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/group.css">


<title>チャット画面</title>
<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1 class="page_title">チャット</h1>

<div class="chat_left_content">
	<c:forEach var="item" items="${my_group_list}">
		<div class="chat_partner_select">
			<h4><a href="Group_chat_datail.action?group_id=${item.group_id}">${item.group_name}</a></h4>
		</div>
	</c:forEach>
</div>
<c:if test="${chat_list != null}">

	<iframe src="group_chat_inner.jsp" id="chat_iframe"></iframe>
	<!-- チャット送信欄 -->
	<div class="chat_submit_area">
		<form action="Chat_submit.action">
			<input type="text" name="chat_content" placeholder="メッセ―ジを入力" id="chat_content_area">
			<input type="hidden" name="group_id" value="${select_id}">
			<input type="submit" value="送信" id="chat_submit_area">
		</form>
	</div>
</c:if>
<div class="common_back_button">
	<a href="../group/group_top.jsp"><img src="../images/戻るボタン1.png"></a>
</div>
<%@include file="../footer.jsp"  %>