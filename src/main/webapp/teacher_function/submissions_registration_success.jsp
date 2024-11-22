<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">
<div class="successPage">
<title>提出物登録完了画面</title>

<h1>登録完了</h1>
<p>提出物の登録が完了いたしました</p>
<form action="../teacher_function/submissions_registration.jsp" method="get">
    <button type="submit">完了</button>
</form>


</div>
<%@include file="../footer.jsp"  %>