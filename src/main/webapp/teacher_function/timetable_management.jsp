<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<title>時間割管理画面</title>

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
	<p>登録が完了いたしました</p>
</c:if>
<c:if test="${timetable_change_List.size() > 0}">
	<form action="Timetable_update.action">
	    <c:forEach begin="1" end="${Month_of_days}" step="1" var="days">
		    	<div class="timetable_box">
			    	<c:if test="${days < 10}">
			    		<c:forEach var="item_timetable" items="${timetable_change_List}">
					    	<c:if test="${item_timetable.data.substring(8,11).equals('0'.concat(Integer.toString(days)).concat('日')) == true}">
					    		<c:if test="${item_timetable.timetable_hour.equals('1') == true}">
					    			<h1>${item_timetable.data}</h1>
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
					    			<h1>${item_timetable.data}</h1>
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
		    	</div>
		</c:forEach>
		<input type="submit" value="登録">
	</form>
	<div class="pagination">
	  <button onclick="changePage(-1)">前へ</button>
	  <button onclick="changePage(1)">次へ</button>
	</div>
	
</c:if>
<a href="../common/top.jsp">TOP</a>
<script src="../js/timetable.js"></script>
<%@include file="../footer.jsp"  %>