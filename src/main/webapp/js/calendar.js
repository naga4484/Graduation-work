let popupWindow = document.getElementById("popupWindow");
let dateBox = document.getElementById("dateBox");
let closePopup = document.getElementById("closePopup");

// カレンダー表示用のクラス
class Calendar {
    constructor() {
        this.count = 0;
        this.lastButton = document.getElementById('last-button');
        this.nextButton = document.getElementById('next-button');
        this.calendarget(); // 初回のカレンダー表示
        this.addEventListeners();
    }

    // カレンダーの表示処理
    calendarget() {
        const weeks = ['日', '月', '火', '水', '木', '金', '土'];
        const date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + this.count;
        const nowYear = date.getFullYear();
        const nowMonth = date.getMonth();
        const nowDate = date.getDate();

        // 月と年の調整
        let adjustedMonth = (month + 12) % 12;
        year += Math.floor((month + 12) / 12) - 1;
        month = adjustedMonth;

        let week = new Date(year, month, 1).getDay();
        let lastDay = new Date(year, month + 1, 0).getDate();
        let previousMonthLastDay = new Date(year, month, 0).getDate();

        // カレンダーのHTMLを構築
        let calendarHTML = `<h1>${year}年 ${month + 1}月</h1><table><tr>`;
        for (let w of weeks) {
            calendarHTML += `<th class="table-title">${w}</th>`;
        }
        calendarHTML += '</tr>';

        let dayCounter = 1;
        let nextMonthDayCounter = 1;
        let firstFlag = 0;

        for (let i = 0; i < 6; i++) {
            calendarHTML += '<tr>';
            for (let j = 0; j < 7; j++) {
                if (j === week && firstFlag === 0) {
                    firstFlag = 1;
                }
                if (firstFlag === 1 && dayCounter <= lastDay) {
                    if (year === nowYear && month === nowMonth && dayCounter === nowDate) {
                        calendarHTML += `<td class="normal" style="background-color:#f3ff70" data-infomation="${year}/${month + 1}/${dayCounter}">${dayCounter}</td>`;
                    } else {
                        calendarHTML += `<td class="normal" data-infomation="${year}/${month + 1}/${dayCounter}">${dayCounter}</td>`;
                    }
                    dayCounter++;
                } else if (firstFlag === 0) {
                    // 先月のデータを補正
                    let prevMonth = month - 1;
                    let prevYear = year;
                    if (prevMonth < 0) {
                        prevMonth = 11;
                        prevYear--;
                    }
                    calendarHTML += `<td class="ex_td" data-infomation="${prevYear}/${prevMonth + 1}/${previousMonthLastDay - week + j + 1}">${previousMonthLastDay - week + j + 1}</td>`;
                } else {
                    // 次月のデータを補正
                    let nextMonth = month + 1;
                    let nextYear = year;
                    if (nextMonth > 11) {
                        nextMonth = 0;
                        nextYear++;
                    }
                    calendarHTML += `<td class="ex_td" data-infomation="${nextYear}/${nextMonth + 1}/${nextMonthDayCounter}">${nextMonthDayCounter}</td>`;
                    nextMonthDayCounter++;
                }
            }
            calendarHTML += '</tr>';
        }
        calendarHTML += '</table>';

        const calendarElement = document.getElementById('calendar');
        if (calendarElement) {
            calendarElement.innerHTML = calendarHTML;
        }
    }

    // 前月表示
    previousMonth() {
        this.count--;
        this.calendarget();
    }

    // 次月表示
    nextMonth() {
        this.count++;
        this.calendarget();
    }

    // ボタンにイベントリスナーを追加
    addEventListeners() {
        if (this.lastButton) {
            this.lastButton.addEventListener('click', () => this.previousMonth());
        }
        if (this.nextButton) {
            this.nextButton.addEventListener('click', () => this.nextMonth());
        }
    }
}

// カレンダーインスタンスの生成
document.addEventListener("DOMContentLoaded", function () {
    // カレンダーインスタンス作成
    new Calendar();

    // 日付クリック時にCalendarDisplayActionに遷移
    document.addEventListener('click', function (e) {
        if (e.target.classList.contains('normal') || e.target.classList.contains('ex_td')) {
            const selectedDate = e.target.dataset.infomation;
            window.location.href = '../schedule/CalendarDisplay.action?selectedDate=' + encodeURIComponent(selectedDate);
        }
    });

    // ポップアップを表示する関数
    function showPopup() {
        if (popupWindow) {
            popupWindow.classList.add("show");
        }
    }

    // ポップアップを非表示にする関数
    function hidePopup() {
        if (popupWindow) {
            popupWindow.classList.remove("show");
        }
    }

    // 日付ボックスをクリックしてポップアップを表示
    if (dateBox) {
        dateBox.addEventListener("click", function () {
            showPopup();
        });
    }

    // 閉じるボタンをクリックしてポップアップを非表示に
    if (closePopup) {
        closePopup.addEventListener("click", function () {
            hidePopup();
        });
    }

    // ポップアップの外側クリックで非表示にする
    if (popupWindow) {
        popupWindow.addEventListener("click", function (event) {
            if (event.target === popupWindow) {
                hidePopup();
            }
        });
    }
});

const limit = document.querySelectorAll(".txt-limit");
const len = 20;
limit.forEach((element) => { // 取得した要素に対してループを実行
    let str = element.textContent; // 要素のテキスト内容を取得
    let font_elm = element.innerHTML.substring(0,22);
    if (str.length > len) {
        element.innerHTML = font_elm + str.substring(0, len) + "…</font>" ; // 指定文字数で切り取って末尾に "…" を追加
    }
});