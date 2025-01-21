<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="../css/teacher_function.css">
<link rel="stylesheet" type="text/css" href="../css/attendance.css">


<title>提出物詳細画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>提出物詳細機能</h1>

<form action="Submissions_detail_change.action">
    <div class="teacher_center">
        <h2>提出物</h2>
        <!-- エラーメッセージの表示 -->
        <c:if test="${distinct_error != null}">
            <p class="system_return_mes">${distinct_error}</p>
        </c:if>

        <!-- 提出物情報の入力フォーム -->
        <label>提出物名</label>
        <input type="text" name="submission_name" placeholder="提出物名を入力してください" maxlength="30" required="required" value="${distinctsubmissions_id.name}">

        <label>科目</label>
        <select name="subject_id">
            <c:forEach var="class_subject" items="${class_subject}">
                <option value="${class_subject.subject_id}">${class_subject.subject_name}</option>
            </c:forEach>
        </select>

        <label>期限</label>
        <select id="year" name="year"></select>
        <select id="month" name="month"></select>
        <select id="date" name="date"></select>

        <input type="hidden" name="submissions_id" value="${distinctsubmissions_id.submissions_id}">
        <input type="submit" value="変更">
    </div>
</form>

<!-- 提出物削除フォーム -->
<div class="delete">
    <form action="Submissions_detail_delete.action">
        <input type="hidden" name="submissions_id" value="${distinctsubmissions_id.submissions_id}">
        <input type="submit" class="delete_submit" value="提出物の削除">
    </form>
</div>

<c:if test="${sub_detail_send_list != null}">
	<div class="teacher_table">
		<h3 class="submissions_detail_tag">提出状況の確認</h3>
		<iframe class="submissions_detail_inner_iframe" src="submissions_detail_inner.jsp"></iframe>
	</div>
</c:if>

<div class="common_back_button">
 <a href="../teacher_function/submissions_confirmation.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/year.js"></script>
<%@ include file="../footer.jsp" %>
