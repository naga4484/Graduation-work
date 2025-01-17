<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アンケート回答</title>
    <link rel="stylesheet" href="../css/questionnaire.css">
</head>
<body>
    <div class="answer-container">
        <h1 class="page-title2">アンケート回答</h1>

        <!-- エラーメッセージの表示 -->
        <c:if test="${not empty errorMessage}">
            <p class="message message-error">${errorMessage}</p>
        </c:if>

        <!-- アンケート詳細の表示 -->
        <c:if test="${not empty questionnaire}">
            <h2 class="question-title">${questionnaire.title}</h2>
            <p class="question-content">${questionnaire.questionnaire}</p>

            <!-- 選択肢の表示 -->
            <form action="SubmitAnswer.action" method="post">
                <input type="hidden" name="questionnaireId" value="${questionnaire.questionnaireId}">
                <div class="options-container">
                    <c:forEach var="option" items="${questionnaire.options}">
                        <div class="option-item">
                            <label>
                                <input type="radio" name="selectedOption" value="${option.optionNumber}" required>
                                ${option.optionContent}
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <button type="submit">送信</button>
                </div>
            </form>
        </c:if>

        <div class="common_back_button">
           <a href="QuestionnaireList.action"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div> 
        </div>
    </div>
</body>
</html>
<%@include file="../footer.jsp"%>
