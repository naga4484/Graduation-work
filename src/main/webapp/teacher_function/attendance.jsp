<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">
<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>出欠管理画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>
<h1>出欠管理機能</h1>

<c:if test="${not_attendancestudent != null}">
	<button id="up_not_attendance">欠席学生の情報を登録する</button>
	<div id="attendance_box">
	<div class="scroll_table">
		<form  action="AttendanceNotregistration.action">
		  <div class="table-container">
			<table>
				<tr>
				
					<th class="headword">病欠</th>
					<th class="headword">公欠</th>
					<th class="headword">遅刻</th>
					<th class="headword">その他</th>
					<th class="headword">学生ID</th>
					<th class="headword">学生氏名</th>
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
			</div>
			</div>
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
	<div class="teacher_table">
		<h2>学生出欠登録</h2>
		<table>
			<tr>
				<th class="headword">出欠可否</th>
				<th class="headword">学生ID</th>
				<th class="headword">学生氏名</th>
			</tr>
			<c:forEach var="item" items="${attendancsstudentlist}">
		  		<tr>
		  			<td><input type="checkbox" name="${item.student_id}" value="出席" checked></td>
		  			<td>${item.student_id}</td>
		  			<td>${item.name}</td>
		  		</tr>
			</c:forEach>
		</table>
	</div>
	
		<input type="submit" value="登録">
	</form>
	<c:if test="${samplelist.size() > 0}">
		<iframe src="attendance_list.jsp"></iframe>
	</c:if>
</c:if>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/attendance.js"></script>
<%@include file="../footer.jsp"  %>