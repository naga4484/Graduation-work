<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/my_submissions.css">

<title>提出物管理画面</title>

<h1>提出物管理機能</h1>
<c:if test="${my_submissions != null}">
	<div id="my_submissions_box">
		<h1>提出物提出</h1>
		<div>
			<form action="My_submissions_send.action" enctype="multipart/form-data" method="POST">
		        <label for="file">Choose file:</label>
		        <input type="file" name="file">
		        <input type="submit" value="Upload">
		    </form>
	    </div>
	</div>
	<div id="my_submissions_box_back">
	</div>
</c:if>
<c:if test="${my_send_submissions != null}">
	<div id="my_submissions_box">
		<h1>提出状況の確認</h1>
		<div>
			<form action="My_submissions_send_update.action" enctype="multipart/form-data" method="POST">
		        <label for="file">Choose file:</label>
		        <input type="file" name="file">
		        <input type="submit" value="Upload">
		    </form>
	    </div>
	    <a href="My_submissions_management_update_download.action">提出済みのファイルの確認</a>
	</div>
	<div id="my_submissions_box_back">
	</div>
</c:if>
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
							<td>提出済み</td>
							<td><a href="My_submissions_management_update.action?submissions_id=${item.submissions_id}" target="_parent">提出状況変更へ</a></td>
						</c:when>
						<c:otherwise>
							<td>未提出</td>
							<td><a href="My_submissions_management.action?submissions_id=${item.submissions_id}" target="_parent">提出物詳細へ</a></td>
						</c:otherwise>
					</c:choose>
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