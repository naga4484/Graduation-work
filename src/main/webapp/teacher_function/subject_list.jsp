<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"
	href="../css/teacher_function.css">

<title>科目情報一覧画面</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1 class="page_title">科目情報一覧機能</h1>

<div class="subject_list_area">
	<!-- メッセージ表示 -->
	<c:if test="${delete_mes != null}">
		<p class="system_return_mes">${delete_mes}</p>
	</c:if>
	<c:if test="${change_mes != null}">
		<p class="system_return_mes">${change_mes}</p>
	</c:if>
	<c:if test="${none_mes != null}">
		<p class="system_return_mes">${none_mes}</p>
	</c:if>
	<!-- 新規登録リンク -->
	<p><a href="subject_registration.jsp">新規登録</a></p>
		<div class="subject_list_table_area">
			<!-- 科目情報編集フォーム -->
			<form action="Subjectchangedata.action">
				<div class="subject_list_table_head">
					<div class="subject_list_table_head_id">
						<p>科目ID</p>
					</div>
					<div class="subject_list_table_head_name">
						<p>科目名</p>
					</div>
					<div class="subject_list_table_head_unit">
						<p>総単位数</p>
					</div>
					<div class="subject_list_table_head_class">
						<p>クラスID</p>
					</div>
					<div class="subject_list_table_head_color">
						<p>科目色</p>
					</div>
					<div class="subject_list_table_head_delete">
						<p>DELETE</p>
					</div>
				</div>
				<c:forEach var="item" items="${class_subject}">
					<div class="subject_list_table_body">
						<div class="subject_list_table_id">
							<p id="subject_id">${item.subject_id}</p>
						</div>
						<div class="subject_list_table_name">
							<p><input type="text" maxlength="30"
							name="${item.subject_id}_subject_name"
							value="${item.subject_name}" class="subject_list_table_name_input"></p>
						</div>
						<div class="subject_list_table_unit">
							<p><input type="number" max="999" min="1"
								name="${item.subject_id}_total_unit" value="${item.total_unit}" class="subject_list_table_unit_input"></p>
						</div>
						<div class="subject_list_table_class">
							<p>${item.class_id}</p>
						</div>
						<div class="subject_list_table_color">
							<p><input type="color"
								name="${item.subject_id}_subject_color"
								value="${item.subject_color}" class="subject_list_table_color_input"></p>
						</div>
						<div class="subject_list_table_delete">
							<p><input type="button" id="button${item.subject_id}"
								name="${item.subject_id}" value="削除"></p>
						</div>
					</div>
				</c:forEach>
		</div>
		<input type="submit" value="変更" id="subject_change_button" class="subject_change_button_css">
	</form>
</div>

<!-- 削除確認ボックス -->
<div id="delete_box"></div>
<div id="delete_box_back"></div>

<script src="../js/subject.js"></script>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>



<%@ include file="../footer.jsp"%>
