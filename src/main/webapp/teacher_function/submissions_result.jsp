<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>提出物成績登録画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>提出物成績</h1>
<div class="left_form_content">
	<form action="Submissions_result_class.action">
		<label>クラス</label>
	    <select name="class_id">
		    <c:forEach var="class_num" items="${class_num}">
		    	<option value="${class_num.class_id}">${class_num.class_num}</option>
		    </c:forEach>
		</select>
		<input type="submit" value="検索">
	</form>
</div>

<c:if test="${submissions_result_student_list != null}">
	<div class="left_form_content">
		<c:if test="${reg_mes != null}">
			<p class="system_return_mes">${reg_mes}</p>
		</c:if>
	</div>
	<form action="Submissions_result_registration.action">
		<div class="left_form_content">
			<h2>成績情報</h2>
			<label>提出物</label>
		    <select name="submissions_id">
			    <c:forEach var="class_submissions" items="${submissions_result_list}">
			    	<option value="${class_submissions.submissions_id}">${class_submissions.name}</option>
			    </c:forEach>
			</select>
			<label>点数</label>
	    	<input type="number" name="result" placeholder="点数を入力してください" min="0" max="100" required style="width:180px; max-width:180px;">
	    	<label>5段階評価</label>
			<select name="evaluation">
				<c:forEach begin="1" end="5" var="i">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<label>コメント</label>
			<textarea name="comment" id="comment"></textarea>
			<input type="hidden" value="${submissions_result_student_list[0].class_id}" name="class_id">
			<input type="submit" value="登録">
		</div>
		<div class="teacher_table">
			<div class="result_table_head">
				<div class="result_table_button">
					<p>選択</p>
				</div>	
				<div class="result_table_id">
					<p>学生ID</p>
				</div>	
				<div class="result_table_name">
					<p>学生氏名</p>
				</div>	
			</div>
			<c:forEach var="item" items="${semester_result_student_list}" varStatus="j">
				<div class="result_table_body">
					<c:choose>
						<c:when test="${j.index == 0}">
							<div class="result_table_button">
								<p><input type="radio" name="select_con" value="${item.student_id}" id="${item.student_id}" checked></p>
							</div>
	   					</c:when>
	   					<c:otherwise>
		   					<div class="result_table_button">
								<p><input type="radio" name="select_con" value="${item.student_id}" id="${item.student_id}"></p>
							</div>
						</c:otherwise>
					</c:choose>	
					<div class="result_table_id">
						<p>${item.student_id}</p>
					</div>	
					<div class="result_table_name">
						<p>${item.name}</p>
					</div>	
				</div>
			</c:forEach>
		</div>
	</form>
</c:if>
<div class="common_back_button">
 <a href="../teacher_function/grade_registration_top.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<%@include file="../footer.jsp"%>