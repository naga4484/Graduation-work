<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">

<head>
<title>ニックネーム変更</title>
</head>
<body>
	<div class="mypage_main_content">
		<div class="update_nickname">
			<h1>ニックネーム変更</h1>

			<form action="StudentNicknameUpdate.action" method="post">
			    <div class="box2">
			    <label>新しいニックネームを入力</label><br><br>
				<input type="hidden" name="student_id" id="student_id"value="${account.student_id }"> 
			    <input type="text"name="new_nickname" required="required">
			    <div class="directions">
				<p>※この情報は、「ちりつもサービス」内で公開されます。</p>
				<p>メールアドレス、電話番号などの個人情報や</p>
				<p>個人の特定につながる情報は入力しないでください。</p>
				</div>
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