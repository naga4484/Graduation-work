<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>科目情報登録画面</title>

<h1>科目情報登録機能</h1>
<c:if test="${subject_id_duplication_error != null}">
	<p>${subject_id_duplication_error }</p>
</c:if>
<form action="Subjectregistration.action" method="post">
	<p>${errorMessage }</p>
    <label>科目ID</label>
    <input type="text" maxlength="5" name="subject_id" placeholder="科目IDを入力してください"><br><br>
    
    <label>科目名</label>
    <input type="text" maxlength="30"  name="subject_name"  placeholder="科目名を入力してください"><br><br>
    
	<label>総単位数</label>
    <input type="number" max="999" min="1" name="total_unit" placeholder="総単位数を入力してください"><br><br>
    
    <label>科目イメージカラー</label>
    <input type="color" name="subject_color" value="#cce5ff"><br><br>
    
    <input type="hidden" name="class_id" value="${account.class_id}">

   	<button type="submit">登録</button> 
</form>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>