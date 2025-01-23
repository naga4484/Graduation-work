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
<link rel="stylesheet" type="text/css" href="../css/attendance.css">
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
	<!-- チャット欄 -->
	<div>
		<c:choose>
			<c:when test="${sessionScope.samplelist.size() > 0}">
				<div class="attendance_inner_list_head">
					<div class="attendance_inner_list_name">
						<p>氏名</p>
					</div>
					<div class="attendance_inner_list_kind">
						<p>出欠種別</p>
					</div>
				</div>
				<c:forEach var="item" items="${sessionScope.samplelist}">
					<div class="attendance_inner_list_body">
						<div class="attendance_inner_list_name">
							<p>${item.name}</p>
						</div>
						<div class="attendance_inner_list_kind">
							<c:choose>
								<c:when test="${item.attendance_kind_id.equals('0')}">
									<p style="color:red">その他</p>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('1')}">
									<p>出席</p>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('2')}">
									<p style="color:red">病欠</p>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('3')}">
									<p style="color:red">公欠</p>
								</c:when>
								<c:when test="${item.attendance_kind_id.equals('4')}">
									<p style="color:red">遅刻</p>
								</c:when>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>本日のデータは登録されていません</p>
			</c:otherwise>
		</c:choose>
	</div>
<%@include file="../footer.jsp"  %>