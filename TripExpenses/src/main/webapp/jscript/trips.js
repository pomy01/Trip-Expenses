/**
 * Gets the trips list from server in HTML format and 
 *
 * injects it in the display window.
 */
var selectedTripCred;
var tripCreateFormDisplayed = false;

/**
 * Displays user's trips.
 */
function displayTrips() {
	tripCreateFormDisplayed = false;
	var message = TripsService.generateTripsList();
	display = document.getElementById("displayWindow");
	if(message) {
		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generate trips' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * Handles the Closing of a trip.
 */
function closeTrip() {
	var message = CloseTripService.close({tripCredential  : selectedTripCred});
	if (message == "SUCCESS") {
		var message2 = DisplayTripService.displayTripDetails({
			tripCred : selectedTripCred
			});
		display = document.getElementById("displayWindow");
		if(message2) {
			display.innerHTML = message2;
		}
		else {
			display.innerHTML = "<h1>Trip Selection has failed !</h1>";
		}
		$('#displayWindow').trigger("create");
	}
}

/**
 * reopen a closed Trip
 */
function reopenTrip() {
	var message = ReopenTripService.reopen({tripCredential  : selectedTripCred});
	if (message == "SUCCESS") {
		var message2 = DisplayTripService.displayTripDetails({
			tripCred : selectedTripCred
			});
		display = document.getElementById("displayWindow");
		if(message2) {
			display.innerHTML = message2;
		}
		else {
			display.innerHTML = "<h1>Trip Selection has failed !</h1>";
		}
		$('#displayWindow').trigger("create");
	}
}

/**
 * Triggered when a trip is clicked in the listview.
 * will display the html content into the display window.
 */
function tripSelected(elem) {
	selectedTripCred = $(elem.parentNode).data('name');
	var message = DisplayTripService.displayTripDetails({
		tripCred : selectedTripCred
		});
	display = document.getElementById("displayWindow");
	if(message) {
		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generate trips' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * the function that triggers the appearance of the Trip creation form.
 * Also done from server side by un commenting the first row.
 */
function addTrip() {
	//TODO: determine which is the best way to do this.(ask Dan)
	//client side or serverside.....i guess client side to let the client customize its input method.
	if(tripCreateFormDisplayed == false) {
		var message = TripCreateFormService.generateTripCreateForm();
		display = document.getElementById("displayWindow");
		if(message) {
			$('#displayWindow').prepend(message);
		}
		else {
			display.innerHTML = "<h1>The 'generateTripCreateForm' message returned was NULL</h1>";
		}
		$('#displayWindow').trigger("create");
		tripCreateFormDisplayed = true;
	}	
}

/**
 * Handles the request to create a new trip.
 */
function createTrip () {
	var tripN = $("#tripN").val();
	var tripL = $("#tripL").val();
	var tripD = $("#tripD").val();
	tripCreateFormDisplayed = false;
	//TODO: better handling of the input.
	//duplicate the input handling on the server side.
	
	if (tripN) {
		var message = AddTripService.addNewTrip({
			tripName : tripN,
			tripLocation : tripL,
			tripDescription : tripD
			});
		if(message != "CREATION_FAILED") {
			displayTrips();
		} else {
			alert ("creation failed");
		}
	}
}

/**
 * Displays the form to add a new member to the trip.
 */
function addMemberForm() {
	message = "<div class=\"ui-field-contain\">\n" + 
    "             <input type=\"text\"\n" + 
    "                name=\"friendName\" id=\"memberN\" data-theme=\"a\" placeholder=\"Ask friend to join\"> " +
    "            <label> </label>\n" + 
    "            <label>  </label>\n"
	+ "	<div class=\"ui-grid-a\">"
	+ "		<div class=\"ui-block-a\">"
	+ "			<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
	+ "				data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
	+ "				data-theme=\"a\""
	+ "				class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-plus" 
	+ "ui-nodisc-icon\" onclick=\"addMemberToTrip()\">"
	+ "					<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
	+ "						class=\"ui-btn-text\"> Add Member</span> <span"
	+ "						class=\"ui-icon ui-icon-shadow\">&nbsp;</span></span>"
	+ "			</a>"
	+ "			</span>"
	+ "		</div>"
	+ "		<div class=\"ui-block-b\">"
	+ "			<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
	+ "				data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
	+ "				data-theme=\"a\""
	+ "				class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-delete"
	+ "				ui-nodisc-icon\"  onclick=\"cancelMemberAddition()\">"
	+ "					<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
	+ "						class=\"ui-btn-text\">Cancel </span> <span"
	+ "						class=\"ui-icon ui-icon-delete ui-icon-shadow\">&nbsp;</span></span>"
	+ "			</a>"
	+ "			</span>"
	+ "		</div>"
	+ "	</div>"
    + "</div>";
	
	$('#membersWindow').prepend(message);
	$('#displayWindow').trigger("create");
}

/**
 * Invite a new member to join your trip.
 */
function addMemberToTrip() { 
	var newMember = $('#memberN').val();
	var message  = AddTripMemberService.addMember({
		tripCredential : selectedTripCred,
		newMemberName : newMember
		});
	//TODO: handle the response of the add member service.
	message = DisplayTripService.displayTripDetails({
		tripCred : selectedTripCred
		});
	display = document.getElementById("displayWindow");
	if(message) {
		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generate trips' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * Cancels the display of the addMember form.
 */
function cancelMemberAddition () {
	var message = DisplayTripService.displayTripDetails({
		tripCred : selectedTripCred
		});
	display = document.getElementById("displayWindow");
	if(message) {
		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generate trips' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}