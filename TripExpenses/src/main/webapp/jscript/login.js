$(document)
		.ready(
				function() {
					// facebook login
					function loginWithFacebook() {
						var ceva = LoginFb.getInfo();
						alert(ceva);
						alert("login with facebook");
						// var message = LoginFb.verify();
						// window.location.href =
						// 'http://localhost:8080/TripExpenses/rest/LoginFb/verify';
					};
				});

function login() {
	document.getElementById("error").style.visibility = 'hidden';
	var usern = $("#user").val();
	var pass = $("#pass").val();
	if (usern && pass) {
		var message = LoginService.login({
			username : usern,
			password : pass
		});
		if (message == "WRONG_PASSWORD") {
			document.getElementById("error").style.visibility = 'visible';
			document.getElementById("error").innerHTML = '<font color="red"><b>Wrong password</b>';
		} else if (message == "NOT_REGISTERED") {
			document.getElementById("error").style.visibility = 'visible';
			var content = '<font color="red"><b>The user : </font><font color="blue">'
				+ usern
				+ '</font><font color="red"> does not exist !</b>'
				+ '<a href="register.html">Click to register</a>';
			document.getElementById("error").innerHTML = content;
		} else if (message == "USER_REGISTERED") {
			 window.location="HomePage.html";
		}
	} else {
		document.getElementById("error").style.visibility = 'visible';
		document.getElementById("error").innerHTML = '<font color="red"><b>Both fields are mandatory</b>';
	}
};