<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" %>
<img src="../images/menu.png" class="menu_var" id="menu_var">
<div id="menu_box">
	<p class="menu_title">menu</p>
	
		<c:choose>
		    <c:when test="${account.account_kind == \"教師\"}">
		    <div class="menu_box_contents">
		        <a href="../teacher_function/teacher_function.jsp" class="menu_links">教師</a>
		    </div>
		    </c:when>
		</c:choose>
	
	<div class="menu_box_contents">
		<a href="../my_management/My_management_top.action" class="menu_links">自己管理</a>
	</div>
	<div class="menu_box_contents">
		<a href="../group/group_top.jsp" class="menu_links">グループ管理</a>
	</div>
	<div class="menu_box_contents">
		<a href="" class="menu_links">アンケート</a>
	</div>
	<div class="menu_box_contents">
		<a href="" class="menu_links">効率化</a>
	</div>
	<div class="menu_box_contents">
		<a href="../common/top.jsp" class="menu_links">TOP</a>
	</div>
	<div class="menu_box_contents">
		<a href="../account/Logout.action" class="logout_links">ログアウト</a>
	</div>
</div>
<div id="menu_box_back">
</div>