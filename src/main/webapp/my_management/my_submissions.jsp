<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/my_submissions.css">

<title>提出物管理画面</title>

<h1 class="page_title">提出物管理機能</h1>
<c:if test="${my_submissions != null}">
	<div id="my_submissions_box">
		<h1>提出物提出</h1>
		<a href="Close_my_submissions_send.action"><button class="my_submissions_box_close_button">閉じる</button></a>
		<div>
			<form action="My_submissions_send.action" enctype="multipart/form-data" method="POST">
		        <label for="file">提出ファイル</label>
		        <input type="file" name="file" class="my_submissions_box_file_select"><br>
		        <input type="submit" value="送信" class="my_submissions_box_file_send_button">
		    </form>
	    </div>
	</div>
	<div id="my_submissions_box_back">
	</div>
</c:if>
<c:if test="${my_send_submissions != null}">
	<div id="my_submissions_box">
		<h1>提出状況の確認</h1>
		<a href="Close_my_submissions_send_update.action"><button class="my_submissions_box_close_button">閉じる</button></a>
		<div>
			<form action="My_submissions_send_update.action" enctype="multipart/form-data" method="POST">
		        <label for="file">提出ファイル</label>
		        <input type="file" name="file" class="my_submissions_box_file_select"><br>
		        <input type="submit" value="送信" class="my_submissions_box_file_send_button">
		    </form>
	    </div>
	    <a href="My_submissions_management_update_download.action" class="my_submissions_box_file_download">提出済みのファイルの確認</a>
	</div>
	<div id="my_submissions_box_back">
	</div>
</c:if>
<div class="my_submissions_main_area">
	<c:if test="${my_submissions_list != null}">
		<div class="my_submissions_main_table_area_top">
			<div class="my_submissions_main_table_area_name">
				<p>提出物名</p>
			</div>
			<div class="my_submissions_main_table_area_date">
				<p>提出期限</p>
			</div>
			<div class="my_submissions_main_table_area_flag">
				<p>提出状況</p>
			</div>
			<div class="my_submissions_main_table_area_send">
				<p>リンク</p>
			</div>
		</div>
		<c:forEach var="item" items="${my_submissions_list}">
			<div class="my_submissions_main_table_area">
				<div class="my_submissions_main_table_area_name">
					<p>${item.name}</p>
				</div>
				<div class="my_submissions_main_table_area_date">
					<p>${item.create_date}</p>
				</div>
				<c:choose>
					<c:when test="${item.submissions_flag == true}">
						<div class="my_submissions_main_table_area_flag">
							<p>提出済み</p>
						</div>
						<div class="my_submissions_main_table_area_send">
							<p><a href="My_submissions_management_update.action?submissions_id=${item.submissions_id}">提出状況変更へ</a></p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="my_submissions_main_table_area_flag">
							<p>未提出</p>
						</div>
						<div class="my_submissions_main_table_area_send">
							<p><a href="My_submissions_management.action?submissions_id=${item.submissions_id}">提出物詳細へ</a></p>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</c:if>
</div>
<div class="common_back_button">
  <a href="../my_management/my_management.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<script src="../js/my_submissions.js"></script>
<%@include file="../footer.jsp"  %>