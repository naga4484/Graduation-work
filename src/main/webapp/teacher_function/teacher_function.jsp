<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>教師機能一覧画面</title>

<link rel="stylesheet" type="text/css" href="../css/teacher.css">
<div class="teacher_top">
    <h1>教師機能</h1>
</div>

<div class="teacher_function_icon center-text">
	<a href="submissions_registration.jsp"id="registration"><img src="../images/提出物登録アイコン.png" class="teacher_link_icon">提出物登録</a>
	<a href="submissions_confirmation.jsp"id="confirmation"><img src="../images/提出物確認アイコン.png" class="teacher_link_icon">提出物確認</a>
	<a href="attendance.jsp"id="attendance"><img src="../images/出欠アイコン.png" class="teacher_link_icon">出欠</a>
	<a href="timetable_management.jsp"id="timetable"><img src="../images/時間割アイコン.png" class="teacher_link_icon">時間割</a>
	<a href="grade_registration_top.jsp"id="grade"><img src="../images/成績アイコン.png" class="teacher_link_icon">成績</a>
	<a href="Subjectlisttop.action"id="Subjectlisttop"><img src="../images/科目一覧アイコン.png" class="teacher_link_icon">科目一覧</a>
	<a href="student_registration.jsp"id="student"><img src="../images/学生情報アイコン.png" class="teacher_link_icon">学生情報</a>
	<a href=""id="authority"><img src="../images/権限アイコン.png" class="teacher_link_icon">権限</a>
	<a href=""id="seating"><img src="../images/座席表アイコン.png" class="teacher_link_icon">座席表</a>
	<a href=""id="group"><img src="../images/グループアイコン.png" class="teacher_link_icon">グループ</a>
	<a href=""id="trend"><img src="../images/傾向分析アイコン.png" class="teacher_link_icon">傾向分析</a>
	<a href="Timetable_template_set.action"id="template"><img src="../images/テンプレートアイコン.png" class="teacher_link_icon">科目テンプレ</a>
</div>
<div class="teacher_registration">
	<a href="teacher_registration.jsp">教師アカウントの登録</a>
</div>
<div class="common_back_button">
  <a href="../common/top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>