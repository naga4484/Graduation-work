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
<style>
    body {
        margin: 0;
        overflow-x: hidden;
    }
    body::-webkit-scrollbar {
    width: 8px;
	}
	body::-webkit-scrollbar-thumb {
	    background-color: #888; 
	    border-radius: 10px;
	     height:50px;
	}
	body::-webkit-scrollbar-track {
	    background-color: #f9ffff;
	    border-radius: 10px;
	}
</style>
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${sessionScope.my_management_date_list.size() > 0}">
				<c:forEach var="item_date" items="${my_management_date_list}">
					<div class="my_management_schedule">
						<h2>${item_date.calender_date}</h2>
						<c:forEach var="item_content" items="${my_management_content_list}">
							<c:if test="${item_date.calender_date == item_content.calender_date}">
								<p id="my_management_schedule_data">${item_content.setting_date}</p>
								<p class="txt-limits" id="my_management_schedule_content">${item_content.schedule_content}</p>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
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