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

<div class="teacher_center">
	<h1>科目情報一覧機能</h1>

	<!-- メッセージ表示 -->
	<c:if test="${delete_mes != null}">
		<p>${delete_mes}</p>
	</c:if>
	<c:if test="${change_mes != null}">
		<p>${change_mes}</p>
	</c:if>
	<c:if test="${none_mes != null}">
		<p>${none_mes}</p>
	</c:if>

	<!-- 新規登録リンク -->
	<a href="subject_registration.jsp">新規登録</a>

	<div class="subject_table">
		<!-- 科目情報編集フォーム -->
		<form action="Subjectchangedata.action">
			<table>
				<thead>
					<tr>
						<th class="headword">科目ID</th>
						<th class="headword">科目名</th>
						<th class="headword">総単位数</th>
						<th class="headword">クラスID</th>
						<th class="headword">科目色</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${class_subject}">
						<tr>
							<td id="subject_id">${item.subject_id}</td>
							<td><input type="text" maxlength="30"
								name="${item.subject_id}_subject_name"
								value="${item.subject_name}"></td>
							<td><input type="number" max="999" min="1"
								name="${item.subject_id}_total_unit" value="${item.total_unit}">
							</td>
							<td>${item.class_id}</td>
							<td><input type="color"
								name="${item.subject_id}_subject_color"
								value="${item.subject_color}"></td>
							<td><input type="button" id="button${item.subject_id}"
								name="${item.subject_id}" value="削除"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<input type="submit" value="変更" id="subject_change_button">
	</form>
</div>

<!-- 削除確認ボックス -->
<div id="delete_box"></div>

<script src="../js/subject.js"></script>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>



<%@ include file="../footer.jsp"%>
