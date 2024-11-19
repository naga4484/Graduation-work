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
	<div>
		<c:choose>
			<c:when test="${sessionScope.samplelist.size() > 0}">
				<table>
					<tr>
						<th>氏名</th>
						<th>出欠種別</th>
					</tr>
					<c:forEach var="item" items="${sessionScope.samplelist}">
						<tr>
							<td>${item.name}</td>
							<c:choose>
								<c:when test="${item.attendance_kind_id.equals('0')}">
									<td>その他</td>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('1')}">
									<td>出席</td>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('2')}">
									<td>病欠</td>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('3')}">
									<td>公欠</td>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('4')}">
									<td>遅刻</td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>本日のデータは登録されていません</p>
			</c:otherwise>
		</c:choose>
	</div>
<%@include file="../footer.jsp"  %>