<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<title>自己管理画面</title>

<h1>自己管理機能</h1>

<div>
	<h3>スケジュール系データ</h3>
	<c:choose>
		<c:when test="${my_management_date_list.size() > 0}">
			<c:forEach var="item_date" items="${my_management_date_list}">
				<div>
					<h2>${item_date.calender_date}</h2>
					<c:forEach var="item_content" items="${my_management_content_list}">
						<c:if test="${item_date.calender_date == item_content.calender_date}">
							<p>${item_content.setting_date}</p>
							<p>${item_content.schedule_content}</p>
						</c:if>
					</c:forEach>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>
				<p>予定はありません</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<div>
	<h3>出欠系データ</h3>
	<c:choose>
		<c:when test="${attendance_management_list_size > 0}">
			<p>直近${attendance_management_list_size}日間での出席日数</p>
			<c:if test="${attendance_management_date >= 0.5}">
				<p>${attendance_count}日</p>
			</c:if>
			<c:if test="${attendance_management_date < 0.5}">
				<p  style="color: red;">${attendance_count}日</p>
			</c:if>
		</c:when>
		<c:otherwise>
			<div>
				<p>出欠データがありません</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<div>
	<h3>提出物系データ</h3>
	<c:choose>
		<c:when test="${my_submissions_list.size() > 0}">
			<table>
				<tr>
					<th>提出物名</th>
					<th>期限</th>
					<th>提出済</th>
					<th></th>
				</tr>
				<c:forEach var="item" items="${my_submissions_list}">
					<tr>
						
						<td>${item.name}</td>
						<td>${item.create_date}</td>
						<c:choose>
							<c:when test="${item.submissions_flag == true}">
								<td>○</td>
							</c:when>
							<c:otherwise>
								<td>✕</td>
							</c:otherwise>
						</c:choose>
						<td><a href="My_submissions_management.action?submissions_id=${item.submissions_id}">提出物詳細へ</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="my_submissions.jsp">提出物一覧画面へ</a>
		</c:when>
		<c:otherwise>
			<div>
				<p>提出物はありません</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>


<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>