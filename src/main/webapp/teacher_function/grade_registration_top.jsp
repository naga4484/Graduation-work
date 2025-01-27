<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>成績登録トップ画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>成績登録</h1>
<div class="teacher_link">
	<div class="teacher_function_iocn">
		<a href="semester_result.jsp">学期成績</a>
		<a href="submissions_result.jsp">提出物成績</a>
		<a href="subject_result.jsp">科目成績</a>
	</div>
</div>
<p class="result_check_links"><a href="Result_check.action">各種成績を確認する</a></p>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>



<%@include file="../footer.jsp"%>