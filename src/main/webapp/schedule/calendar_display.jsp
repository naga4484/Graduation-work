<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>カレンダー表示画面</title>
<link rel="stylesheet" type="text/css" href="../css/calendar.css">
<script src="../js/calendar.js" defer></script>


<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>カレンダー</h1>

<!-- 天気情報エリア -->
<div class="weather-info">
    <h2>天気情報</h2>
    <c:choose>
	    <c:when test="${today_temperature_data.size() == 1}">
	    	<p>${today_temperature_data[0]}</p>
	    </c:when>
    	<c:otherwise>
		    <p>${today_temperature_data[0]}の今日の天気</p>
		    <p>天気　：　${today_temperature_data[1]}</p>
		    <p>最高気温　：　${today_temperature_data[2]}</p>
		    <p>最高気温　：　${today_temperature_data[3]}</p>
	    </c:otherwise>
	</c:choose>
</div>

<!-- 日付と授業エリア -->
<div class="date-and-schedule">
    <div class="date-box" id="dateBox">
        <div class="date-text">${selectedDate}</div> <!-- 日付表示 -->
        <div class="class-text">詳細情報</div> <!-- 授業メッセージ -->
    </div>
</div>

<!-- 予定エリア -->
<div class="plan_area">
	<h2>予定</h2>
	<div class="plan-info">
		<c:choose>
			<c:when test="${cal_list.size() > 0}">
				<c:forEach var="item" items="${cal_list}">
					<p class="txt-limits">${item.setting_date}　${item.schedule_content}</p>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<p>予定はありません</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<!-- 提出物エリア -->
<div class="submissions_area">
	<h2>提出物</h2>
	<div class="submission-info">
		<c:choose>
			<c:when test="${cal_submissions.size() > 0}">
				<c:forEach var="item" items="${cal_submissions}">
					<p class="txt-limit"><font color="${item.submissions_date_color}">${item.create_date}　${item.name}</font></p>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>提出物はありません</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<!-- 詳しい予報とスケジュール画面へのリンク、トップ画面に戻るリンク -->
<div class="additional-links">
    <p><a href="Schedule_weather.action">詳しい予報へ</a></p>
    <p><a href="Schedule_plan.action">スケジュール画面</a></p>
    <p><a href="../common/top.jsp">トップ画面に戻る</a></p>
</div>

<!-- ポップアップウィンドウ -->
<div id="popupWindow" class="popup-window">
    <div class="popup-content">
        <h2>${selectedDate}</h2>
        <ul>
         <c:forEach var="item" items="${schedule_timetable}">
         	<c:choose>
				<c:when test="${item.subject_id != null}">
					<c:forEach var="subject" items="${class_subject}">
						<c:if test="${subject.subject_id == item.subject_id}">
							<li>${subject.subject_name}</li>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li>未設定</li>
				</c:otherwise>
			</c:choose>
         </c:forEach>
        </ul>
        <button id="closePopup">閉じる</button>
    </div>
</div>

<script src="../js/text_limit.js" defer></script>
<%@ include file="../footer.jsp" %>
