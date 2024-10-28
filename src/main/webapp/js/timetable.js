let select_data = document.querySelector('[name="timetable_date"]');
let select_class = document.querySelector('[name="class_id"]');


select_data.addEventListener('change',function(e){
	let data_num = select_data.value;
	let class_num = select_class.value;
	
	window.location.href = 'Timetables_data_search.action?data_num=' + data_num +'&class_id='+ class_num;
});