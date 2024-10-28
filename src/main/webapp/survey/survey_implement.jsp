<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>
<%@ page import="java.util.ArrayList" %>
<head>
<title>アンケート実施</title>
    <link rel="stylesheet" type="text/css" href="../css/inquiry.css">
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <script type="text/javascript">
	let inputCount = 2; // 外部にカウンタを定義し、一意の名前を維持

	function addInputField() {
		let container = document.getElementById("input-container");

		// 新しい入力フィールドを作成
		let newInput = document.createElement("input");//新しい入力フォームの作成
		newInput.type = "text";//テキストボックスの設置
		newInput.name = "input" + inputCount;//name属性の数字の変更
		newInput.placeholder = "回答フォーム " + inputCount;//入力フォームの中の文字の変更

		// 削除ボタンを作成
		let removeButton = document.createElement("button");
		removeButton.type = "button";//ボタンを設置
		removeButton.innerText = "削除";//ボタンに削除と命名
		removeButton.onclick = function() {//クリックするとイベントが発生するようにする
			container.removeChild(newInput);//入力フォームの削除
			container.removeChild(removeButton);//ボタンの削除
			container.removeChild(br); // 改行も削除
		};

		// 改行を作成
		let br = document.createElement("br");

		container.appendChild(newInput);
		container.appendChild(removeButton);
		container.appendChild(br); // 各新しい入力フィールドの後に改行を追加

		inputCount++; // 新しい入力フィールドを追加した後でカウンタを増加
	}
</script>
</head>
<body>
	<div class="main_content">
		<div class="survey_implement">
			<h1>アンケート実施</h1>
			<form action="SubmitForm.action" method="post">
				<h2>アンケート内容</h2>
				<input type="text" id="survey_name">
				<h3>回答</h3>
				<div id="input-container">
					<input type="text" name="input1" placeholder="回答フォーム 1"> <br>
				</div>
				<button type="button" onclick="addInputField()">フィールドを追加</button>
				<button type="submit">送信</button>
			</form>
			<a href="survey_list.jsp">戻る</a>
		</div>
	</div>
</body>
<%@ include file="../footer.jsp"%>
