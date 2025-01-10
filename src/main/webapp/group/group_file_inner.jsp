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
</head>
<body>
	<!-- チャット欄 -->
	<div class="chat_right_content">
		<div>
			<c:choose>
				<c:when test="${sessionScope.file_list.size() > 0}">
					<c:forEach var="item" items="${sessionScope.file_list}">
						<c:choose>
							<c:when test="${item.user_id == sessionScope.user.user_id}">
								<div class="chat_my_content">
									<p class="chat_name">${item.name}</p>
									<a href="Group_file_download.action?name=${item.share_item_path}" target="_parent"><p class="chat_content">${item.share_item_path}</p></a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="chat_partner_content">
									<p class="chat_name">${item.name}</p>
									<a href="Group_file_download.action?name=${item.share_item_path}" target="_parent"><p class="chat_content">${item.share_item_path}</p></a>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>共有履歴はありません</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<%@include file="../footer.jsp"  %>