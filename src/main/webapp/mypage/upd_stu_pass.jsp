<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">

<head>
    <title>パスワード変更</title>
</head>
<body>
    <div class="mypage_main_content">
        <div class="update_pass">
            <h1>パスワード変更</h1>

            <form action="StudentPasswordUpdate.action" method="post">
                <input type="hidden" name="password" value="${account.password}">
                <input type="hidden" name="student_id" value="${account.student_id}">

                <div class="error">
                <p>${errorMessage}</p>
                </div>
                <div class="box3">
                
                <label>パスワードを入力</label><br>
                <input type="password" name="old_password" required>
                <br>

                <label>新しいパスワード</label><br>
                <input type="password" name="new_password" required>
                <br>

                <label>新しいパスワードを確認</label><br>
                <input type="password" name="confirm_password" required>
                <br>
                
                </div>
                
                <div class="passup_buttun">
                <button type="submit">変更</button>
                </div>
            </form>

            <!-- パスワードリセット用フォーム -->
             <div class="pass_form">
             <form action="PasswordReset.action" method="post">
             <input type="hidden" name="email" value="${account.address}">
             <a href="PasswordReset.action?email=${account.address}">パスワードを忘れた方はこちら</a>
             </form>
             </div>


        </div>
        <br>
           <div class="common_back_button">
           <a href="change_top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div> 
        </div>
</body>
<%@ include file="../footer.jsp" %>
