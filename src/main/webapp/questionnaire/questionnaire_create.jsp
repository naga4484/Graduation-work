<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アンケート作成</title>
    <script>
        let answerCount = 2; // 初期回答欄数
        const maxAnswers = 4; // 最大回答数

        function addAnswer() {
            if (answerCount < maxAnswers) {
                answerCount++;
                const answerDiv = document.createElement('div');
                answerDiv.id = `answer_${answerCount}`;
                answerDiv.innerHTML = `<input type="text" name="answer${answerCount}" placeholder="回答${answerCount}" required>`;
                document.getElementById("answers").appendChild(answerDiv);
            } else {
                alert("回答欄は最大4つまでです。");
            }
        }

        function removeAnswer() {
            if (answerCount > 2) {
                const answerDiv = document.getElementById(`answer_${answerCount}`);
                document.getElementById("answers").removeChild(answerDiv);
                answerCount--;
            } else {
                alert("回答欄は最低2つ必要です。");
            }
        }
    </script>
</head>
<body>
    <h1>アンケート作成</h1>

    <!-- メッセージ表示エリア -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="QuestionnaireCreate.action" method="post">
        <div>
            <label for="title">アンケートタイトル:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div>
            <label for="question">アンケート内容:</label>
            <textarea id="question" name="question" required></textarea>
        </div>
        <div id="answers">
            <div id="answer_1"><input type="text" name="answer1" placeholder="回答1" required></div>
            <div id="answer_2"><input type="text" name="answer2" placeholder="回答2" required></div>
        </div>
        <button type="button" onclick="addAnswer()">回答を追加</button>
        <button type="button" onclick="removeAnswer()">回答を削除</button>
        <div>
            <button type="submit">送信</button>
        </div>
    </form>
    <a href="../common/top.jsp">戻る</a>
</body>
</html>
