let box = document.querySelector("#delete_box");
let subject_id = document.querySelectorAll("#subject_id");

let boxstyle = box.style;

for(let i = 0;i < subject_id.length;i++){
	console.log("押した？");
	let btnname = subject_id[i].innerText;
	let btn = document.getElementById("button"+ btnname);
	btn.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
    }else{
        boxstyle.display = "block";
        box.innerHTML = `
        <p class="subject_delete_mes">科目コード「`+ btnname +`」削除しますか？？？</p>
	    <a href="Subjectdelete.action?subject_id=` + btnname +`"><button id="subject_next_button">はい</button></a>
	    <input type="button" value="いいえ" id="subject_close_button">`;
	    let subject_close = document.getElementById("subject_close_button");
	    subject_close.addEventListener('click',function(){
		if(boxstyle.display === "block"){ 
	        boxstyle.display = "none";
	    	}
		});
    }
}
}


	
