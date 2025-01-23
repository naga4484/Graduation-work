<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/schedule.css">

<title>スケジュール管理画面</title>

<h1 class="page_title">スケジュール</h1>
<div>
	<p class="schedule_plan_date">${selectedDate}</p>
</div>
<div>
	<c:if test="${distinct_error != null}">
		<p class="system_return_mes">${distinct_error}</p>
	</c:if>
	<c:if test="${comp_mes != null}">
		<p class="system_return_mes">${comp_mes}</p>
	</c:if>
	<c:if test="${edit_mes != null}">
		<p class="system_return_mes">${edit_mes}</p>
	</c:if>
</div>
<div>
	<c:choose>
		<c:when test="${cal_list.size() > 0}">
			<iframe src="schedule_plan_list.jsp" class="schedule_plan_list"></iframe>
			<div class="schedule_plan_button">
				<a href="../schedule/schedule_edit.jsp"><button class="schedule_edit_button">変更</button></a>
			</div>
		</c:when>
		<c:otherwise>
			<p class="schedule_none_reg">スケジュールが登録されていません</p>
		</c:otherwise>
	</c:choose>
</div>

<div class="schedule_plan_form">
	<form action="Scheduleregistration.action">
		<input type="text" name="schedule_content" id="schedule_content" placeholder="スケジュールを入力" required>
		<input type="number" name="hour" id="schedule_hour" placeholder="時" min="0" max="23" required class="schedule_plan_form_number">
		<input type="number" name="minute" id="schedule_minute" placeholder="分" min="0" max="59" required class="schedule_plan_form_number">
		<input type="submit" value="登録" class="schedule_plan_form_button">
		
	</form>
</div>
<script src="../js/text_limit.js" defer></script>

<div class="common_back_button">
  <a href="../schedule/calendar_display.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>