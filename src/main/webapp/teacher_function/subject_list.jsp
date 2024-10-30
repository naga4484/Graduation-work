<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>科目情報一覧画面</title>

<h1>科目情報一覧機能</h1>
<c:if test="${delete_mes != null}">
	<p>${delete_mes}</p>
</c:if>
<c:if test="${change_mes != null}">
	<p>${change_mes}</p>
</c:if>
<c:if test="${none_mes != null}">
	<p>${none_mes}</p>
</c:if>
<a href="subject_registration.jsp">新規登録</a>
<form action="Subjectchangedata.action">
	<table>
		<tr>
			<th>科目ID</th>
			<th>科目名</th>
			<th>総単位数</th>
			<th>クラスID</th>
			<th>科目色</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${subject_list}">
	  		<tr>
	  			<td id="subject_id">${item.subject_id}</td>
	  			<td><input type="text" value="${item.subject_name}" name="${item.subject_id}_subject_name"></td>
	  			<td><input type="text" value="${item.total_unit}" name="${item.subject_id}_total_unit"></td>
	  			<td>${item.class_id}</td>
	  			<td><input type="color" name="subject_color" value="${item.subject_color}"></td>
	  			<td><input type="button" id="button${item.subject_id}" value="削除" name="${item.subject_id}"></td>
	  		</tr>
		</c:forEach>
	</table>
	<input type="submit" value="変更" id="subject_change_button">
</form>


<div id="delete_box">
</div>


<script src="../js/subject.js"></script>
<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>