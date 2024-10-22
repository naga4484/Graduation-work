let menu_box = document.querySelector("#menu_box");
let menu_var = document.getElementById("menu_var");
let menu_back = document.getElementById("menu_box_back");
let menu_boxstyle = menu_box.style;
let menu_backstyle = menu_back.style;

menu_var.onclick = function() {
	if(menu_boxstyle.display === "block"){ 
        menu_boxstyle.display = "none";
        menu_backstyle.display = "none";
    }else{
        menu_boxstyle.display = "block";
        menu_backstyle.display = "block";
}}

menu_back.onclick = function() {
	if(menu_boxstyle.display === "block"){ 
        menu_boxstyle.display = "none";
        menu_backstyle.display = "none";
    }else{
        menu_boxstyle.display = "block";
        menu_backstyle.display = "block";
}}