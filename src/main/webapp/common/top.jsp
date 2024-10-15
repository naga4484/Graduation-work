<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>トップ画面</title>
<img src="../images/main_image.png" class="main_image">
<!-- ここはカレンダー機能 -->
<div class="cal-area">
    <div id="calendar"></div>
    <div class="actions">
        <button id="last-button"><span>前の月</span></button>
        <button id="next-button"><span>次の月</span></button>
    </div>
</div>


<!-- ここらへんから、トップ画面のメイン要素のHTML -->
<div class="top_down_content">

	<div class="top_main_function_icon">
		<!-- 各種機能のアイコン用 -->
		<c:choose>
		    <c:when test="${account.account_kind == \"教師\"}">
		        <a href="../teacher_function/teacher_function.jsp">教師</a>
		    </c:when>
		</c:choose>
		<a href="">自己管理</a>
		<a href="">グループ管理</a>
		<a href="">アンケート</a>
		<a href="">効率化</a>
	</div>
	<div class="top_sub_fnction_icon">
		<a href="">サポート</a>
		<a href="">リラックス</a>
	</div>
	<div class="top_critical_function_icon">
		<a href="../account/Logout.action">ログアウト</a>
		<a href="">問い合わせ</a>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- ここらへんはプチ開発で試した内容。とりあえず保存しているだけ -->
	<h1>ログイン処理の確認</h1>
	<p>${account.name}　でログインしている</p>
	<p><a href="../account/Logout.action">ログアウト</a></p>
	
	<h1>スクレイピングの確認</h1>
	<c:forEach begin="0" end="5" step="2" var="i">
		<p>${today_temperature_data[i]} ： ${today_temperature_data[i+1]}</p>
	</c:forEach>
	
	<h1>AI使用の確認</h1>
	<p><a href="../ai/gemini.jsp">AI使用ページへ</a></p>
</div>

<%@include file="../footer.jsp"  %>