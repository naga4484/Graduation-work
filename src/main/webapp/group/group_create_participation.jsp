<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<title>グループ作成参加画面</title>

<h1>グループ作成参加</h1>

<div>
	<c:if test="${dis_cre_error != null}">
		<p>${dis_cre_error}</p>
	</c:if>
	<h2>新規作成</h2>
	<form action="Group_create.action">
		<input type="text" name="group_name" placeholder="グループ名を入力してください" maxlength="20" required>
		<input type="text" name="group_id" placeholder="グループIDを入力してください" maxlength="5" required>
		<input type="submit" value="作成"> 
	</form>
</div>
<div>
	<c:if test="${dis_par_error != null}">
		<p>${dis_par_error}</p>
	</c:if>
	<c:if test="${none_error != null}">
		<p>${none_error}</p>
	</c:if>
	<h2>新規参加</h2>
	<form action="Group_participation.action">
		<input type="text" name="group_id" placeholder="グループ名を入力してください" maxlength="5" required>
		<input type="submit" value="参加"> 
	</form>
</div>


<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>