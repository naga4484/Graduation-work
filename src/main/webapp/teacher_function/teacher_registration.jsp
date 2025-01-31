<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>教師アカウント画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
<div class="teacher_center1">
<h1>教師アカウント機能</h1>

<c:if test="${comp_mes != null}">
	<p class="system_return_mess">${comp_mes}</p>
</c:if>
</div>
<div class="teacher_center2">
<form action="Teacherregistration.action" method="post">
	
    <label>氏名</label>
    <input type="text" name="name" placeholder="氏名を入力してください" required value="${entered_name}">
    
    <label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<c:choose>
	    		<c:when test="${class_num.class_id == entered_class_id}">
	    			<option value="${class_num.class_id}" selected>${class_num.class_num}</option>
	    		</c:when>
	    		<c:otherwise>
	    			<option value="${class_num.class_id}">${class_num.class_num}</option>
	    		</c:otherwise>
	    	</c:choose>
	    		
	    </c:forEach>
	</select>
	
	<p>${teacher_id_duplication_error}</p>
	<label>教師ID</label>
    <input type="text" name="teacher_id" placeholder="教師IDを入力してください" value="${entered_teacher_id}" required>
    
    <label>メールアドレス</label>
    <input type="email" name="email" placeholder="メールアドレスを入力してください" value="${entered_email}" required>
    <br><br>
   	<button type="submit">登録</button> 
</form>
</div>
<div class="common_back_button">
  <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>