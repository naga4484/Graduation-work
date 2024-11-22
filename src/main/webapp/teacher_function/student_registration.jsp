<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>学生情報登録画面</title>
<div class="teacher_center">

<h1>学生情報登録機能</h1>

<form action="Studentregistration.action" method="post">
	
    <label>氏名</label>
    <input type="text" name="name" required="required" placeholder="氏名を入力してください"><br><br>
    
    <label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select><br><br>
	
	<p>${student_id_duplication_error}</p>
	<label>学生ID</label>
    <input type="text" name="student_id" required="required" placeholder="学生IDを入力してください">
   	<button type="submit">登録</button> 
</form>
</div>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>