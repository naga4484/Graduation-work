<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSSに関しては、機能ファルダごとにCSSを分けている -->
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
</head>
<body>
<div class="main_content">

<title>ログインユーザー選択画面</title>

<h1>ログインユーザー選択</h1>

<p>${logout_mes}</p>

<a href="student_login.jsp">学生ユーザーはこちら</a>
<a href="teacher_login.jsp">教師ユーザーはこちら</a>



<%@include file="../footer.jsp"  %>