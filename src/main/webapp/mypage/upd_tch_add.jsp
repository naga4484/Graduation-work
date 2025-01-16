<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">

<head>

<title>メールアドレス変更</title>
</head>
<body>
	<div class="mypage_main_content">
		<div class="update_add">
			<h1>メールアドレス変更</h1>
				<form action="TeacherAddressUpdate.action"method="post">
				<input type="hidden"name="teacher_id"id="teacher_id"value="${account.teacher_id }">
				
				<div class="error">
				<p>${errorMessage }</p>
				</div>
				<div class="box1">
					<label>新しいメールアドレスを入力</label><br><br>
					<input type="email" name="new_address"required="required">
					<br><br>
					<label>新しいメールアドレスを確認</label><br><br>
					<input type="email" name="confirm_address"required="required">
					
				</div>
					
					<button type="submit">変更</button>
				</form>
		</div>
           <div class="common_back_button">
           <a href="change_top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div> 
	</div>

</body>
<%@ include file="../footer.jsp"%>