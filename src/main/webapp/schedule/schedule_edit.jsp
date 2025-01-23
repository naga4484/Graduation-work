<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/schedule.css">

<title>スケジュール変更画面</title>

<h1 class="page_title">スケジュール変更</h1>
<div>
	<p class="schedule_plan_date">${selectedDate}</p>
</div>
<div class="schedule_req_mes">
	<c:if test="${dis_error != null}">
	<p>${dis_error}</p>
	</c:if>
	<c:if test="${del_mes != null}">
		<p>${del_mes}</p>
	</c:if>
</div>
<form action="Schedule_edit.action">
	<div class="schedule_plan_edit_form">
		<c:choose>
			<c:when test="${cal_list.size() > 0}">
				<div class="schedule_edit_table_head">
					<div class="schedule_edit_table_hour">
						<p>時間</p>
					</div>
					<div class="schedule_edit_table_minute">
						<p>分</p>
					</div>
					<div class="schedule_edit_table_schedule">
						<p>スケジュール</p>
					</div>
					<div class="schedule_edit_table_delete">
						<p>削除</p>
					</div>
				</div>
				<c:forEach var="item" items="${cal_list}" varStatus="status">
					<div class="schedule_edit_table_body">
						<div class="schedule_edit_table_hour">
							<p><input type="number" value="${item.setting_date.substring(0,2)}" name="${status.index}_hour_${item.user_id}" min="0" max="23" required class="schedule_edit_hour_input"></p>
						</div>
						<div class="schedule_edit_table_minute">
							<p><input type="number" value="${item.setting_date.substring(3,5)}" name="${status.index}_minute_${item.user_id}" min="0" max="59" required class="schedule_edit_minute_input"></p>
						</div>
						<div class="schedule_edit_table_schedule">
							<p><input type="text" value="${item.schedule_content}" name="${status.index}_content_${item.user_id}" required class="schedule_edit_schedule_input"></p>
						</div>
						<div class="schedule_edit_table_delete">
							<p><a href="Schedule_delete.action?delete_data=${item.calendar_id}">削除</a></p>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>スケジュールが登録されていません</p>
			</c:otherwise>
		</c:choose>
		<input type="hidden" value="${cal_list.size()}" name="list_size"> 
	</div>	
	<input type="submit" value="変更" class="schedule_plan_edit_button"> 
</form>

<div class="common_back_button">
  <a href="../schedule/schedule_plan.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>