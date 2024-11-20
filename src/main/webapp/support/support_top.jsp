<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/support.css">

 <title>サポート画面</title>
 
  	<div class="support_top">
	 <h2>サポート</h2>
	</div>
  <div class="support_top_icon">
	 <!-- タイマーリンク -->
	 <div class="support_links">
	    <a href="timer.jsp"><img src="../images/タイマーアイコン(横長).png" class="support_link_icon"></a>
	 </div>
	
	 <!-- カスタマイズリンク -->
	 <div class="support_links">
	    <a href="customize.jsp"><img src="../images/カスタマイズアイコン(横長).png" class="support_link_icon"></a>
     </div>
	
 	 <!-- 電卓リンク -->
	  <div class="support_links">
          <!-- hrefを削除し、onclickイベントで子ウィンドウを開くように修正 -->
          <a href="#" onclick="openCalculator(); return false;"><img src="../images/電卓アイコン(横長).png" class="support_link_icon"></a>
      </div>
   </div>
<div class="back_button">
    <a href="../common/top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a>
</div>

 <script src="../js/calculator.js"></script>

<%@include file="../footer.jsp"  %>




