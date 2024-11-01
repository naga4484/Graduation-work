<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>学期成績登録画面</title>

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
<form action="Semester_result_registration.action">
	<div>
		<h2>成績情報</h2>
		<c:if test="${distinct_error != null}">
			<p>${distinct_error}</p>
		</c:if>
		<c:if test="${complete_mes != null}">
			<p>${complete_mes}</p>
		</c:if>
		
		<label>学期</label>
		<select name="semester">
			<c:forEach begin="1" end="3" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select>
		<label>点数</label>
    	<input type="text" name="result" placeholder="点数を入力してください">
    	<label>5段階評価</label>
		<select name="evaluation">
			<c:forEach begin="1" end="5" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select>
		<label>コメント</label>
		<input type="textarea" name="comment">
		<input type="submit" value="登録">
	</div>
	<div>
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
							<td><input type="radio" name="semester" value="〇" id="${item.student_id}" checked></td>
    					</c:when>
    					<c:otherwise>
    						<td><input type="radio" name="semester" value="〇" id="${item.student_id}" for="${item.student_id}"></td>
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

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"%>