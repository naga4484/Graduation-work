let countdown; 
let isCountingUp = false;  // カウントアップかどうかを判断するフラグ
let countUpTime = 0;       // カウントアップ用のタイム変数

// 入力時にリアルタイムでタイマー表示を更新する関数
function validateAndUpdate(element) {
    // 入力値が数字のみであることを確認
    element.value = element.value.replace(/[^0-9]/g, '');

    // 入力値が2桁以内であることを確認
    if (element.value.length > 2) {
        element.value = element.value.slice(0, 2);
    }

    updateDisplay();
    }

const updateDisplay = () => {
    const hours = parseInt(document.getElementById("hours").value) || 0;
    const minutes = parseInt(document.getElementById("minutes").value) || 0;
    const seconds = parseInt(document.getElementById("seconds").value) || 0;
    document.getElementById("timerDisplay").innerHTML =
        hours + ":" + ("0" + minutes).slice(-2) + ":" + ("0" + seconds).slice(-2);  //時間の表示が2桁になるように
    };


const startTimer = () => {
    const hours = parseInt(document.getElementById("hours").value) || 0;
    const minutes = parseInt(document.getElementById("minutes").value) || 0;
    const seconds = parseInt(document.getElementById("seconds").value) || 0;
    let totalTime = hours * 3600 + minutes * 60 + seconds;

    if (countdown) {
        clearInterval(countdown);
    }

    // 時・分・秒がすべて0の場合はカウントアップタイマーを起動
    if (totalTime === 0) {
        isCountingUp = true;
        countdown = setInterval(() => {
            countUpTime++;  // カウントアップの変数を増加
            const displayHours = Math.floor(countUpTime / 3600);
            const displayMinutes = Math.floor((countUpTime % 3600) / 60);
            const displaySeconds = countUpTime % 60;
            document.getElementById("timerDisplay").innerHTML =
                displayHours + ":" + ("0" + displayMinutes).slice(-2) + ":" + ("0" + displaySeconds).slice(-2);
        }, 1000);
    } else {
        isCountingUp = false;
        countdown = setInterval(() => {
            if (totalTime <= 0) {
                clearInterval(countdown);
                alert("時間です！お疲れさまでした！");  //終了時のメッセージ
            } else {
                totalTime--;
                const displayHours = Math.floor(totalTime / 3600);
                const displayMinutes = Math.floor((totalTime % 3600) / 60);
                const displaySeconds = totalTime % 60;
                document.getElementById("timerDisplay").innerHTML =
                    displayHours + ":" + ("0" + displayMinutes).slice(-2) + ":" + ("0" + displaySeconds).slice(-2);
            }
        }, 1000);
    }
};

const stopTimer = () => {
    clearInterval(countdown);  // カウントを停止して時間を保持
};

const resetTimer = () => {
    clearInterval(countdown);
    countUpTime = 0;  // カウントアップ時間をリセット
    document.getElementById("timerDisplay").innerHTML = "0:00:00";
    document.getElementById("hours").value = "";
    document.getElementById("minutes").value = "";
    document.getElementById("seconds").value = "";
};
