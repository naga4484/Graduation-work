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
			<c:when test="${sessionScope.my_submissions_list.size() > 0}">
				<div class="my_submissions_teacher_inner_head">
					<div class="my_submissions_teacher_inner_name">
						<p>提出物名</p>
					</div>
					<div class="my_submissions_teacher_inner_limit">
						<p>期限</p>
					</div>
					<div class="my_submissions_teacher_inner_submit">
						<p>提出状況</p>
					</div>
				</div>
				<c:forEach var="item" items="${my_submissions_list}" varStatus="loop">
					<div class="my_submissions_teacher_inner_body">
						<div class="my_submissions_teacher_inner_name">
							<p class="txt-limits-min">${item.name}</p>
						</div>
						<div class="my_submissions_teacher_inner_limit">
							<p>${item.create_date}</p>
						</div>
						<div class="my_submissions_teacher_inner_submit">
							<c:choose>
								<c:when test="${item.submissions_flag == true}">
									<p>提出済み</p>
								</c:when>
								<c:otherwise>
									<p>未提出</p>
								</c:otherwise>
							</c:choose>
						</div>
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