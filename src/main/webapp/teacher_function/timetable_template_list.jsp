<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>時間割テンプレート画面</title>

<h1>時間割テンプレート機能</h1>

<a href="../teacher_function/timetable_template.jsp">テンプレート新規作成</a>

<table>
	<tr>
		<th>テンプレートID</th>
		<th>テンプレート名</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach var="item_timetable" items="${timetablelist}">
		<tr>
			<td>${item_timetable.template_id}</td>
			<td>${item_timetable.template_name}</td>
			<td><a href="Timetable_template_update.action?template_id=${item_timetable.template_id}">編集のリンク</a></td>
			<td><a href="Timetable_template_delete.action?template_id=${item_timetable.template_id}">削除のリンク</a></td>
		</tr>
	</c:forEach>
</table>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>