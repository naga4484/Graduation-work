<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>
<%@ page import="java.util.ArrayList" %>
<link rel="stylesheet" type="text/css" href="../css/inquiry.css">
<html>

<body>
    <div class="container">
        <h2>アンケート一覧</h2>
        <div class="buttons">
            <button onclick="location.href='conductSurvey.jsp'">アンケートを実施</button>
            <button onclick="location.href='participateSurvey.jsp'">アンケートに参加</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>自作アンケート</th>
                    <th>詳細</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // サーブレットから取得したアンケートデータを取り出し
                    ArrayList<String> surveys = (ArrayList<String>) request.getAttribute("surveys");
                    if (surveys != null && !surveys.isEmpty()) {
                        for (String question : surveys) {
                %>
                            <tr>
                                <td><%= question %></td>
                                <td>
                                    <button class="analyze-btn">解析</button>
                                    <button class="utilize-btn">活用</button>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr><td colspan="2">データがありません</td></tr>
           	<a href="../common/top.jsp">戻る</a>
                        
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
<%@include file="../footer.jsp"  %>