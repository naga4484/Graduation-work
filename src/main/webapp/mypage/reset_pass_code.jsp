<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">

<head>
<meta charset="UTF-8">
<title>password変更-確認コード入力</title>
</head>
<body>
    <div class="mypage_main_content">
        <div class="pass_reset">
            <h1>パスワード変更</h1>
            <div class="send_messe">
            <p>${successMessage}</p>
            </div>
            <div class="error">
            <p>${errorMessage}</p>
            </div>
            
            <form action="PasswordCodeVerify.action" method="post">
                <div class="box4">
                <input type="hidden" name="kind" value="${account.account_kind }">
                <div class="input_messe">
                <p>確認コードを入力してください</p>
                </div>
                <input type="text" name="verification_code" required="required">
                <br><br>
                <div class="code_return">
                <a href="PasswordResend.action">確認コードを再送信</a><br>
                </div>
                </div>               
                
                <button type="submit">送信</button>
            </form>
            <br><br>
        </div>
           <div class="common_back_button">
           <a href="change_top.jsp"><img src="../images/戻るボタン1.png" class="support_back_icon"></a></a>
           </div>  
        </div>
</body>
<%@ include file="../footer.jsp"%>
