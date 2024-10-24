<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>提出物確認画面</title>

<h1>提出物確認機能</h1>

<form action="Submissions_class.action">
	<label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select>
	
	<input type="submit" value="検索">
</form>
<c:if test="${none_error != null}">
	<p>${none_error}</p>
</c:if>

<c:if test="${change_mes != null}">
	<p>${change_mes}</p>
</c:if>
<c:if test="${delete_mes != null}">
	<p>${delete_mes}</p>
</c:if>
<c:if test="${distinctsubmissions_class != null}">
	<table>
		<tr>
			<th>No</th>
			<th>提出物ID</th>
			<th>提出物名</th>
		</tr>
		<tr>
			<c:forEach var="item" items="${distinctsubmissions_class}" varStatus="status">
			    <td>${status.index + 1}</td>
			    <td><a href="Submissions_detail.action?submissions_id=${item.submissions_id}">${item.submissions_id}</a></td>
			    <td><a href="Submissions_detail.action?submissions_id=${item.submissions_id}">${item.name}</a></td>
			</c:forEach>
		</tr>
	</table>
</c:if>

<a href="../common/top.jsp">TOP</a>
<script src="../js/year.js"></script>
<%@include file="../footer.jsp"  %>