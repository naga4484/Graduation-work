let up_not_my_submissions = document.getElementById("my_submissions_box");
let my_submissions_box_back = document.getElementById("my_submissions_box_back");
let boxstyle = up_not_my_submissions.style;
let my_submissions_backstyle = my_submissions_box_back.style;


up_not_my_submissions.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
        my_submissions_backstyle.display = "none";
    }else{
        boxstyle.display = "block";
        my_submissions_backstyle.display = "block";
}}

my_submissions_backstyle.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
        my_submissions_backstyle.display = "none";
    }else{
        boxstyle.display = "block";
        my_submissions_backstyle.display = "block";
}}