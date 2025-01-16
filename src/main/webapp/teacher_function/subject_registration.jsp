<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>科目情報登録画面</title>


<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>
<div class="teacher_center1">

<h1>科目情報登録機能</h1>
<c:if test="${subject_id_duplication_error != null}">
	<p class="system_return_mes">${subject_id_duplication_error }</p>
</c:if>
</div>

<form action="Subjectregistration.action">
<div class="teacher_center2">
	<p>${errorMessage }</p>
    <label>科目ID</label>
    <input type="text" maxlength="5" name="subject_id" placeholder="科目IDを入力してください" required><br>
    
    <label>科目名</label>
    <input type="text" maxlength="30"  name="subject_name"  placeholder="科目名を入力してください"required> <br>
    
	<label>総単位数</label>
    <input type="number" max="999" min="1" name="total_unit" placeholder="総単位数を入力してください" required><br>
    
    <label>科目イメージカラー</label>
    <input type="color" name="subject_color" value="#cce5ff"><br><br>
    
    
    <input type="hidden" name="class_id" value="${account.class_id}">
</div>
<div class="subregis_submit">
   	<button type="submit">登録</button> 
</div>
</form>
<div class="common_back_button">
 <a href="../teacher_function/subject_list.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>

<%@include file="../footer.jsp"  %>