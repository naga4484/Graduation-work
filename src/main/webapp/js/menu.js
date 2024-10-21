let menu_box = document.querySelector("#menu_box");
let menu_var = document.getElementById("menu_var");
let menu_back = document.getElementById("menu_box_back");
let boxstyle = menu_box.style;
let menu_backstyle = menu_back.style;

menu_var.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
        menu_backstyle.display = "none";
    }else{
        boxstyle.display = "block";
        menu_backstyle.display = "block";
}}

menu_back.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
        menu_backstyle.display = "none";
    }else{
        boxstyle.display = "block";
        menu_backstyle.display = "block";
}}