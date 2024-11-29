<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>学期成績登録画面</title>


<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>学期成績</h1>
<form action="Semester_result_class.action">
	<label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select>
	<input type="submit" value="検索">
</form>

<c:if test="${semester_result_student_list != null}">
<c:if test="${reg_mes != null}">
	<p>${reg_mes}</p>
</c:if>
<form action="Semester_result_registration.action">
	<div>
		<h2>成績情報</h2>
		<label>学期</label>
		<select name="semester">
			<c:forEach begin="1" end="3" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select>
		<label>点数</label>
    	<input type="number" name="result" placeholder="点数を入力してください" min="0" max="100" required>
    	<label>5段階評価</label>
		<select name="evaluation">
			<c:forEach begin="1" end="5" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select>
		<label>コメント</label>
		<textarea name="comment" id="comment"></textarea>
		<input type="hidden" value="${semester_result_student_list[0].class_id}" name="class_id">
		<input type="submit" value="登録">
	</div>
	<div class="teacher_table">
		<table>
			<tr>
				<th>選択</th>
				<th>学生ID</th>
				<th>学生氏名</th>
			</tr>
			<c:forEach var="item" items="${semester_result_student_list}" varStatus="j">
		  		<tr>
		  			<c:choose>
						<c:when test="${j.index == 0}">
							<td><input type="radio" name="select_con" value="${item.student_id}" id="${item.student_id}" checked></td>
    					</c:when>
    					<c:otherwise>
    						<td><input type="radio" name="select_con" value="${item.student_id}" id="${item.student_id}"></td>
						</c:otherwise>
					</c:choose>
		  			<td>${item.student_id}</td>
		  			<td>${item.name}</td>
		  		</tr>
			</c:forEach>
		</table>
	</div>
</form>
</c:if>
<div class="common_back_button">
 <a href="../teacher_function/grade_registration_top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>

<%@include file="../footer.jsp"%>