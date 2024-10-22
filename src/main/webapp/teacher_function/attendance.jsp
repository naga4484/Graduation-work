<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>出欠管理画面</title>

<h1>出欠管理機能</h1>

<form action="Attendancesearch.action">
	
	<c:if test="${none_error != null}">
	<p>${none_error}</p>
	</c:if>
	<label>学生</label>
	<input type="text" name="student_name" placeholder="名前を入力してください">
	
	<label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select>
	
	<input type="submit" value="検索">
</form>
<form  action="Attendanceregistration.action">
	<table>
		<tr>
			<th>出欠可否</th>
			<th>学生ID</th>
			<th>学生氏名</th>
		</tr>
		<c:forEach var="item" items="${attendancsstudentlist}">
	  		<tr>
	  			<td><input type="checkbox" name="${item.student_id}" value="出席" checked></td>
	  			<td>${item.student_id}</td>
	  			<td>${item.name}</td>
	  		</tr>
		</c:forEach>
	</table>
	<input type="submit" value="登録">
</form>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>