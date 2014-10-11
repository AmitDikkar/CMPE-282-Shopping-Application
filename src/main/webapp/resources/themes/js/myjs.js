/**
 * 
 */

function getCookie(cname) {
		    var name = cname + "=";
		    var ca = document.cookie.split(';');
		    for(var i=0; i < ca.length; i++) {
		        var c = ca[i];
		        while (c.charAt(0)==' ') c = c.substring(1);
		        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
		    }
		    return "";
		}

function checkCookie(cname) {
    var username=getCookie(cname);
    if (username!="") {
        return true;
    }else{
        return false;
    }
}

function eraseCookie(cname) {
	document.cookie = cname + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

/**
 * Initialize document with cookie values and do other stuff.
 */
function initializeDocument(){
	if(checkCookie("userName")){
		var userName = getCookie("userName");
		$("#headerUserName").text(userName);
		$("#btnLoginLogout").val("logout");
		$("#btnLoginLogout").find("#spanLoginLogout").text("Logout");
	}
}

$(document).ready(function(){
	
	//This is onclick event for login/logout button.
	$("#btnLoginLogout").click(function(event){
		event.preventDefault();
		var logoutValue = "logout";
		var buttonValue = $("#btnLoginLogout").val();
		if(buttonValue === logoutValue){
			eraseCookie("userId");
			eraseCookie("userName");
		}
		window.location = "login";
	});	
});
