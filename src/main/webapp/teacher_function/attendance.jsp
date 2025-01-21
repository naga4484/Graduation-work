<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">
<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>出欠管理画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>
<h1>出欠管理機能</h1>

<div class="left_form_content">
	<c:if test="${not_attendancestudent != null}">
		<button id="up_not_attendance">欠席学生の情報を登録する</button>
		<div id="attendance_box">
			<h1 class="not_attendance_stu_tags">欠席生徒の種別登録</h1>
			<form  action="AttendanceNotregistration.action">
				<div class="not_attendance_stu_head">
					<div class="not_attendance_stu_radio">
						<p>病欠</p>
					</div>
					<div class="not_attendance_stu_radio">
						<p>公欠</p>
					</div>
					<div class="not_attendance_stu_radio">
						<p>遅刻</p>
					</div>
					<div class="not_attendance_stu_radio">
						<p>その他</p>
					</div>
					<div class="not_attendance_stu_id">
						<p>学生ID</p>
					</div>
					<div class="not_attendance_stu_name">
						<p>学生氏名</p>
					</div>
				</div>
				<c:forEach var="item" items="${not_attendancestudent}">
					<div class="not_attendance_stu_body">
						<div class="not_attendance_stu_radio">
							<p><input type="radio" name="${item.student_id}" value="病欠" checked></p>
						</div>
						<div class="not_attendance_stu_radio">
							<p><input type="radio" name="${item.student_id}" value="公欠"></p>
						</div>
						<div class="not_attendance_stu_radio">
							<p><input type="radio" name="${item.student_id}" value="遅刻"></p>
						</div>
						<div class="not_attendance_stu_radio">
							<p><input type="radio" name="${item.student_id}" value="その他"></p>
						</div>
						<div class="not_attendance_stu_id">
							<p>${item.student_id}</p>
						</div>
						<div class="not_attendance_stu_name">
							<p>${item.name}</p>
						</div>
					</div>
				</c:forEach>
				<input type="submit" value="登録">
			</form>
		</div>
		<div id="attendance_box_back">
		</div>
	</c:if>
	<c:if test="${attendance_finish != null}">
		<p class="system_return_mes">${attendance_finish}</p>
	</c:if>
	
	<form action="Attendancesearch.action">
		<h2>学生検索</h2>
		<c:if test="${none_error != null}">
		<p class="system_return_mes">${none_error}</p>
		</c:if>
		<label>学生</label>
		<input type="text" name="student_name" placeholder="名前を入力してください">
		
		<label>クラス</label>
	    <select name="class_id">
		    <c:forEach var="class_num" items="${class_num}">
		    	<option value="${class_num.class_id}">${class_num.class_num}</option>
		    </c:forEach>
		</select>
		
		<input type="submit" value="検索">
	</form>
	<c:if test="${attendancsstudentlist.size() > 0}">
		<form  action="Attendanceregistration.action">
		<div class="teacher_table">
			<h2>学生出欠登録</h2>
			<div class="submissions_reg_head">
	          	<div class="submission_reg_flag">
	          		<p>出欠可否</p>
	          	</div>
	          	<div class="submission_reg_id">
	          		<p>学生ID</p>
	          	</div>
	          	<div class="submission_reg_name">
	          		<p>学生氏名</p>
	          	</div>
	        </div>
	        <c:forEach var="item" items="${attendancsstudentlist}">
	       		<div class="submissions_reg_body">
		           	<div class="submission_reg_flag">
		           		<p><input type="checkbox" name="${item.student_id}" value="出席" checked></p>
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
		
			<input type="submit" value="登録">
		</form>
		<c:if test="${samplelist.size() > 0}">
			<h3 class="attendance_list_iframe_text">登録済み情報</h3>
			<iframe src="attendance_list.jsp" class="attendance_list_iframe"></iframe>
		</c:if>
	</c:if>
</div>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/attendance.js"></script>
<%@include file="../footer.jsp"  %>