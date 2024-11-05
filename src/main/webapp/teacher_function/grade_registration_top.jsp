<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>成績登録トップ画面</title>

<h1>成績登録</h1>

<div class="teacher_function_iocn">
	<a href="semester_result.jsp">学期成績</a>
	<a href="submissions_result.jsp">提出物成績</a>
	<a href="subject_result.jsp">科目成績</a>
</div>
<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"%>