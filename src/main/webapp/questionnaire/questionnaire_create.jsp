<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アンケート作成</title>
    <link rel="stylesheet" href="../css/questionnaire.css">
</head>
<body>
    <div class="form-container">
        <h1 class="page-title">アンケート作成</h1>

        <!-- メッセージ表示エリア -->
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <form action="QuestionnaireCreate.action" method="post">
            <label for="title">アンケートタイトル:</label>
            <input type="text" id="title" name="title" value="${param.title}" required>

            <label for="question">アンケート内容:</label>
            <textarea id="question" name="question" required>${param.question}</textarea>

            <div id="answers">
                <label>回答選択肢:</label>
                <input type="text" name="answer1" placeholder="回答1" value="${param.answer1}">
                <input type="text" name="answer2" placeholder="回答2" value="${param.answer2}">
                <input type="text" name="answer3" placeholder="回答3" value="${param.answer3}">
                <input type="text" name="answer4" placeholder="回答4" value="${param.answer4}">
            </div>

            <button type="submit">送信</button>
        </form>
        <div class="common_back_button">
           <a href="QuestionnaireList.action"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div> 
        </div>
    </div>
</body>
</html>
<%@include file="../footer.jsp"  %>