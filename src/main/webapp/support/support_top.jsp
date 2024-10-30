<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/support.css">

 
 	<div id="support_top">
	<h2>サポート</h2>
	<section>
	
	<!-- タイマーリンク -->
	<div class="timer_links">
	<a href="timer.jsp"><img src="" class="timer_link_icon"></a>
        <p class="timer_links_title">タイマー</p>
        <a href="timer.jsp">タイマーリンク</a>
	</div>
	
	<!-- カスタマイズリンク -->
	<div class="customize_links">
	<a href="customize.jsp"><img src="" class="customize_link_icon"></a>
		<p class="customize_links_title">カスタマイズ</p>
	</div>
	
	<!-- 電卓リンク -->
	 <div class="calculator_links">
            <!-- hrefを削除し、onclickイベントで子ウィンドウを開くように修正 -->
            <a href="#" onclick="openCalculator(); return false;">test<img src="" class="calculator_link_icon"></a>
            <p class="calculator_links_title">電卓</p>
        </div>

 <a href="../common/top.jsp">戻る</a>
 <script src="../js/calculator.js"></script>

<%@include file="../footer.jsp"  %>



