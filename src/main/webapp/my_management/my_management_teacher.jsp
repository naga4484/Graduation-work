<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/my_management.css">


<title>自己管理画面</title>

<h1 class="page_title">自己管理機能</h1>

<div class="my_management_schedule_area">
	<h3>近況のスケジュール</h3>
	<c:choose>
		<c:when test="${my_management_date_list.size() > 0}">
			<iframe src="my_management_schedule_list.jsp" class="my_management_schedule_iframe"></iframe>
		</c:when>
		<c:otherwise>
			<div class="my_management_schedule_none">
				<p>予定はありません</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<div class="my_management_submission_area">
	<h3>設定済み提出物一覧</h3>
	<c:choose>
		<c:when test="${my_submissions_list.size() > 0}">
			<iframe src="my_management_list_teacher.jsp" class="my_management_iframe_teacher"></iframe>
			<br><a href="../teacher_function/submissions_confirmation.jsp">提出物一覧画面へ</a>
		</c:when>
		<c:otherwise>
			<div class="my_management_schedule_none">
				<p>提出物はありません</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>


<div class="common_back_button">
  <a href="../common/top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<script src="../js/text_limit.js"></script>
<%@include file="../footer.jsp"  %>