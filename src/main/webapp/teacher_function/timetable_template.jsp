<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>一括設定送信</title>
<div>
  <!-- 左側の要素 -->
  <div class="item" onclick="showSettings(1)">未設定 1</div>
  <div class="item" onclick="showSettings(2)">未設定 2</div>
  <div class="item" onclick="showSettings(3)">未設定 3</div>
  <div class="item" onclick="showSettings(4)">未設定 4</div>
</div>

<!-- 詳細設定エリア -->
<div id="settingsArea" class="detail-settings">
  <h3>詳細設定</h3>
  <!-- form要素で設定を一括送信 -->
  <form id="settingsForm" method="POST" action="/your-java-servlet-url">
    <!-- 各アイテムの設定を保持する隠しフィールド -->
    <input type="hidden" name="itemSettings[1][color]" id="item1Color">
    <input type="hidden" name="itemSettings[1][name]" id="item1Name">
    <input type="hidden" name="itemSettings[1][template]" id="item1Template">
    
    <input type="hidden" name="itemSettings[2][color]" id="item2Color">
    <input type="hidden" name="itemSettings[2][name]" id="item2Name">
    <input type="hidden" name="itemSettings[2][template]" id="item2Template">

    <input type="hidden" name="itemSettings[3][color]" id="item3Color">
    <input type="hidden" name="itemSettings[3][name]" id="item3Name">
    <input type="hidden" name="itemSettings[3][template]" id="item3Template">
    
    <input type="hidden" name="itemSettings[4][color]" id="item4Color">
    <input type="hidden" name="itemSettings[4][name]" id="item4Name">
    <input type="hidden" name="itemSettings[4][template]" id="item4Template">

    <label for="subjectColor">科目の色</label>
    <select id="subjectColor">
      <option value="red">赤</option>
      <option value="blue">青</option>
      <option value="green">緑</option>
      <option value="yellow">黄</option>
    </select>

    <label for="subjectName">項目名</label>
    <input type="text" id="subjectName" placeholder="項目名を入力">

    <label for="templateName">テンプレート名</label>
    <input type="text" id="templateName" placeholder="テンプレート名を入力">
    
    <button type="button" onclick="saveSettings()">設定を保存</button>
    <button type="submit">すべて送信</button>
  </form>
</div>

<script>
  let currentItem = null;
  const itemSettings = {};

  // 各アイテムの詳細設定を表示する関数
  function showSettings(itemNumber) {
    currentItem = itemNumber;
    document.getElementById('settingsArea').style.display = 'block';

    // フィールドに現在の設定を表示（初回は空）
    document.getElementById('subjectColor').value = itemSettings[itemNumber]?.color || '';
    document.getElementById('subjectName').value = itemSettings[itemNumber]?.name || '';
    document.getElementById('templateName').value = itemSettings[itemNumber]?.template || '';

    console.log(`Item ${itemNumber} の設定を開きました`);
  }

  // 各アイテムの設定を保存する関数
  function saveSettings() {
    const color = document.getElementById('subjectColor').value;
    const subjectName = document.getElementById('subjectName').value;
    const templateName = document.getElementById('templateName').value;

    // 選択中のアイテムの設定を保存
    itemSettings[currentItem] = {
      color,
      name: subjectName,
      template: templateName
    };

    // 隠しフィールドに設定を反映
    document.getElementById(`item${currentItem}Color`).value = color;
    document.getElementById(`item${currentItem}Name`).value = subjectName;
    document.getElementById(`item${currentItem}Template`).value = templateName;

    console.log(`Item ${currentItem} の設定を保存しました`);
    document.getElementById('settingsArea').style.display = 'none';
  }
</script>

<a href="../common/top.jsp">TOP</a>
<%@include file="../footer.jsp"  %>