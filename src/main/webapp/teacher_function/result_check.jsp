<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">
<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>成績確認画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>
<h1>成績管理機能</h1>

<div class="left_form_content">
	
	
	<form action="Result_search.action">
		<h2>学生検索</h2>
		<c:if test="${none_error != null}">
			<p class="system_return_mes">${none_error}</p>
		</c:if>
		<label>クラス</label>
	    <select name="class_id">
		    <c:forEach var="class_num" items="${class_num}">
		    	<option value="${class_num.class_id}">${class_num.class_num}</option>
		    </c:forEach>
		</select>
		<label>成績種別</label>
	    <select name="result_kind" id="result_kind">
		    <option value="semester">学期</option>
		    <option value="subject">科目</option>
		    <option value="submissions">提出物</option>
		</select>
		<div class="result_kind_area" id="result_kind_area">
		</div>
		
		<input type="submit" value="検索">
	</form>
	
</div>
<div>
	<c:choose>
		<c:when test="${semester_result_list.size() > 0}">
			<div class="teacher_table">
				<div class="result_tables_head">
					<div class="result_tables_semester">
						<p>学期</p>
					</div>
					<div class="result_tables_name">
						<p>氏名</p>
					</div>
					<div class="result_tables_result">
						<p>点数</p>
					</div>
					<div class="result_tables_evaluation">
						<p>評価</p>
					</div>
					<div class="result_tables_comment">
						<p>コメント</p>
					</div>
				</div>
				<c:forEach var="result_list" items="${semester_result_list}">
					<div class="result_tables_body">
						<div class="result_tables_semester">
							<p>${result_list.semester}</p>
						</div>
						<div class="result_tables_name">
							<p>${result_list.name}</p>
						</div>
						<div class="result_tables_result">
							<p>${result_list.result}</p>
						</div>
						<div class="result_tables_evaluation">
							<p>${result_list.evaluation}</p>
						</div>
						<div class="result_tables_comment">
							<p class="txt-limits">${result_list.comment}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:when test="${subject_result_list.size() > 0}">
			<div class="teacher_table">
				<div class="result_tables_head">
					<div class="result_tables_subject">
						<p>科目ID</p>
					</div>
					<div class="result_tables_name">
						<p>氏名</p>
					</div>
					<div class="result_tables_result">
						<p>点数</p>
					</div>
					<div class="result_tables_evaluation">
						<p>評価</p>
					</div>
					<div class="result_tables_comment">
						<p>コメント</p>
					</div>
				</div>
				<c:forEach var="result_list" items="${subject_result_list}">
					<div class="result_tables_body">
						<div class="result_tables_subject">
							<p>${result_list.subject_id}</p>
						</div>
						<div class="result_tables_name">
							<p>${result_list.name}</p>
						</div>
						<div class="result_tables_result">
							<p>${result_list.result}</p>
						</div>
						<div class="result_tables_evaluation">
							<p>${result_list.evaluation}</p>
						</div>
						<div class="result_tables_comment">
							<p class=".txt-limits-min">${result_list.comment}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:when test="${submissions_result_list.size() > 0}">
			<div class="teacher_table">
				<div class="result_tables_head">
					<div class="result_tables_subject">
						<p>提出物ID</p>
					</div>
					<div class="result_tables_name">
						<p>氏名</p>
					</div>
					<div class="result_tables_result">
						<p>点数</p>
					</div>
					<div class="result_tables_evaluation">
						<p>評価</p>
					</div>
					<div class="result_tables_comment">
						<p>コメント</p>
					</div>
				</div>
				<c:forEach var="result_list" items="${submissions_result_list}">
					<div class="result_tables_body">
						<div class="result_tables_subject">
							<p>${result_list.submissions_id}</p>
						</div>
						<div class="result_tables_name">
							<p>${result_list.name}</p>
						</div>
						<div class="result_tables_result">
							<p>${result_list.result}</p>
						</div>
						<div class="result_tables_evaluation">
							<p>${result_list.evaluation}</p>
						</div>
						<div class="result_tables_comment">
							<p class=".txt-limits-min">${result_list.comment}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p class="result_none_mes">成績情報が登録されていません</p>
		</c:otherwise>
	</c:choose>
</div>
<script>
	let selectElement = document.getElementById("result_kind");
	let changeElement = document.getElementById("result_kind_area");
	
	selectElement.addEventListener("change", function(e) {
	  let selectedValue = e.target.value;
	  if(selectedValue == "semester"){
		  changeElement.innerHTML=``;
	  }
	  else if(selectedValue == "subject"){
		  changeElement.innerHTML=`
			  <select name="subject_id">
			 	 <c:forEach var="subject" items="${subject_list}">
			 	 	<option value="${subject.subject_id}">${subject.subject_name}</option>
			 	 </c:forEach>
			  </select>
		  `;
	  }
	  else if(selectedValue == "submissions"){
		  changeElement.innerHTML=`
			  <select name="submissions_id">
			 	 <c:forEach var="submissions" items="${submissions_list}">
			 	 	<option value="${submissions.submissions_id}">${submissions.name}</option>
			 	 </c:forEach>
			  </select>
		  `;
	  }
	});
</script>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/attendance.js"></script>
<%@include file="../footer.jsp"  %>