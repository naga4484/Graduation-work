<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            background-color: #4169e1; /* 背景色をロイヤルブルーに設定 */
            color: white; /* テキストを白に設定 */
        }
        a {
            display: inline-block; /* transform が適用されるために必要 */
            text-decoration: none; /* リンクの下線を削除 */
            color: red; /* リンクの色を白に設定 */
        }
        
        h1 {
            font-size: 80px; /* メイン見出しのフォントサイズ */
        }
        
        h1:hover,
        p:hover,
        a:hover,
        h2:hover {
            animation: anime-h2 0.3s linear; /* ホバー時のアニメーション */
        }
        
        @keyframes anime-h2 {
            20% {
                transform: translate(-2px, 2px);
            }
            40% {
                transform: translate(-2px, -2px);
            }
            60% {
                transform: translate(2px, 2px);
            }
            80% {
                transform: translate(2px, -2px);
            }
        }
    </style>
</head>
<body>
    <h1>:(</h1>
    <h2>Error</h2>
    <p>${errorMessage}</p>
    <p>Please log out... just do it!</p>
    <p>0% tukurukinaiyo</p>
    <a href="mypage_top.jsp">back page</a>
</body>
</html>
