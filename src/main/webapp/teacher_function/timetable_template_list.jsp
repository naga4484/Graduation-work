<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>時間割テンプレート画面</title>


<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>時間割テンプレート機能</h1>

<a href="../teacher_function/timetable_template.jsp" class="timetable_tmp_reg_link">テンプレート新規作成</a>
<c:if test="${timetablelist.size() > 0}">
	<a href="../teacher_function/timetable_management.jsp">時間割管理画面へ</a>
</c:if>

<div class="left_form_content">
	<c:if test="${delete_mes != null}">
		<p class="system_return_mes">${delete_mes}</p>
	</c:if>
	<c:choose>
		<c:when test="${timetablelist.size() > 0}">
			<div class="timetable_tmp_list_table">
				<div class="timetable_tmp_list_head">
					<div class="timetable_tmp_list_name">
						<p>テンプレート名</p>
					</div>
					<div class="timetable_tmp_list_edit">
						<p>編集</p>
					</div>
					<div class="timetable_tmp_list_delete">
						<p>削除</p>
					</div>
				</div>
				<c:forEach var="item_timetable" items="${timetablelist}">
					<div class="timetable_tmp_list_body">
						<div class="timetable_tmp_list_name">
							<p>${item_timetable.template_name}</p>
						</div>
						<div class="timetable_tmp_list_edit">
							<p><a href="Timetable_template_update.action?template_name=${item_timetable.template_name}">編集</a></p>
						</div>
						<div class="timetable_tmp_list_delete">
							<p><a href="Timetable_template_delete.action?template_name=${item_timetable.template_name}">削除</a></p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p>テンプレートは作成されていません</p>
		</c:otherwise>
	</c:choose>
</div>

<div class="common_back_button">
  <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>