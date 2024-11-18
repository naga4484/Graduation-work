<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 確認コードのチェックを終えた後のパスワード変更画面 -->
<!-- また、既存のパスワードがあっている際のパスワード入力画面 -->
<head>
    <title>パスワード変更</title>
</head>
<body>
    <div class="main_content">
        <div class="update_pass">
            <h1>パスワード変更</h1>

            <!-- パスワード更新フォーム -->
            <form action="StudentPasswordUpdate.action" method="post">
                <input type="hidden" name="student_id" value="${account.student_id}">
                <input type="hidden" name="password" value="${account.password}">
                
                <p>${errorMessage}</p>
                <!-- 古いパスワードを保持 -->
                <input type="hidden" name="old_password" value="${account.password }">
                
                <br><br>
                
                <label>新しいパスワード</label>
                <input type="password" name="new_password" required="required">
                
                <br><br>
                
                <label>新しいパスワードを確認</label>
                <input type="password" name="confirm_password" required="required">
                
                <br><br>
                
                <button type="submit">変更</button>
            </form>

        </div>
        
        <br>
        <a href="change_top.jsp">戻る</a>
    </div>
</body>
<%@ include file="../footer.jsp"%>