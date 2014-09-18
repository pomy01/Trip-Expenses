$(document).bind("pageinit", function() {
//     displayHome(); 
	document.getElementById("trips").click()
});


function logout() {
	var message = LogoutService.logoutUser();
	window.location="index.html";
}

/**
 * Display the Home Page.
 */

function displayHome() {
	var message = HomeServices.generateHome();
	display = document.getElementById("displayWindow");
	if(message) {
		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generate home' message returned was NULL</h1>";
	}

	$('#displayWindow').trigger("create");
}