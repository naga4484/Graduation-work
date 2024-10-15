<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>教師機能一覧画面</title>

<h1>教師機能</h1>

<div class="teacher_function_iocn">
	<a href="">提出物登録</a>
	<a href="">提出物確認</a>
	<a href="">出欠</a>
	<a href="">時間割</a>
	<a href="">成績</a>
	<a href="student_registration.jsp">学生情報</a>
	<a href="">権限</a>
	<a href="">座席表</a>
	<a href="">グループ</a>
	<a href="">傾向分析</a>
	<a href="">科目テンプレ</a>
</div>
<div class="teacher_registration">
	<a href="">教師アカウントの登録</a>
</div>
<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>