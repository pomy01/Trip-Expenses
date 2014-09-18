/**
 * Javascript file for functions that handle Payments. 
 */

/**
 * Function to display the form for adding a new friend.
 */
function addPaymentForm() {
	var message = PaymentCreateFormService.generatePaymentCreateForm();
	if(message) {
		$('#paymentsWindow').prepend(message);
	}
	else {
		display.innerHTML = "<h1>The 'generateTripCreateForm' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * Function that handles the Payment addition.
 */
function addPayment() {
	var paymentD = $("#paymentD").val();
	var paymentS = $("#paymentS").val();
	//TODO: better handling of the input.
	//duplicate the input handling on the server side.
	
	//TODO: handle the NO_SUM case.
	if (paymentS) {
		var message = AddPaymentService.addNewPayment({
			tripCredential  : selectedTripCred,
			paymentDescription : paymentD,
			paymentSum : paymentS });
		if(message != "CREATION_FAILED") {
			$('#displayWindow').trigger("create");
		} else {
			alert ("creation failed");
		}
	}
	var message2 = DisplayTripService.displayTripDetails({
		tripCred : selectedTripCred
		});
	display = document.getElementById("displayWindow");
	if(message2) {
		display.innerHTML = message2;
	}
	else {
		display.innerHTML = "<h1>The 'generate trips' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * Function that cancels the payment addition form. 
 */
function cancelPaymentAddition() {
	var message2 = DisplayTripService.displayTripDetails({
		tripCred : selectedTripCred
		});
	display = document.getElementById("displayWindow");
	if(message2) {
		display.innerHTML = message2;
	}
	else {
		display.innerHTML = "<h1>The 'generate trips' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

