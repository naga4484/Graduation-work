let relaxWindow; // リラックスウィンドウを保持
let currentAudio; // 現在再生中の音楽オブジェクトを保持
let musicList = []; // 音楽ファイルリスト
let currentTrackIndex = 0; // 現在再生中のトラックのインデックス
let isPlaying = false; // 再生中かどうかの状態
let isLooping = false; // ループ状態

// リラックスウィンドウを開く関数
function openRelaxWindow() {
    if (relaxWindow && !relaxWindow.closed) {
        relaxWindow.focus();
    } else {
        relaxWindow = window.open(
            "../relax/relax.jsp",
            "RelaxWindow",
            "width=600,height=500,resizable=yes"
        );
    }
}

// 音楽リストを動的に読み込む関数
function loadMusicList() {
    fetch("../relax/musicList") // サーブレットから音楽リストを取得
        .then(response => response.json())
        .then(data => {
            musicList = data; // 音楽リストを格納
            const musicSelect = document.getElementById("musicSelect");
            musicSelect.innerHTML = ""; 
            musicList.forEach(file => {
                const option = document.createElement("option");
                option.value = file;
                option.text = file.replace(".mp3", ""); // 拡張子を除去して表示
                musicSelect.appendChild(option);
            });
            currentTrackIndex = 0; // 初期化
            playMusic(); // 最初の曲を自動再生
        })
        .catch(error => console.error("Error loading music list:", error));
}

// 音楽を再生する関数
function playMusic() {
    if (musicList.length === 0) return; // 音楽リストが空の場合は何もしない
    const selectedMusic = musicList[currentTrackIndex]; // 現在の曲を取得
    if (currentAudio) {
        currentAudio.pause();
        currentAudio.currentTime = 0;
    }
    currentAudio = new Audio(`../music/${selectedMusic}`);
    currentAudio.loop = isLooping; // ループ設定を反映
    currentAudio.play();
    isPlaying = true;
    updatePlayPauseIcon(); // アイコンを再生中に更新

    // セレクトボックスの選択状態を現在の曲に更新
    const musicSelect = document.getElementById("musicSelect");
    musicSelect.value = selectedMusic;

    // 曲の終了時に次の曲に自動で移動
    currentAudio.addEventListener("ended", skipToNextTrack);
}

// 再生/一時停止のアイコンを切り替える関数
function togglePlayPause() {
    if (currentAudio) {
        if (isPlaying) {
            currentAudio.pause();
            isPlaying = false;
        } else {
            currentAudio.play();
            isPlaying = true;
        }
        updatePlayPauseIcon(); // アイコンを切り替え
    }
}

// アイコン画像を更新する関数
function updatePlayPauseIcon() {
    const playPauseButton = document.getElementById("playPauseButton");
    playPauseButton.src = isPlaying ? "../images/pause.png" : "../images/play.png";
}

// 次の曲にスキップする関数
function skipToNextTrack() {
    currentTrackIndex = (currentTrackIndex + 1) % musicList.length; // 次の曲に移動し、最後の曲の後は最初の曲に戻る
    playMusic(); // 次の曲を再生
}

// 巻き戻しの関数
function rewindMusic() {
    if (currentAudio) {
        currentAudio.currentTime = Math.max(0, currentAudio.currentTime - 10); // 10秒巻き戻し
    }
}

// ループ切り替えのアイコンを更新する関数
function toggleLoop() {
    isLooping = !isLooping;
    if (currentAudio) {
        currentAudio.loop = isLooping;
    }
    const loopButton = document.getElementById("loopButton");
    loopButton.src = isLooping ? "../images/loop_on.png" : "../images/loop.png";
}

// ランダムな運勢を表示する関数
function showFortune() {
    const fortunes = ["大吉", "中吉", "小吉", "吉", "凶", "大凶"];
    const result = fortunes[Math.floor(Math.random() * fortunes.length)];
    document.getElementById("fortuneResult").innerText = result; // 運勢結果を表示
}

// ページがロードされたときに音楽リストを読み込む
window.addEventListener("load", loadMusicList);

// セレクトボックスの変更時に selectTrack 関数を呼び出す
document.getElementById("musicSelect").addEventListener("change", selectTrack);

// セレクトボックスの曲を選択したときに再生する関数
function selectTrack() {
    const musicSelect = document.getElementById("musicSelect");
    const selectedMusic = musicSelect.value;
    currentTrackIndex = musicList.indexOf(selectedMusic); // 選択された曲のインデックスを取得
    playMusic(); // 選択された曲を再生
}