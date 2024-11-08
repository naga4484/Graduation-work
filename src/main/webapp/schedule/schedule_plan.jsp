<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>スケジュール管理画面</title>

<h1>スケジュール</h1>
<div>
	<p>${selectedDate}</p>
</div>
<div>
	<c:if test="${distinct_error != null}">
		<p>${distinct_error}</p>
	</c:if>
	<c:if test="${comp_mes != null}">
		<p>${comp_mes}</p>
	</c:if>
	<c:if test="${edit_mes != null}">
		<p>${edit_mes}</p>
	</c:if>
</div>
<div>
	<c:choose>
		<c:when test="${cal_list.size() > 0}">
			<table>
				<tr>
					<th>時間</th>
					<th>分</th>
					<th>スケジュール</th>
				</tr>
				<c:forEach var="item" items="${cal_list}">
			  		<tr>
			  			<td>${item.setting_date.substring(0,2)}</td>
			  			<td>${item.setting_date.substring(3,5)}</td>
			  			<td>${item.schedule_content}</td>
			  		</tr>
				</c:forEach>
			</table>
			<div>
				<button><a href="../schedule/schedule_edit.jsp">変更モード</a></button>
			</div>
		</c:when>
		<c:otherwise>
			<p>スケジュールが登録されていません</p>
		</c:otherwise>
	</c:choose>
</div>

<div>
	<form action="Scheduleregistration.action">
		<input type="text" name="schedule_content" id="schedule_content" placeholder="スケジュールを入力" required>
		<input type="number" name="hour" id="schedule_hour" placeholder="時" min="0" max="23" required>
		<input type="number" name="minute" id="schedule_minute" placeholder="分" min="0" max="59" required>
		<input type="submit" value="登録">
		
	</form>
</div>



<a href="../common/top.jsp">TOP</a>
<script src="../js/attendance.js"></script>
<%@include file="../footer.jsp"  %>