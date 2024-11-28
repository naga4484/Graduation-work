<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/group.css">

<title>グループ管理画面</title>

<h1 class="page_title">グループ管理</h1>

<div>
	<c:if test="${comp_mes != null}">
		<p>${comp_mes}</p>
	</c:if>
	<c:if test="${no_par_error != null}">
		<p>${no_par_error}</p>
	</c:if>
	<div class="group_icons">
		<div class="group_icon_links1">
			<a href="#"><img src="../images/共有アイコン.png"></a>
			<p>共有(未着手)</p>
		</div>
		<div class="group_icon_links2">
			<a href="Group_chat.action"><img src="../images/チャットアイコン.png"></a>
			<p>チャット</p>
		</div>
		<div class="group_icon_links3">
			<a href="group_create_participation.jsp"><img src="../images/グループアイコン.png"></a>
			<p>グループ作成・参加</p>
		</div>
	</div>
</div>


<div class="common_back_button">
  <a href="../common/top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>