<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" type="text/css" href="../css/calendar.css">

<title>スケジュール管理画面</title>

<div class="weather_content">
	<p class="weather_content_title"><span>${temperature_name}</span>の一週間の天気</p>
	<c:forEach var="i" begin="0" end="7" varStatus="status">
		<div class="weather_lists">
			<p class="weather_lists_day">${weeks_days[status.index].substring(5,10)}</p>
			<hr class="weather_lists_hr">
			<img src="../images/${weeks_weather[status.index][0]}.png" class="weather_png" alt="${weeks_weather[status.index][0]}">
			<c:forEach var="i" begin="1" end="3">
				<p class="weather_lists_contents">${weeks_weather[status.index][i]}</p>
			</c:forEach>
		</div>
	</c:forEach>
</div>


<div class="common_back_button">
  <a href="../schedule/calendar_display.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>