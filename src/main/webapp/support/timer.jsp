<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<html>
<head>
    <title>タイマー</title>
    <link rel="stylesheet" type="text/css" href="../css/support.css">
    <script src="../js/timer.js"></script>
</head>
<body>

    <div class="timer-container">
        <div id="timerDisplay">0:00:00</div>
        <div class="time-inputs">
            <input type="number" id="hours" min="0" max="99"oninput="validateAndUpdate(this)"> <label for="hours">時</label>
            <input type="number" id="minutes" min="0" max="99" oninput="validateAndUpdate(this)"> <label for="minutes">分</label>
            <input type="number" id="seconds" min="0" max="99" oninput="validateAndUpdate(this)"> <label for="seconds">秒</label>
        </div>

        <div class="timer-buttons">
            <button onclick="startTimer()">スタート</button>
            <button onclick="stopTimer()">ストップ</button>
            <button onclick="resetTimer()">リセット</button>
        </div>
    </div>
<div class="common_back_button">
    <a href="support_top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a>
</div>
</body>
</html>
<%@ include file="../footer.jsp" %>
