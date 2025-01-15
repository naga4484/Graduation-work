<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">

<title>時間割管理画面</title>


<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<h1>時間割機能</h1>

<form action="Timetable_class.action">
	<label>クラス</label>
    <select name="class_id">
	    <c:forEach var="class_num" items="${class_num}">
	    	<option value="${class_num.class_id}">${class_num.class_num}</option>
	    </c:forEach>
	</select>
	
	<input type="submit" value="検索">
</form>
<c:if test="${timetable_select.size() > 0}">
	<div id="timetable_select_date">
		<label>日付</label>
		<select name="timetable_date">
			<c:forEach var="item" items="${timetable_select}">
				<c:choose>
					<c:when test="${item.equals(data_num) == true}">
						<option value="${item}" selected>${item}</option>
					</c:when>
					<c:otherwise>
						<option value="${item}">${item}</option>
					</c:otherwise>
				</c:choose>	
		    </c:forEach>
		</select>
	</div>
</c:if>
<c:if test="${success_mes != null}">
	<p class="system_return_mes">登録が完了いたしました</p>
</c:if>
<c:if test="${timetable_change_List.size() > 0}">
	<form action="Timetable_update.action">
	    <c:forEach begin="1" end="${Month_of_days}" step="1" var="days">
		    	<div class="timetable_box">
			    	<c:if test="${days < 10}">
			    		<c:forEach var="item_timetable" items="${timetable_change_List}">
					    	<c:if test="${item_timetable.data.substring(8,11).equals('0'.concat(Integer.toString(days)).concat('日')) == true}">
					    		<c:if test="${item_timetable.timetable_hour.equals('1') == true}">
					    			<h1 id="contents_data_${days}">${item_timetable.data}</h1>
					    		</c:if>
					    		<p>${item_timetable.timetable_hour}コマ目：
						    		<select name="${item_timetable.data}${item_timetable.timetable_hour}">
						    			<option value="Notset">-------</option>
						    			<c:forEach var="item_subject" items="${class_subject}">
						    				<c:choose>
												<c:when test="${item_subject.subject_id.equals(item_timetable.subject_id) == true}">
						    						<option value="${item_subject.subject_id}" selected>${item_subject.subject_name}</option>
						    					</c:when>
						    					<c:otherwise>
													<option value="${item_subject.subject_id}">${item_subject.subject_name}</option>
												</c:otherwise>
											</c:choose>		
						    			</c:forEach>
						    		</select>
					    		</p>
					    	</c:if>
				   		</c:forEach>
			    	</c:if>
			    	<c:if test="${days >= 10}">
			    		<c:forEach var="item_timetable" items="${timetable_change_List}">
					    	<c:if test="${item_timetable.data.substring(8,11).equals(Integer.toString(days).concat('日')) == true}">
					    		<c:if test="${item_timetable.timetable_hour.equals('1') == true}">
					    			<h1 id="contents_data_${days}">${item_timetable.data}</h1>
					    		</c:if>
					    		<p>${item_timetable.timetable_hour}コマ目：
						    		<select name="${item_timetable.data}${item_timetable.timetable_hour}">
						    			<option value="Notset">-------</option>
						    			<c:forEach var="item_subject" items="${class_subject}">
						    				<c:choose>
												<c:when test="${item_subject.subject_id.equals(item_timetable.subject_id) == true}">
						    						<option value="${item_subject.subject_id}" selected>${item_subject.subject_name}</option>
						    					</c:when>
						    					<c:otherwise>
													<option value="${item_subject.subject_id}">${item_subject.subject_name}</option>
												</c:otherwise>
											</c:choose>		
						    			</c:forEach>
						    		</select>
					    		</p>
					    	</c:if>
				   		</c:forEach>
			    	</c:if>
			    	<select name="timetable_template" id="select_template${days}">
			    		<c:set var="num">nonejfannfenfjdsanjk</c:set>
			    		<c:forEach var="item_template" items="${timetable_template_teacher}">
			    			<c:choose>
								<c:when test="${item_template.template_name.equals(num) == true}">
		    					</c:when>
		    					<c:otherwise>
		    						<c:set var="num">${item_template.template_name}</c:set>
									<option value="${item_template.template_name}">${item_template.template_name}</option>
								</c:otherwise>
							</c:choose>
			    		</c:forEach>
			    	</select>
			    	<c:set var="num">nonemakmnfajemskkd</c:set>
		    		<c:forEach var="item_template" items="${timetable_template_teacher}">
		    			<c:choose>
							<c:when test="${item_template.template_name.equals(num) == true}">
								<input type="hidden" id="${item_template.template_name}_${item_template.timetable_hour}" value="${item_template.subject_id}">
	    					</c:when>
	    					<c:otherwise>
	    						<c:set var="num">${item_template.template_name}</c:set>
								<input type="hidden" id="${item_template.template_name}_${item_template.timetable_hour}" value="${item_template.subject_id}">
							</c:otherwise>
						</c:choose>
		    		</c:forEach>
			    	<input type="button" id="template_button${days}" value="適用">
		    	</div>
		</c:forEach>
		<input type="hidden" value="${timetable_template_teacher}" id="template_list">
		<div class="timetable_button">
		<input type="submit" value="登録">
		</div>
	</form>
       <div class="pagination">
         <button onclick="changePage(-1)">＜</button>
         <button onclick="changePage(1)">＞</button>
       </div>

	
</c:if>
<div class="common_back_button">
 <a href="../teacher_function/teacher_function.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>


<script src="../js/timetable.js"></script>
<script>
	//テンプレート適応用(Javaから渡したデータを使う場合、jsファイルだと上手くいかないので、こっちで作成)
	let template_list = document.getElementById('template_list');
	for(let i = 1;i <= "${Month_of_days}";i++){
		let template_button = document.getElementById('template_button' + i);
		template_button.addEventListener('click',function(e){
			let select_template = document.querySelector("#select_template" + i);
			let template_name = select_template.value;
			let data_num = document.getElementById('contents_data_' + i).innerText;
			let template_data_1 = document.getElementById(template_name + '_1');
			let template_data_2 = document.getElementById(template_name + '_2');
			let template_data_3 = document.getElementById(template_name + '_3');
			let template_data_4 = document.getElementById(template_name + '_4');
			let select_1 = document.querySelector('[name="' + data_num + '1"]');
			let select_2 = document.querySelector('[name="' + data_num + '2"]');
			let select_3 = document.querySelector('[name="' + data_num + '3"]');
			let select_4 = document.querySelector('[name="' + data_num + '4"]');
			select_1.value = template_data_1.value || "Notset";
			select_2.value = template_data_2.value || "Notset";
			select_3.value = template_data_3.value || "Notset";
			select_4.value = template_data_4.value || "Notset";
			console.log(template_data_1);
			console.log(template_data_2);
			console.log(template_data_3);
			console.log(template_data_4);
			
		});
	}
</script>
<%@include file="../footer.jsp"  %>