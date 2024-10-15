<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"  %>

<title>教師ログイン画面</title>

<h1>教師ログイン</h1>

<form action="Teacherlogin.action" method="post">
	<p>${login_error}<p>
    <p>教師ID：</p><input type="text"required="required" name="teacher_id"> 
    <p>パスワード：</p><input type="text"required="required" name="password"> 
    


   	<button type="submit">ログイン</button> 
</form>

<%@include file="../footer.jsp"  %>