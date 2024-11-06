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

    <!-- URLから日付を取得 -->
    <%
        String selectedDate = (String) request.getAttribute("selectedDate");
        if (selectedDate == null) {
            selectedDate = "日付が選択されていません";
        }
    %>

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
            <div class="date-text"><%= selectedDate %></div> <!-- 日付表示 -->
            <div class="class-text">授業がありません</div> <!-- 授業メッセージ -->
        </div>
    </div>

    <!-- 予定エリア -->
    <h2>予定</h2>
    <div class="plan-info">
        <p>予定がありません</p>
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
            <h2>詳細情報</h2>
            <ul>
                <li>（データベース接続は後で追加）</li>
            </ul>
            <button id="closePopup">閉じる</button>
        </div>
    </div>

<%@ include file="../footer.jsp" %>
</body>
</html>
