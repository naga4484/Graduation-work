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
<link rel="stylesheet" type="text/css" href="../css/teacher_function.css">
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${sessionScope.sub_detail_send_list.size() > 0}">
				<div class="submissions_detail_inner_head">
					<div class="submissions_detail_inner_id">
						<p>学生ID</p>
					</div>
					<div class="submissions_detail_inner_name">
						<p>氏名</p>
					</div>
					<div class="submissions_detail_inner_submit">
						<p>提出状況</p>
					</div>
					<div class="submissions_detail_inner_file">
						<p>提出済みファイル</p>
					</div>
				</div>
				<c:forEach var="item" items="${sessionScope.sub_detail_send_list}">
					<div class="submissions_detail_inner_body">
						<div class="submissions_detail_inner_id">
							<p>${item.student_id}</p>
						</div>
						<div class="submissions_detail_inner_name">
							<p>${item.name}</p>
						</div>
						<c:choose>
							<c:when test="${item.submissions_flag == true}">
								<div class="submissions_detail_inner_submit">
									<p>提出済み</p>
								</div>
								<div class="submissions_detail_inner_file">
									<p><a href="Submissions_sended_file_download.action?path=${item.submissions_my_name}&submissions_id=${item.submissions_id}&student_id=${item.student_id}" target="_parent">${item.submissions_my_name}</a></p>
								</div>
							</c:when>
							<c:otherwise>
								<div class="submissions_detail_inner_submit">
									<p>未提出</p>
								</div>
								<div class="submissions_detail_inner_file">
									<p></p>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
<%@include file="../footer.jsp"  %>