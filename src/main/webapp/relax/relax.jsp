<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List" %>
<html>
<head>
    <title>リラックス機能</title>
    <link rel="stylesheet" type="text/css" href="../css/relax.css">
</head>
<body>
    <div class="relax-container">
        <h1>リラックス</h1>
        <div class="bgm-control">
            <label for="musicSelect">曲の選択</label>
            <select id="musicSelect"></select>
            <div class="button-group">
                <img id="rewindButton" src="../images/rewind.png" alt="巻き戻し" onclick="rewindMusic()" class="control-icon">
                <img id="playPauseButton" src="../images/play.png" alt="再生/一時停止" onclick="togglePlayPause()" class="control-icon">
                <img id="skipButton" src="../images/skip.png" alt="スキップ" onclick="skipToNextTrack()" class="control-icon">
            </div>
            <img id="loopButton" src="../images/loop.png" alt="ループ切り替え" onclick="toggleLoop()" class="control-icon loop-icon">
        </div>
        <div class="fortune-telling">
            <button onclick="showFortune()">運勢を占う</button>
            <div id="fortuneResult">本日の運勢</div>
        </div>
    </div>
    <script src="../js/relax.js"></script>
</body>
</html>
