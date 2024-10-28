function update(value) {
    document.getElementById('display').value = value; // ID指定でターゲット
}

function append(value) {
    document.getElementById('display').value += value;
}

function calc() {
    const expression = document.getElementById('display').value;
    try {
        const result = new Function('return ' + expression)();
        update(result.toString());
    } catch (e) {
        update("Error"); // エラー処理を追加
    }
}

// クリアボタン用関数
function clearLastEntry() {
    const display = document.getElementById('display');
    display.value = display.value.slice(0, -1); // 末尾の1文字を削除
}

// 全消去ボタン用関数
function clearAll() {
    update('');
}



function openCalculator() {
     window.open("calculator.jsp", "calculatorWindow", "width=200,height=200");
}