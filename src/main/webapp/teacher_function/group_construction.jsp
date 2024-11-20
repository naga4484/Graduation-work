<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>グループ表示画面</title>

<h1>グループ機能</h1>

<div>
	<c:forEach var="entry" items="${group_list}" varStatus="count">
		<p>グループ${count.index + 1}</p>
		<c:forEach var="item" items="${entry.value}">
			<p>${item.name}</p>
		</c:forEach>
	</c:forEach>
</div>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>