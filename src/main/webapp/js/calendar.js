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

        let week = new Date(year, month, 1).getDay();
        let lastDay = new Date(year, month + 1, 0).getDate();
        let previousMonthLastDay = new Date(year, month, 0).getDate();
        let monthAdjust = Math.floor(month / 12);

        if (monthAdjust >= 0) {
            year += monthAdjust;
            month = month % 12;
        } else {
            year += monthAdjust;
            month = 12 + month % 12;
            if (month === 12) {
                month = 0;
            }
        }

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
                    calendarHTML += `<td class="ex_td" data-infomation="${year}/${month}/${previousMonthLastDay - week + j + 1}">${previousMonthLastDay - week + j + 1}</td>`;
                } else {
                    calendarHTML += `<td class="ex_td" data-infomation="${year}/${month + 2}/${nextMonthDayCounter}">${nextMonthDayCounter}</td>`;
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



// これは、クラス内での利用の仕方が分からないので、無視。
// なんでもいいから要素をクリックしたら、イベントが発生する。
// その要素がtdタグなら、処理を実行する。
// 日付クリック時にCalendarDisplayActionに遷移
    document.addEventListener('click', function(e) {
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
        dateBox.addEventListener("click", function() {
            showPopup();
        });
    }

    // 閉じるボタンをクリックしてポップアップを非表示に
    if (closePopup) {
        closePopup.addEventListener("click", function() {
            hidePopup();
        });
    }

    // ポップアップの外側クリックで非表示にする
    if (popupWindow) {
        popupWindow.addEventListener("click", function(event) {
            if (event.target === popupWindow) {
                hidePopup();
            }
        });
    }
});
