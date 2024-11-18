<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"
	href="../css/teacher_function.css">

<title>提出物登録画面</title>

<h1>提出物登録機能</h1>
<div class="main_contents">
	<div class="student_search">
	<form action="Submissionsstudent.action">
		<h2>学生検索</h2>
		<c:if test="${none_error != null}">
			<p>${none_error}</p>
		</c:if>
		<label>学生</label> <input type="text" name="student_name"
			placeholder="名前を入力してください"> <label>クラス</label> <select
			name="class_id">
			<c:forEach var="class_num" items="${class_num}">
				<option value="${class_num.class_id}">${class_num.class_num}</option>
			</c:forEach>
		</select> <input type="submit" value="検索">
	</form>
	</div>
	
	<c:if test="${submissionsstudent != null}">
		<form action="Submissionsregistration.action">
			<div class="submission_registration">
				<h2>提出物</h2>
				<c:if test="${distinct_error != null}">
					<p>${distinct_error}</p>
				</c:if>
				<c:if test="${complete_mes != null}">
					<p>${complete_mes}</p>
				</c:if>
				<label>提出物名</label> <input type="text" name="submission_name"placeholder="提出物名を入力してください" required="required"> 
				<label>科目</label>
				<select name="subject_id">
					<c:forEach var="class_subject" items="${class_subject}">
						<option value="${class_subject.subject_id}">${class_subject.subject_name}</option>
					</c:forEach>
				</select> 
				<label>日付</label> 
				<select id="year" name="year"></select> 
				<select id="month" name="month"></select> 
				<select id="date" name="date"></select>

				<input type="submit" value="登録">
			</div>
			<div class="teacher_table">
				<table>
				<h2>検索結果</h2>
					<tr>
						<th class="headword">提出登録</th>
						<th class="headword" >学生ID</th>
						<th class="headword">学生氏名</th>
					</tr>
					<c:forEach var="item" items="${submissionsstudent}">
						<tr>
							<td><input type="checkbox"  name="${item.student_id}"
								value="〇" checked></td>
							<td>${item.student_id}</td>
							<td>${item.name}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</c:if>
</div>

<a href="../teacher_function/teacher_function.jsp">戻る</a>
<script src="../js/year.js"></script>
<%@include file="../footer.jsp"%>