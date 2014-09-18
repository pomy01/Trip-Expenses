function verifyRegister() {
	var usern = $("#user_reg").val();
	var pass = $("#pass_reg").val();
	var email = $("#mail").val();

	if (usern && pass && mail) {
		 var message = RegisterService.register({
		 username : usern,
		 password : pass,
		 mail : email
		 });
	}
}