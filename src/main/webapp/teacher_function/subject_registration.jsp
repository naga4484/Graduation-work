<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>科目情報登録画面</title>

<h1>科目情報登録機能</h1>
<form action="Subjectregistration.action" method="post">
	
    <label>科目ID</label>
    <input type="text" name="subject_id" placeholder="科目IDを入力してください"><br><br>
    
    <label>科目ID</label>
    <input type="text" name="subject_name" placeholder="科目名を入力してください"><br><br>
    
	<label>総単位数</label>
    <input type="text" name="total_unit" placeholder="総単位数を入力してください">
    
    <input type="hidden" name="class_id" value="${account.class_id}">

   	<button type="submit">登録</button> 
</form>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>