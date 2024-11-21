<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アンケート一覧</title>
    <link rel="stylesheet" href="../css/questionnaire.css">
</head>
<body>
    <h1>アンケート一覧</h1>

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
                    <th>詳細</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="questionnaire" items="${questionnaireList}">
                    <tr>
                        <td>${questionnaire.title}</td>
                        <td>
                            <button>解析</button>
                            <button>活用</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- アクションボタン -->
    <div>
        <a href="questionnaire_create.jsp"><button>アンケート作成</button></a>
        <a href="questionnaire_participate.jsp"><button>アンケート参加</button></a>
    </div>

    <a href="../common/top.jsp">戻る</a>
</body>
</html>
