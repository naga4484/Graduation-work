<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>スケジュール変更画面</title>

<h1>スケジュール変更</h1>
<div>
	<p>${selectedDate}</p>
</div>
<c:if test="${dis_error != null}">
	<p>${dis_error}</p>
</c:if>
<c:if test="${del_mes != null}">
	<p>${del_mes}</p>
</c:if>
<div>
	<form action="Schedule_edit.action">
		<c:choose>
			<c:when test="${cal_list.size() > 0}">
				<table>
					<tr>
						<th>時間</th>
						<th>分</th>
						<th>スケジュール</th>
						<th></th>
					</tr>
					<c:forEach var="item" items="${cal_list}" varStatus="status">
				  		<tr>
				  			<td><input type="number" value="${item.setting_date.substring(0,2)}" name="${status.index}_hour_${item.user_id}" min="0" max="23" required></td>
				  			<td><input type="number" value="${item.setting_date.substring(3,5)}" name="${status.index}_minute_${item.user_id}" min="0" max="59" required></td>
				  			<td><input type="text" value="${item.schedule_content}" name="${status.index}_content_${item.user_id}" required></td>
				  			<td><a href="Schedule_delete.action?delete_data=${item.setting_date}">削除</a></td>
				  		</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>スケジュールが登録されていません</p>
			</c:otherwise>
		</c:choose>
		<input type="hidden" value="${cal_list.size()}" name="list_size"> 
		<input type="submit" value="変更"> 
	</form>
</div>
<a href="../common/top.jsp">TOP</a>
<script src="../js/attendance.js"></script>
<%@include file="../footer.jsp"  %>