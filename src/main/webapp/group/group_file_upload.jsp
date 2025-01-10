<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../css/group.css">

<title>グループ作成参加画面</title>
<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1 class="page_title">共有</h1>

<div class="chat_left_content">
	<c:forEach var="item" items="${my_group_list}">
		<a href="Group_file_upload_datail.action?group_id=${item.group_id}"><div class="chat_partner_select">
			<h4>${item.group_name}</h4>
		</div></a>
	</c:forEach>
</div>
<c:if test="${file_list != null}">

	<iframe src="group_file_inner.jsp" id="chat_iframe"></iframe>
	
	
	<!-- 共有アイテム送信欄 -->
	<div class="chat_submit_area">
		<form action="Group_file_upload_submit.action?group_id=${select_id}" enctype="multipart/form-data" method="POST">
	        <label for="file">Choose file:</label>
	        <input type="file" name="file">
	        <input type="submit" value="Upload">
	    </form>
	</div>
	
	
</c:if>


<div class="common_back_button">
  <a href="../group/group_top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<%@include file="../footer.jsp"  %>