<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインユーザー選択画面</title>
<!-- CSSに関しては、機能ファルダごとにCSSを分けている -->
<link rel="stylesheet" type="text/css" href="../css/top.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/teacher.css">
<link rel="stylesheet" type="text/css" href="../css/account.css">
<title>エラー画面</title>
</head>
<body>
	<div class="main_content">
		<p>${error_mes}</p>
		<a href="../account/Logout.action">ログアウト</a>
	</div>
</body>
</html>

<%@include file="../footer.jsp"%>
