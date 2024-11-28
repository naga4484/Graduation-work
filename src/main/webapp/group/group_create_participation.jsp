<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/group.css">

<title>グループ作成参加画面</title>
<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1 class="page_title">グループ作成参加</h1>

<div class="group_new_create">
	<h2>新規作成</h2>
	<c:if test="${dis_cre_error != null}">
		<p class="group_mes">${dis_cre_error}</p>
	</c:if>
	<form action="Group_create.action">
		<label class="group_create_text_label">グループ名</label>
		<input type="text" name="group_name" placeholder="グループ名を入力してください" maxlength="20" required id="group_create_text">
		<label class="group_create_text_label">グループID</label>
		<input type="text" name="group_id" placeholder="グループIDを入力してください" maxlength="5" required id="group_create_text">
		<input type="submit" value="作成" id="group_create_button"> 
	</form>
</div>
<div class="group_new_par">
	<h2>新規参加</h2>
	<c:if test="${dis_par_error != null}">
		<p class="group_mes">${dis_par_error}</p>
	</c:if>
	<c:if test="${none_error != null}">
		<p class="group_mes">${none_error}</p>
	</c:if>
	<form action="Group_participation.action">
		<label class="group_create_text_label">グループID</label>
		<input type="text" name="group_id" placeholder="グループIDを入力してください" maxlength="5" required id="group_create_text">
		<input type="submit" value="参加" id="group_create_button"> 
	</form>
</div>


<div class="common_back_button">
  <a href="../group/group_top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>