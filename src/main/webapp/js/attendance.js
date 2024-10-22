let attendance_box = document.querySelector("#attendance_box");
let up_not_attendance = document.getElementById("up_not_attendance");
let attendance_box_back = document.getElementById("attendance_box_back");
let boxstyle = attendance_box.style;
let attendance_backstyle = attendance_box_back.style;

up_not_attendance.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
        attendance_backstyle.display = "none";
    }else{
        boxstyle.display = "block";
        attendance_backstyle.display = "block";
}}

attendance_box_back.onclick = function() {
	if(boxstyle.display === "block"){ 
        boxstyle.display = "none";
        attendance_backstyle.display = "none";
    }else{
        boxstyle.display = "block";
        attendance_backstyle.display = "block";
}}