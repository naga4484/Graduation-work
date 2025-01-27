<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アンケート一覧</title>
    <link rel="stylesheet" href="../css/questionnaire.css">
</head>
<body>

        <h1 class="page-title">アンケート一覧</h1>

    <div class="container">

        <!-- メッセージ表示 -->
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
            <!-- メッセージを一度だけ表示するためにセッションから削除 -->
            <c:set var="message" scope="session" value="" />
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>

        <!-- アンケートが存在しない場合のメッセージ -->
        <c:if test="${empty questionnaireList}">
            <p>現在はアンケートが存在しません。</p>
        </c:if>

        <!-- アンケート一覧表示 -->
        <c:if test="${not empty questionnaireList}">
            <table>
                <thead>
                    <tr>
                        <th>アンケートタイトル</th>
                        <th>結果</th>
                        <th>回答</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="questionnaire" items="${questionnaireList}">
                        <tr class="${questionnaire.answered ? 'answered' : ''}">
                            <td>${questionnaire.title}</td>
                            <td>
                                <!-- 作成者の場合のみ結果ボタンを表示 -->
                                <c:if test="${questionnaire.creator}">
                                    <a href="QuestionnaireResult.action?questionnaireId=${questionnaire.questionnaireId}">
                                        <button>結果</button>
                                    </a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!questionnaire.answered}">
                                    <a href="QuestionnaireParticipateDetail.action?questionnaireId=${questionnaire.questionnaireId}">
                                        <button>回答</button>
                                    </a>
                                </c:if>
                                <c:if test="${questionnaire.answered}">
                                    <span style="color: gray;">回答済み</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- アンケート作成ボタン -->
                <div class="form-actions">
                    <a href="questionnaire_create.jsp"><button>アンケート作成</button></a>
                </div>
                <div class="form-actions">
                    <div class="common_back_button">
           <a href="../common/top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div> 
        </div>
    </div>
</body>
</html>
<%@include file="../footer.jsp" %>
