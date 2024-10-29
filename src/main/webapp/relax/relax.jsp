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
                <button onclick="playMusic()">再生</button>
                <button onclick="togglePlayPause()">一時停止/再開</button>
                <button onclick="rewindMusic()">巻き戻し</button>
                <button id="skipButton" onclick="skipToNextTrack()">スキップ</button>
                <button onclick="toggleLoop()">ループ切替</button>
            </div>
        </div>
        <div class="fortune-telling">
            <button onclick="showFortune()">運勢を占う</button>
            <div id="fortuneResult">本日の運勢</div>
        </div>
    </div>
    <script src="../js/relax.js"></script>
</body>
</html>
