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
		<table>
			<tr>
				<th>時間</th>
				<th>分</th>
				<th>スケジュール</th>
			</tr>
			<c:forEach var="item" items="${sessionScope.cal_list}">
		  		<tr>
		  			<td>${item.setting_date.substring(0,2)}</td>
		  			<td>${item.setting_date.substring(3,5)}</td>
		  			<td class="txt-limits">${item.schedule_content}</td>
		  		</tr>
			</c:forEach>
		</table>
	</div>
<script src="../js/text_limit.js"></script>
<%@include file="../footer.jsp"  %>