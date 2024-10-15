<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"  %>

<title>学生ログイン画面</title>

<h1>学生ログイン</h1>

<form action="Studentlogin.action" method="post">
	<p>${login_error}<p>
    <p>学生ID：</p><input type="text"required="required" name="student_id"> 
    <p>パスワード：</p><input type="text"required="required" name="password"> 
    

   	<button type="submit">ログイン</button> 
</form>

<%@include file="../footer.jsp"  %>