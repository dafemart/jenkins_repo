"use strict"
var EntityType = "null";

function isChecked(type){
   EntityType = type;
}

window.onload = function(){
    document.getElementById("employee").addEventListener("click", function(){isChecked("employee");}, false);
    document.getElementById("benco").addEventListener("click",function(){isChecked("benco");}, false);
    document.getElementById("DirectManager").addEventListener("click", function(){isChecked("Direct Manager");}, false);
    document.getElementById("DirectSupervisor").addEventListener("click", function(){isChecked("Direct Supervisor");}, false);
    document.getElementById("DepartmentHead").addEventListener("click", function(){isChecked("Department Head");}, false);
};