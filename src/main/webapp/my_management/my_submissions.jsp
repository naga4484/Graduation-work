<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/my_submissions.css">

<title>提出物管理画面</title>

<h1>提出物管理機能</h1>

<div id="my_submissions_box">
	<h1>一旦未完成の機能を残すな！！</h1>
	<p>一旦未完成です</p>
</div>
<div id="my_submissions_box_back">
</div>

<div>
	<c:if test="${my_submissions_list != null}">
		<table>
			<tr>
				<th>提出物名</th>
				<th>期限</th>
				<th>提出済</th>
				<th></th>
			</tr>
			<c:forEach var="item" items="${my_submissions_list}">
				<tr>
					
					<td>${item.name}</td>
					<td>${item.create_date}</td>
					<c:choose>
						<c:when test="${item.submissions_flag == true}">
							<td>○</td>
						</c:when>
						<c:otherwise>
							<td>✕</td>
						</c:otherwise>
					</c:choose>
					<td><a href="My_submissions_management.action?submissions_id=${item.submissions_id}" target="_parent">提出物詳細へ</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
<div class="common_back_button">
  <a href="../my_management/my_management.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<script src="../js/my_submissions.js"></script>
<%@include file="../footer.jsp"  %>