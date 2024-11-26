<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">

<head>
<meta charset="UTF-8">
<title>email変更-確認コード入力</title>
</head>
<body>
    <div class="mypage_main_content">
        <div class="add_reset">
            <h1>メールアドレス変更</h1>
            <div class="code_return"
            <p>${successMessage}</p>
            </div>
            <div class="error">
            <p>${errorMessage}</p>
            </div>
            
            <form action="AddressCodeVerify.action" method="post">
                <input type="hidden" name="kind" value="${account.account_kind }">
                <p>確認コード</p>
                <input type="text" name="verification_code" required="required">
                <br>
                <div class="return_link">
                <a href="AddressResend.action">確認コードを再送信</a><br>
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
