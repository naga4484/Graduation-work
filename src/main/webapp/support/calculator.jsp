<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/support.css">    
    <title>電卓</title>
</head>
<body>
    <div class="calculator_title">
		<p>でんたっくん</p>
    </div>
    <div class="calculator">
        
        <!-- Calculation Display Area -->
		<input id="display" type="text" readonly /> <!-- readonly属性を追加 -->
        <br>
        <button onclick="append('7')">7</button>
        <button onclick="append('8')">8</button>
        <button onclick="append('9')">9</button>
        <button onclick="append('+')">+</button>
        <button onclick="clearLastEntry()">C</button> <!-- Cボタンの関数を修正 -->
        <br>
        <button onclick="append('4')">4</button>
        <button onclick="append('5')">5</button>
        <button onclick="append('6')">6</button>
        <button onclick="append('-')">-</button>
        <button onclick="append('%')">%</button>
        <br>
        <button onclick="append('1')">1</button>
        <button onclick="append('2')">2</button>
        <button onclick="append('3')">3</button>
        <button onclick="append('/')">÷</button>
        <button onclick="append('*')">×</button>
        <br>
        <button onclick="append('0')">0</button>
        <button onclick="append('.')">.</button>
        <button onclick="calc()">=</button>
        <button onclick="clearAll()">AC</button> <!-- ACボタンの関数を修正 -->
        <br>
    </div>
</body>
<script src="../js/calculator.js"></script>
