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
	<div>
		<c:choose>
			<c:when test="${sessionScope.sub_detail_send_list.size() > 0}">
				<table>
					<tr>
						<th>学生ID</th>
						<th>氏名</th>
						<th>提出状況</th>
						<th>提出済みファイル</th>
					</tr>
					<c:forEach var="item" items="${sessionScope.sub_detail_send_list}">
						<tr>
							<td>${item.student_id}</td>
							<td>${item.name}</td>
							<c:choose>
								<c:when test="${item.submissions_flag == true}">
									<td>提出済み</td>
									<td><a href="Submissions_sended_file_download.action?path=${item.submissions_my_name}&submissions_id=${item.submissions_id}&student_id=${item.student_id}" target="_parent">${item.submissions_my_name}</a></td>
								</c:when>
								<c:otherwise>
									<td>未提出</td>
									<td></td>
								</c:otherwise>
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