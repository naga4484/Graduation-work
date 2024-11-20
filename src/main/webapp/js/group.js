
let reader_radio_on = document.getElementById('yes_flag');
let reader_radio_off = document.getElementById('no_flag');
let reader_content = document.getElementById('reader_content');
let reader_content_style = reader_content.style;

reader_radio_on.onchange = function(e){
	reader_content.innerHTML = `
		<label>リーダー人数</label>
		<input type="number" name="reader_num" required>
	`
};
reader_radio_off.onchange = function(e){
	reader_content.innerHTML = ``
};