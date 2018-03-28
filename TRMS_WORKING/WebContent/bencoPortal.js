var currentRow = 1;
var currentRequestToIssueID = "0";
var btn;
function MapEventTypeNumber(num){
	var type = "Unknown Event";
	if(num == 1)
		type = "University Courses";
	else if(num == 2)
		type = "Seminars";
	else if(num == 3)
		type = "Cert Prep";
	else if(num == 4)
		type = "Certification";
    else if(num == 5)
    	type = "Technical Training";
    else if(num == 6)
    	type = "Other";
    return type;
};

function  MapStatusNumber(num){
	if(num == 0)
		return "ONHOLD";
	else if(num == 1)
		return "APPROVED";
	else return "DENIED";
}


function select(event){
	var target = event.target;
	if ( target.nodeName != 'TD' )
        return;
	 var columns = target.parentNode.getElementsByTagName( 'td' );
	 btn = target;
	 currentRequestToIssueID = columns[0].innerHTML;
}

function approveRequest(){
	if(currentRequestToIssueID == "0"){
		alert("Please select a request to remove");
		return;
	}
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			var row = btn.parentNode;
			  row.parentNode.removeChild(row);
			  currentRequestToRemoveID = "0";
		}
	};
	xhr.open("POST", "ApproversServlet", true);
	xhr.send("decision=deny&requestid=" + currentRequestToIssueID);
}

function denyRequest(){
	
	if(currentRequestToIssueID == "0"){
		alert("Please select a request to remove");
		return;
	}
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			var row = btn.parentNode;
			  row.parentNode.removeChild(row);
			  currentRequestToRemoveID = "0";
		}
	};
	xhr.open("POST", "ApproversServlet", true);
	xhr.send("decision=approve&requestid=" + currentRequestToIssueID);  
} 


function logout(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			location.href = "http://localhost:8095/TRMS_WORKING/";
		}
	};
	xhr.open("GET", "LogoutServlet", true);
	xhr.send();
}

function DisplayUserInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			var userInfo = JSON.parse(xhr.responseText);
			document.getElementById("username").innerHTML = "Welcome " + userInfo.employee.employeeName;
			var PoolRequests = userInfo.poolRequests;
			var requestTable = document.getElementById("RequestTable");
			for(var i = 0; i < PoolRequests.length; i++){
				var newRequest = requestTable.insertRow(1);
				var requestid = newRequest.insertCell(0);
				var eventid = newRequest.insertCell(1);
				var courseLocation = newRequest.insertCell(2);
				var startingdate = newRequest.insertCell(3);
				var justification = newRequest.insertCell(4);
				var coursecost = newRequest.insertCell(5);
				var status = newRequest.insertCell(6);
				
				requestid.innerHTML = "" + PoolRequests[i].requestID;
			    eventid.innerHTML = "" + MapEventTypeNumber(PoolRequests[i].eventID);
				courseLocation.innerHTML = "" + PoolRequests[i].courseLocation;
				startingdate.innerHTML = "" + PoolRequests[i].startingDate;
			    justification.innerHTML = "" + PoolRequests[i].justification;
				coursecost.innerHTML = "" + PoolRequests[i].courseCost;
			    status.innerHTML = "" + MapStatusNumber(PoolRequests[i].status);
			}
		}
	};
	
	xhr.open("GET", "ApproversServlet", true);
	xhr.send();
}

window.onload = function() {
	document.getElementById("approve").addEventListener("click", approveRequest, false);
	document.getElementById("deny").addEventListener("click", denyRequest, false);
	document.getElementById("logout").addEventListener("click", logout, false);
	DisplayUserInfo();
};