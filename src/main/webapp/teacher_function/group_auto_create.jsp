<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/attendance.css">

<title>グループ自動作成画面</title>

<h1>グループ自動作成機能</h1>


<form action="Group_auto_student.action">
	<h2>学生検索</h2>
	<label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select>
	
	<input type="submit" value="検索">
</form>
<c:if test="${st_list.size() > 0}">
	<form action="Group_auto_create.action">
		<div>
			<label>1グループの人数</label>
			<select name="group_member_num" id="group_member_num">
				<c:forEach var="i" begin="1" end="${st_list.size() / 2 + 1}" step="1">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<label for="yes_flag">リーダーを設定する</label>
			<input type="radio" name="reader_flag" id="yes_flag" value="on">
			<label for="no_flag">リーダーを設定しない</label>
			<input type="radio" name="reader_flag" id="no_flag" value="off" checked>
			<div id="reader_content">
			</div>
			<input type="submit" value="生成">
		</div>
		<div id="group_member_table">
			<table>
				<tr>
					<th>学生ID</th>
					<th>氏名</th>
				</tr>
				<c:forEach var="item" items="${st_list}">
					<tr>
						<td>${item.student_id}</td>
						<td>${item.name}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</c:if>

<a href="../common/top.jsp">TOP</a>

<!-- sessionの情報をつかう為にここにjsを記入 -->
<script>
	let group = document.getElementById('group_member_table');
	let reader_radio_on = document.getElementById('yes_flag');
	let reader_radio_off = document.getElementById('no_flag');
	let reader_content = document.getElementById('reader_content');
	let reader_content_style = reader_content.style;
	reader_content_style.display = "none";
	let group_style = group.style;
	let select_reader = document.getElementById('group_member_num');
	let select_reader_value=select_reader.value
	select_reader.addEventListener('change', function() {
		select_reader_value = select_reader.value
		console.log("selectからの変更：" + select_reader_value)
	});

	reader_radio_on.onchange = function(e){
		reader_content_style.display = "block";
		select_reader_value = select_reader.value
		console.log("radioからの変更：" + select_reader_value)
		reader_content.innerHTML = `	
			<p>リーダーの人数：` + select_reader_value + `</p>
		`
		group.innerHTML = `
			<table>
			<tr>
				<th></th>
				<th>学生ID</th>
				<th>氏名</th>
			</tr>
			<c:forEach var="item" items="${st_list}"  varStatus="count">
				<tr>
					<td><input type="checkbox" name="reader_${count.index}" value="リーダー"></td>
					<td>${item.student_id}</td>
					<td>${item.name}</td>
				</tr>
			</c:forEach>
			</table>
		`
	};
	reader_radio_off.onchange = function(e){
		reader_content_style.display = "none";
		reader_content.innerHTML = ``
		group.innerHTML = `
			<table>
			<tr>
				<th>学生ID</th>
				<th>氏名</th>
			</tr>
			<c:forEach var="item" items="${st_list}">
				<tr>
					<td>${item.student_id}</td>
					<td>${item.name}</td>
				</tr>
			</c:forEach>
			</table>
		`
	};
</script>
<%@include file="../footer.jsp"  %>