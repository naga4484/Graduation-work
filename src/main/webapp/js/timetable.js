let select_data = document.querySelector('[name="timetable_date"]');
let select_class = document.querySelector('[name="class_id"]');


select_data.addEventListener('change',function(e){
	let data_num = select_data.value;
	let class_num = select_class.value;
	
	window.location.href = 'Timetables_data_search.action?data_num=' + data_num +'&class_id='+ class_num;
});


const items = document.querySelectorAll('.timetable_box');
  const itemsPerPage = 3;  // 1ページに表示するアイテム数
  let currentPage = 1;

  // ページを変更する関数
  function changePage(direction) {
    currentPage += direction;
    const totalPages = Math.ceil(items.length / itemsPerPage);

    // ページ範囲を制限
    if (currentPage < 1) currentPage = 1;
    if (currentPage > totalPages) currentPage = totalPages;

    // 表示するアイテムの範囲を計算
    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    // 各アイテムの表示を制御
    items.forEach((item, index) => {
      item.style.display = (index >= start && index < end) ? 'inline-block' : 'none';
    });
  }

  // 初期表示を設定
  changePage(0);
  

