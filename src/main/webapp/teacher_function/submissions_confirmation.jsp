<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="../css/teacher_function.css">

<title>提出物確認画面</title>

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
        <p>${none_error}</p>
    </c:if>
    <c:if test="${change_mes != null}">
        <p>${change_mes}</p>
    </c:if>
    <c:if test="${delete_mes != null}">
        <p>${delete_mes}</p>
    </c:if>
</div>

<c:if test="${distinctsubmissions_class.size() > 0}">
	<div class="teacher_table">
    <!-- 提出物リストの表示 -->
        <table>
            <thead>
                <tr>
                    <th class="headword">No</th>
                    <th class="headword">提出物ID</th>
                    <th class="headword">提出物名</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${distinctsubmissions_class}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>
                            <a href="Submissions_detail.action?submissions_id=${item.submissions_id}">
                                ${item.submissions_id}
                            </a>
                        </td>
                        <td>
                            <a href="Submissions_detail.action?submissions_id=${item.submissions_id}">
                                ${item.name}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
	</div>
</c:if>

<a href="../common/top.jsp">TOP</a>
<script src="../js/year.js"></script>
<%@ include file="../footer.jsp" %>
