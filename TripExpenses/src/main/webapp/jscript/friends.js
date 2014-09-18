/**
 * Javascript file that handles the user's friends.
 */

/**
 * Display the Friend List.
 */
function displayFriends() {
	var message = FriendsService.generateFriendsList();
	display = document.getElementById("displayWindow");
	if(message) {
		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generateFriends' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * Triggered when a friend is clicked in the list view.
 * @param elem a reference to the element that was clicked.
 */
function friendSelected(elem) {
	alert(elem.innerHTML);
//	alert(elem.getAttribute("data-name"))
	
}

/**
 * Function to display the form for adding a new friend.
 */
function addFriendForm() {
	var message = AddFriendFormService.addFriendFormService();
	display = document.getElementById("displayWindow");
	if(message) {
		$('#displayWindow').prepend(message);
//		display.innerHTML = message;
	}
	else {
		display.innerHTML = "<h1>The 'generateTripCreateForm' message returned was NULL</h1>";
	}
	$('#displayWindow').trigger("create");
}

/**
 * The function to add a new friend to the user.
 */
function addFriend() {
	var friendN = $("#friendN").val();
	if (friendN) {
		var message = AddFriendService.addNewFriend({
			friendName : friendN});
		if(message != "ADDITION_FAILED") {
			displayFriends();
		} else {
			alert ("creation failed");
		}
	}
	$('#displayWindow').trigger("create");
}
