<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">


<title>提出物詳細画面</title>

<h1>提出物詳細機能</h1>
<div>
	<form action="Submissions_detail_delete.action">
		<input type="hidden" value="${distinctsubmissions_id.submissions_id}" name="submissions_id">
		<input type="submit" value="削除">
	</form>
</div>


<form action="Submissions_detail_change.action">
	<div>
		<h2>提出物</h2>
		<c:if test="${distinct_error != null}">
			<p>${distinct_error}</p>
		</c:if>
		<label>提出物名</label>
		<input type="text" name="submission_name" placeholder="提出物名を入力してください" maxlength="30"required="required" value="${distinctsubmissions_id.name}">
		
		<label>科目</label>
	    <select name="subject_id">
		    <c:forEach var="class_subject" items="${class_subject}">
		    	<option value="${class_subject.subject_id}">${class_subject.subject_name}</option>
		    </c:forEach>
		</select>
		<h2>期限</h2>
		<select id="year" name="year"></select>
		<select id="month" name="month"></select>
		<select id="date" name="date"></select>
		<input type="hidden" value="${distinctsubmissions_id.submissions_id}" name="submissions_id">
		
		<input type="submit" value="変更">
	</div>
</form>

<a href="../common/top.jsp">TOP</a>
<script src="../js/year.js"></script>
<%@include file="../footer.jsp"  %>