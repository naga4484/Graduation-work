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
				<c:when test="${sessionScope.chat_list.size() > 0}">
					<p class="chat_date_block_date">${sessionScope.chat_list[0].chat_date.substring(0,11)}</p>
					<c:set var="item_date">${sessionScope.chat_list[0].chat_date.substring(0,11)}</c:set>
					<c:forEach var="item" items="${sessionScope.chat_list}">
						<c:if test="${item.chat_date.substring(0,11).equals(item_date) == false}">
							<p class="chat_date_block_date">${item.chat_date.substring(0,11)}</p>
							<c:set var="item_date">${item.chat_date.substring(0,11)}</c:set>
						</c:if>
							<c:choose>
								<c:when test="${item.user_id == sessionScope.user.user_id}">
									<div class="chat_my_content">
										<p class="chat_name">${item.name}</p>
										<p class="chat_date">${item.chat_date.substring(11,17)}</p>
										<p class="chat_content">${item.chat_content}</p>
									</div>
								</c:when>
								<c:otherwise>
									<div class="chat_partner_content">
										<p class="chat_name">${item.name}</p>
										<p class="chat_content">${item.chat_content}</p>
										<p class="chat_date">${item.chat_date.substring(11,17)}</p>
									</div>
								</c:otherwise>
							</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>チャット履歴はありません</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<%@include file="../footer.jsp"  %>