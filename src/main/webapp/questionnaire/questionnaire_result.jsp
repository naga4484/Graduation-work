<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アンケート結果</title>
    <link rel="stylesheet" href="../css/questionnaire.css">
</head>
<body>
    <div class="result-container">
        <h1 class="page-title2">アンケート結果</h1>

        <!-- アンケートタイトルと質問内容 -->
        <c:if test="${not empty questionnaire}">
            <h2 class="result-title">${questionnaire.title}</h2>
            <p>${questionnaire.questionnaire}</p>
        </c:if>

        <!-- 回答結果テーブル -->
        <c:if test="${not empty questionnaire.options}">
            <h3 class="result-title">回答結果</h3>
            <table class="result-table">
                <thead>
                    <tr>
                        <th>選択肢</th>
                        <th>回答数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="option" items="${questionnaire.options}">
                        <tr>
                            <td>${option.optionContent}</td>
                            <td>${option.count}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- 戻るリンク -->
        <div class="common_back_button">
           <a href="QuestionnaireList.action"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div> 
        </div>
    </div>
</body>
</html>
<%@include file="../footer.jsp"  %>
