<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<title>グループ管理画面</title>

<h1>グループ管理</h1>

<div>
	<c:if test="${comp_mes != null}">
		<p>${comp_mes}</p>
	</c:if>
	<c:if test="${no_par_error != null}">
		<p>${no_par_error}</p>
	</c:if>
	<a href="#">共有(未着手)</a>
	<a href="Group_chat.action">チャット</a>
	<a href="group_create_participation.jsp">グループ作成・参加</a>
</div>


<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>