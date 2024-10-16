let box = document.querySelector("#delete_box");
let subject_id = document.querySelectorAll("#subject_id");

let boxstyle = box.style;

for(let i = 0;i < subject_id.length;i++){
	let btnname = subject_id[i].innerText;
	let btn = document.getElementById("button"+ btnname);
	btn.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
    }else{
        boxstyle.display = "block";
        box.innerHTML = `
        <p>科目コード「`+ btnname +`」削除しますか？？？</p>
	    <button><a href="Subjectdelete.action?subject_id=` + btnname +`">はい</a></button>
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


	
