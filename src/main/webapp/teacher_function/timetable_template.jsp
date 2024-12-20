<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css"href="../css/teacher_function.css">
<title>一括設定送信</title>

<!-- ログイン状態のみで使用できるようにするモノ -->
<c:if test="${account == null}">
	<c:redirect url="../account/Error_common.action" />
</c:if>

<div class="items">
<!-- 左側の要素 -->
  <div class="item" id="itemLabel1" onclick="showSettings(1)">未設定 1</div>
  <div class="item" id="itemLabel2" onclick="showSettings(2)">未設定 2</div>
  <div class="item" id="itemLabel3" onclick="showSettings(3)">未設定 3</div>
  <div class="item" id="itemLabel4" onclick="showSettings(4)">未設定 4</div>
</div>


<!-- 詳細設定エリア -->
<div id="settingsArea" class="detail-settings">
  <h3>詳細設定</h3>
  <p id="koma_num"></p>
  <!-- form要素で設定を一括送信 -->
  <form action="Timetable_template.action">
    <!-- 各アイテムの設定を保持する隠しフィールド -->
    <input type="hidden" name="itemSettings[1][name]" id="item1Name">
    <input type="hidden" name="itemSettings[2][name]" id="item2Name">
    <input type="hidden" name="itemSettings[3][name]" id="item3Name">
    <input type="hidden" name="itemSettings[4][name]" id="item4Name">
    
    <!-- テンプレート名（全要素共通） -->
    <label for="templateName">テンプレート名</label>
    <input type="text" id="templateName" name="templateName" placeholder="テンプレート名を入力" required>

    <label for="subjectName">科目名</label>
    <select name="subjectName" id="subjectName">
      <c:forEach var="item" items="${class_subject}">
        <option value="${item.subject_id}">${item.subject_name}</option>
      </c:forEach>
    </select>
    
    <button type="button" onclick="saveSettings()">設定を保存</button>
    <button type="submit">すべて送信</button>
  </form>
</div>

<script>
//現在選択されているアイテムの番号を保持する変数
let currentItem = null;
const itemSettings = {};

// 各アイテムの詳細設定を表示する関数
function showSettings(itemNumber) {
  currentItem = itemNumber;
  document.getElementById('settingsArea').style.display = 'block';
  document.getElementById('koma_num').innerText = currentItem + 'コマ目';
  

  // フィールドに現在の設定を表示（初回は空）
  document.querySelector('#subjectName').value = itemSettings[itemNumber]?.name || '';
}

// テンプレート名を共通で保持し、各アイテムに反映する
document.getElementById('templateName').addEventListener('input', function () {
  const templateName = this.value;
  for (let i = 1; i <= 4; i++) {
    itemSettings[i] = itemSettings[i] || {};
    itemSettings[i].template = templateName;

    // 各アイテムの隠しフィールドにも反映
    const itemTemplateField = document.getElementById(`item` + i + `Template`);
    if (itemTemplateField) {
      itemTemplateField.value = templateName;
    }
  }
});

// 各アイテムの設定を保存する関数
function saveSettings() {
  const subjectName = document.querySelector('#subjectName').value;

  // 選択中のアイテムの設定を保存
  itemSettings[currentItem] = {
    name: subjectName,
    template: document.getElementById('templateName').value
  };

  // 各アイテムの隠しフィールドに設定を反映
  document.getElementById(`item` + currentItem + `Name`).value = subjectName;

//アイテムラベルを更新
  const itemLabel = document.getElementById(`itemLabel` + currentItem);
  if (itemLabel) {
    itemLabel.textContent = subjectName || `未設定 ${currentItem}`;
  }
}
</script>

<div class="common_back_button">
  <a href="../teacher_function/timetable_template_list.jsp"><img src="../images/戻るボタン1.png" class="teacher_back_icon"></a>
</div>
<%@include file="../footer.jsp"  %>
