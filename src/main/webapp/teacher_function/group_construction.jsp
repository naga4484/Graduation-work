<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>グループ表示画面</title>

<h1>振り分け結果</h1>

<c:if test="${retry_mes != null}">
	<p>${retry_mes}</p>
</c:if>
<a href="Group_auto_create_retry.action" class="group_retry_button">再抽選</a>
<a href="Group_excel_dl.action" class="group_retry_button">Excelファイルでダウンロードする(未完成[壊れたファイルがDLされる])</a>
<div>
	<c:forEach var="entry" items="${group_list}" varStatus="count">
		<div class="group_create">
			<h1 class="group_create_title">グループ${count.index + 1}</h1>
			<c:forEach var="item" items="${entry.value}" varStatus="reader">
				<p class="group_create_member">${item.name}</p>
			</c:forEach>
		</div>
	</c:forEach>
</div>


<div class="common_back_button">
  <a href="../teacher_function/group_auto_create.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>