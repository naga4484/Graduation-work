<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="../css/teacher_function.css">

<title>提出物登録画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>提出物登録機能</h1>
<div class="main_contents">
    <!-- 学生検索フォーム -->
    <div class="teacher_side">
        <form action="Submissionsstudent.action">
            <h2>学生検索</h2>
            <c:if test="${none_error != null}">
                <p class="system_return_mes">${none_error}</p>
            </c:if>
            <label for="student_name">学生</label>
            <input type="text" id="student_name" name="student_name" placeholder="名前を入力してください">
            <label for="class_id">クラス</label>
            <select id="class_id" name="class_id">
                <c:forEach var="class_num" items="${class_num}">
                    <option value="${class_num.class_id}">${class_num.class_num}</option>
                </c:forEach>
            </select>
            <input type="submit" value="検索">
        </form>
    </div>
    
    <!-- 提出物登録フォーム -->
    <c:if test="${submissionsstudent != null}">
        <form action="Submissionsregistration.action">

            <div>
                <h2>提出物</h2>
                <c:if test="${distinct_error != null}">
                    <p class="system_return_mes">${distinct_error}</p>
                </c:if>
                <c:if test="${complete_mes != null}">
                    <p class="system_return_mes">${complete_mes}</p>
                </c:if>
                <label for="submission_name">提出物名</label>
                <input type="text" id="submission_name"maxlength="30" name="submission_name" placeholder="提出物名を入力してください" required>
                
                <label for="subject_id">科目</label>
                <select id="subject_id" name="subject_id" placeholder="科目を設定してください" required>
                    <c:forEach var="class_subject" items="${class_subject}">
                        <option value="${class_subject.subject_id}">${class_subject.subject_name}</option>
                    </c:forEach>
                </select>

                <label for="year">日付</label>
                <select id="year" name="year"></select>
                <select id="month" name="month"></select>
                <select id="date" name="date"></select>
                
                <input type="submit" value="登録">
            </div>

            <!-- 検索結果テーブル -->
            <div class="teacher_table">
                <h2>検索結果</h2>
                <div class="submissions_reg_head">
                	<div class="submission_reg_flag">
                		<p>提出登録</p>
                	</div>
                	<div class="submission_reg_id">
                		<p>学生ID</p>
                	</div>
                	<div class="submission_reg_name">
                		<p>学生氏名</p>
                	</div>
                </div>
                <c:forEach var="item" items="${submissionsstudent}">
	                <div class="submissions_reg_body">
	                	<div class="submission_reg_flag">
	                		<p><input type="checkbox" name="${item.student_id}" value="〇" checked></p>
	                	</div>
	                	<div class="submission_reg_id">
	                		<p>${item.student_id}</p>
	                	</div>
	                	<div class="submission_reg_name">
	                		<p>${item.name}</p>
	                	</div>
	                </div>
	            </c:forEach>
            </div>
        </form>
    </c:if>
</div>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/year.js"></script>
<%@ include file="../footer.jsp" %>
