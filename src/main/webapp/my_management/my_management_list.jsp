<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<title>ログインユーザー選択画面</title>
<!-- CSSに関しては、機能ファルダごとにCSSを分けている -->
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
<link rel="stylesheet" type="text/css" href="../css/account.css">
<link rel="stylesheet" type="text/css" href="../css/group.css">
<link rel="stylesheet" type="text/css" href="../css/my_management.css">
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${sessionScope.my_submissions_list.size() > 0}">
				<table class="my_management_inner_content">
					<tr>
						<th>提出物名</th>
						<th>期限</th>
						<th>提出済</th>
						<th></th>
					</tr>
					<c:forEach var="item" items="${my_submissions_list}">
						<tr>
							
							<td><p class="txt-limits">${item.name}</p></td>
							<td>${item.create_date}</td>
							<c:choose>
								<c:when test="${item.submissions_flag == true}">
									<td>提出済み</td>
								</c:when>
								<c:otherwise>
									<td>未提出</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>
					<p>提出物はありません</p>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
<script src="../js/text_limit.js"></script>
<%@include file="../footer.jsp"  %>