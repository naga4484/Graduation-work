<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<title>トップ画面</title>


<h1>ログイン処理の確認</h1>
<p>${account.name}　でログインしている</p>
<p><a href="../account/Logout.action">ログアウト</a></p>

<h1>スクレイピングの確認</h1>
<c:forEach begin="0" end="5" step="2" var="i">
	<p>${today_temperature_data[i]} ： ${today_temperature_data[i+1]}</p>
</c:forEach>

<h1>AI使用の確認</h1>
<p><a href="../ai/gemini.jsp">AI使用ページへ</a></p>

<%@include file="../footer.jsp"  %>