<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="../css/teacher_function.css">

<title>提出物確認画面</title>


<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>提出物確認機能</h1>
<div class="class_submission_search">
    <!-- クラス検索フォーム -->
    <form action="Submissions_class.action">
        <h2>クラス検索</h2>
        <label for="class_id">クラス</label>
        <select id="class_id" name="class_id">
            <c:forEach var="class_num" items="${class_num}">
                <option value="${class_num.class_id}">${class_num.class_num}</option>
            </c:forEach>
        </select>
        <input type="submit" value="検索">
    </form>

    <!-- エラーメッセージや成功メッセージ -->
    <c:if test="${none_error != null}">
        <p class="system_return_mes">${none_error}</p>
    </c:if>
    <c:if test="${change_mes != null}">
        <p class="system_return_mes">${change_mes}</p>
    </c:if>
    <c:if test="${delete_mes != null}">
        <p class="system_return_mes">${delete_mes}</p>
    </c:if>
</div>

<c:if test="${distinctsubmissions_class.size() > 0}">
	<div class="teacher_table">
    <!-- 提出物リストの表示 -->
        <div class="submissions_confomation_head">
        	<div class="submissions_confomatio_no">
				<p>No.</p>
			</div>
			<div class="submissions_confomation_id">
				<p>提出物ID</p>
			</div>
			<div class="submissions_confomation_name">
				<p>提出物名</p>
			</div>
		</div>
		<c:forEach var="item" items="${distinctsubmissions_class}" varStatus="status">
        	<div class="submissions_confomation_body">
	        	<div class="submissions_confomatio_no">
					<p>${status.index + 1}</p>
				</div>
				<div class="submissions_confomation_id">
					<p><a href="Submissions_detail.action?submissions_id=${item.submissions_id}">
                         ${item.submissions_id}
                     </a></p>
				</div>
				<div class="submissions_confomation_name">
					<p><a href="Submissions_detail.action?submissions_id=${item.submissions_id}">
                         ${item.name}
                     </a></p>
				</div>
        	</div>
        </c:forEach>
	</div>
</c:if>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/year.js"></script>
<%@ include file="../footer.jsp" %>
