<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>カレンダー表示画面</title>
    <link rel="stylesheet" type="text/css" href="../css/calendar.css">
    <script src="../js/calendar.js" defer></script>
</head>
<body>
    <h1>カレンダー表示</h1>

    <!-- 天気情報エリア -->
    <div class="weather-info">
        <h2>天気情報</h2>
        <c:forEach begin="0" end="3" step="2" var="i">
            <p>${today_temperature_data[i]} ： ${today_temperature_data[i+1]}</p>
        </c:forEach>
    </div>

    <!-- 日付と授業エリア -->
    <div class="date-and-schedule">
        <div class="date-box" id="dateBox">
            <div class="date-text">${selectedDate}</div> <!-- 日付表示 -->
            <div class="class-text">詳細情報</div> <!-- 授業メッセージ -->
        </div>
    </div>

    <!-- 予定エリア -->
    <h2>予定</h2>
    <div class="plan-info">
 		<c:choose>
			<c:when test="${cal_list.size() > 0}">
				<c:forEach var="item" items="${cal_list}">
					<p>${item.setting_date}　${item.schedule_content}</p>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>予定はありません</p>
			</c:otherwise>
        </c:choose>
    </div>

    <!-- 提出物エリア -->
    <h2>提出物</h2>
    <div class="submission-info">
        <p>提出物はありません</p>
    </div>

    <!-- 詳しい予報とスケジュール画面へのリンク、トップ画面に戻るリンク -->
    <div class="additional-links">
        <p><a href="#">詳しい予報へ</a></p>
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

<%@ include file="../footer.jsp" %>
</body>
</html>
