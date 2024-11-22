<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">
<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>出欠管理画面</title>

<h1>出欠管理機能</h1>

<c:if test="${not_attendancestudent != null}">
	<button id="up_not_attendance">欠席学生の情報を登録する</button>
	<div id="attendance_box">
		<form  action="AttendanceNotregistration.action">
			<table>
				<tr>
					<th>病欠</th>
					<th>公欠</th>
					<th>遅刻</th>
					<th>その他</th>
					<th>学生ID</th>
					<th>学生氏名</th>
				</tr>
				<c:forEach var="item" items="${not_attendancestudent}">
			  		<tr>
			  			<td><input type="radio" name="${item.student_id}" value="病欠" checked></td>
			  			<td><input type="radio" name="${item.student_id}" value="公欠"></td>
			  			<td><input type="radio" name="${item.student_id}" value="遅刻"></td>
			  			<td><input type="radio" name="${item.student_id}" value="その他"></td>
			  			<td>${item.student_id}</td>
			  			<td>${item.name}</td>
			  		</tr>
				</c:forEach>
			</table>
			<input type="submit" value="登録">
		</form>
	</div>
	<div id="attendance_box_back">
	</div>
</c:if>
<c:if test="${attendance_finish != null}">
	<p>${attendance_finish}</p>
</c:if>

<form action="Attendancesearch.action">
	<h2>学生検索</h2>
	<c:if test="${none_error != null}">
	<p>${none_error}</p>
	</c:if>
	<label>学生</label>
	<input type="text" name="student_name" placeholder="名前を入力してください">
	
	<label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select>
	
	<input type="submit" value="検索">
</form>
<c:if test="${attendancsstudentlist.size() > 0}">
	<form  action="Attendanceregistration.action">
		<table>
			<tr>
				<th>出欠可否</th>
				<th>学生ID</th>
				<th>学生氏名</th>
			</tr>
			<c:forEach var="item" items="${attendancsstudentlist}">
		  		<tr>
		  			<td><input type="checkbox" name="${item.student_id}" value="出席" checked></td>
		  			<td>${item.student_id}</td>
		  			<td>${item.name}</td>
		  		</tr>
			</c:forEach>
		</table>
		<input type="submit" value="登録">
	</form>
	<c:if test="${samplelist.size() > 0}">
		<iframe src="attendance_list.jsp"></iframe>
	</c:if>
</c:if>

<a href="../common/top.jsp">TOP</a>
<script src="../js/attendance.js"></script>
<%@include file="../footer.jsp"  %>