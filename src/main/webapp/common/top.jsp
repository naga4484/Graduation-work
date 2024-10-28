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
        <button id="last-button"><img src="../images/左矢印.png" class="cal_button_image_last"></button>
        <button id="next-button"><img src="../images/右矢印.png" class="cal_button_image_next"></button>
    </div>
</div>


<!-- ここらへんから、トップ画面のメイン要素のHTML -->
<div class="top_down_content">

	<div class="top_main_function_icon">
		<div class="top_main_function_icon_title">
			<p>基本機能</p>
		</div>
		<!-- 各種機能のアイコン用 -->
		<c:choose>
		    <c:when test="${account.account_kind == \"教師\"}">
		    	<a href="../teacher_function/teacher_function.jsp">
			    	<div class="top_links_contents">
			    		<img src="../images/サンプル1.png" class="teacher_top_icon" id="teacher_top_icon">
			        	<p class="top_links_contents_title1">教師</p>
			    	</div>
			    </a>
		    </c:when>
		</c:choose>
		<div class="top_links_contents">
			<a href=""><img src="../images/サンプル5.png" class="my_management_top_icon"></a> <!-- 自己管理 -->
			<p class="top_links_contents_title2">自己管理</p>
		</div>
		<div class="top_links_contents">
			<a href=""><img src="../images/サンプル6.png" class="my_management_top_icon"></a> <!-- グループ管理 -->
			<p class="top_links_contents_title3">グループ</p>
		</div>
		<div class="top_links_contents">	
			<a href=""><img src="../images/サンプル2.png" class="my_management_top_icon"></a> <!-- アンケート管理 -->
			<p class="top_links_contents_title4">アンケート</p>
		</div>	
		<div class="top_links_contents">	
			<a href=""><img src="../images/サンプル3.png" class="my_management_top_icon"></a> <!-- 効率化管理 -->
			<p class="top_links_contents_title5">効率化</p>
		</div>
	</div>
	<div class="top_sub_fnction_icon">
		<a href="../support/support_top.jsp"><img src="../images/サンプル7.png" class="my_management_top_icon"></a> <!-- サポート -->
		<a href=""><img src="../images/サンプル8.png" class="my_management_top_icon"></a> <!-- リラックス -->
	</div>
	<div class="top_critical_function_icon">
		<div class="top_critical_function_contents">
			<a href="../account/Logout.action"><img src="../images/サンプル9.png" class="top_logout_icon"></a> <!-- ログアウト -->
			<a href="../account/Logout.action"><p class="top_logout_mes">ログアウト</p></a>
		</div>
		<div class="top_critical_function_contents">
			<a href="../inquiry/inquiry_form.jsp"><img src="../images/サンプル10.png" class="top_contact_icon"></a> <!-- 問い合わせ -->
			<p class="top_contact_mes">問い合わせ</p>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- ここらへんはプチ開発で試した内容。とりあえず保存しているだけ -->
	
	<h1>スクレイピングの確認</h1>
	<c:forEach begin="0" end="5" step="2" var="i">
		<p>${today_temperature_data[i]} ： ${today_temperature_data[i+1]}</p>
	</c:forEach>
	
	<h1>AI使用の確認</h1>
	<p><a href="../ai/gemini.jsp">AI使用ページへ</a></p>
</div>

<%@include file="../footer.jsp"  %>