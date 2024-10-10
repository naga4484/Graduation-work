
class calendar {
    constructor() {
        this.count = 0;
        this.last = document.getElementById('last-button');
        this.next = document.getElementById('next-button');
    }

    calendarget() {
        const weeks = ['日', '月', '火', '水', '木', '金', '土']
        const date = new Date()
        let year = date.getFullYear() // 年
        let month = date.getMonth() + this.count//現在の月
        let week = new Date(year,month,1).getDay(); // 月始めの曜日
        let lastday = new Date(year, month + 1, 0).getDate(); // 末日
        let agolastday = new Date(year, month, 0).getDate();
        const nowyear = date.getFullYear();
        const nowmonth = date.getMonth();
        const nowdata = date.getDate();


        let monthdate = Math.floor(month / 12);
        if(monthdate >= 0) {
            year += monthdate;
            month = month % 12;
        }
        else if(month < 0) {
            year += (1 * monthdate);
            month = 12 + month % 12;
            if(month == 12) {
                month = 0;
            }
        }
        
        // 表示時は月を1足さないといけない
        let calendar = `<h1>${year}年 ${month + 1}月</h1>`;

        calendar += `<table><tr>`
        for (let w = 0; w < weeks.length; w++) {
            calendar += `<th class="table-title">${weeks[w]}</th>`
        }
        calendar += `</tr>`
        let firstflag = 0;
        let daycount = 1;
        let beforedaycount = 1;
        let lmday = '';

        for(let i = 1; daycount <= lastday; i++){
            calendar += `<tr>`
            for (let w = 0; w < 7; w++) {
                
                if(w == week && firstflag == 0) {
                    if(year == nowyear && month == nowmonth && daycount == nowdata) {
                        calendar += `<td class="normal" style="background-color:#f3ff70" data-infomation="${year}/${month + 1}/${daycount}">${daycount}</td>`
                    } else {
                        calendar += `<td class="normal" data-infomation="${year}/${month + 1}/${daycount}">${daycount}</td>`
                    }
                    firstflag = 1;
                    daycount++;
                }
                else if(firstflag == 0) {
                    lmday = week - w - 1;
                    if(month == 0) {
                        calendar += `<td class="ex_td" data-infomation="${year - 1}/${12}/${agolastday - lmday}">${agolastday - lmday}</td>`
                    }else {
                        calendar += `<td class="ex_td" data-infomation="${year}/${month}/${agolastday - lmday}">${agolastday - lmday}</td>`
                    }
                    
                }
                else if(firstflag == 1){
                    if(daycount > lastday) {
                        if(month == 11){
                            calendar += `<td class="ex_td" data-infomation="${year + 1}/${1}/${beforedaycount}">${beforedaycount}</td>`
                        } else {
                            calendar += `<td class="ex_td" data-infomation="${year}/${month + 1}/${beforedaycount}">${beforedaycount}</td>`
                        }
                        beforedaycount++;
                    }
                    else {
                        if(year == nowyear && month == nowmonth && daycount == nowdata) {
                            calendar += `<td class="normal" style="background-color:#f3ff70" data-infomation="${year}/${month + 1}/${daycount}">${daycount}</td>`
                        } else {
                            calendar += `<td class="normal" data-infomation="${year}/${month + 1}/${daycount}">${daycount}</td>`
                        }
                        daycount++;
                    }
                    
                }
                
            }
            calendar += `</tr>`
        }
        calendar += `</table>`
        const calendarHTML = document.getElementById('calendar');
        calendarHTML.innerHTML = calendar;

        
    }

    lastmonth() {
        this.count--;
        this.calendarget()
    }

    nextmonth() {    
        this.count++;
        this.calendarget()  
    }
}

// calendarクラスのインスタンス生成。
const cal = new calendar();
cal.calendarget();

// ここら辺は無理やりクラスを利用する形にした。
cal.next.addEventListener('click',function(){
    cal.nextmonth();
});
cal.last.addEventListener('click',function(){
    cal.lastmonth();
});

// これは、クラス内での利用の仕方が分からないので、無視。
// なんでもいいから要素をクリックしたら、イベントが発生する。
// その要素がtdタグなら、処理を実行する。
document.addEventListener('click',function(e){
    if(e.target.classList.contains('normal') || e.target.classList.contains('ex_td')){
        alert(`クリックした日付は、${e.target.dataset.infomation} です。`);
    }
})



