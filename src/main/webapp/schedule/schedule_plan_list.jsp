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
<link rel="stylesheet" type="text/css" href="../css/schedule.css">
</head>
<body>
	<div>
		<c:forEach var="item" items="${sessionScope.cal_list}" varStatus="loop">
			<div class="my_schedule_main_table_area">
				<div class="my_schedule_main_table_area_date">
					<p>${item.setting_date}</p>
				</div>
				<div class="my_schedule_main_table_area_content">
					<p class="txt-limits">${item.schedule_content}</p>
				</div>
			</div>
		</c:forEach>
	</div>
<script src="../js/text_limit.js"></script>
<%@include file="../footer.jsp"  %>