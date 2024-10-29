let relaxWindow; // リラックスウィンドウを保持
let currentAudio; // 現在再生中の音楽オブジェクトを保持
let musicList = []; // 音楽ファイルリスト
let currentTrackIndex = 0; // 現在再生中のトラックのインデックス

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
            musicSelect.innerHTML = ""; // セレクトボックスをリセット
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
    currentAudio.loop = false; // デフォルトはループオフ
    currentAudio.play();

    // セレクトボックスの選択状態を現在の曲に更新
    const musicSelect = document.getElementById("musicSelect");
    musicSelect.value = selectedMusic;

    // 曲の終了時に次の曲に自動で移動
    currentAudio.addEventListener("ended", skipToNextTrack);
}

// 次の曲にスキップする関数
function skipToNextTrack() {
    // 次の曲のインデックスに移動
    currentTrackIndex = (currentTrackIndex + 1) % musicList.length; // 最後の曲の後は最初の曲に戻る
    playMusic(); // 次の曲を再生
}

// スキップボタンをクリックした際のイベント
document.getElementById("skipButton").addEventListener("click", skipToNextTrack);

// 音楽を一時停止/再生する関数
function togglePlayPause() {
    if (currentAudio) {
        if (currentAudio.paused) {
            currentAudio.play();
        } else {
            currentAudio.pause();
        }
    }
}

// 音楽を巻き戻す関数
function rewindMusic() {
    if (currentAudio) {
        currentAudio.currentTime = Math.max(0, currentAudio.currentTime - 10); // 10秒巻き戻し
    }
}

// ループ再生の切り替え関数
function toggleLoop() {
    if (currentAudio) {
        currentAudio.loop = !currentAudio.loop; // ループのオン/オフを切り替え
        alert(`ループ再生が${currentAudio.loop ? "有効" : "無効"}になりました`);
    }
}

// ランダムな運勢を表示する関数
function showFortune() {
    const fortunes = ["大吉", "中吉", "小吉", "吉", "凶", "大凶"];
    const result = fortunes[Math.floor(Math.random() * fortunes.length)];
    document.getElementById("fortuneResult").innerText = result; // 運勢結果を表示
}

// ページがロードされたときに音楽リストを読み込む
window.addEventListener("load", loadMusicList);
