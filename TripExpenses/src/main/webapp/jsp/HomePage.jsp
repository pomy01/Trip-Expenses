<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="ro.sync.tripexpenses.tables.UserRegistered"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to TripExpenses</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript" src="jscript/FacebookLogin.js"></script>

<script lang="javascript" src="jscript/friends.js"></script>
<script lang="javascript" src="jscript/HomePage.js"></script>
</head>
<body>
	<div data-role="page" id="pageone">
		<div data-role="header" data-theme="b" data-position="fixed">
			<!-- 
			<div data-mini="true">
			<p style='text-align: center; color: #00ff00;'>
				Trip Expenses</p>
			</div>
			 -->
			<div data-role="navbar" data-iconpos="top" data-mini="true">
				<ul >
					<li><a href="#" class="ui-btn ui-mini" data-theme="b"
						data-iconpos="notext" data-icon="user" id="friends" onclick="displayFriends()" >Friends</a></li>
					<li><a href="#" class="ui-btn ui-mini" data-theme="b"
						data-iconpos="notext" data-role="button" data-icon="home"
						id="home" onclick="displayHome()">Home</a></li>
					<li><a href="#" class="ui-btn ui-mini" data-theme="b"
						data-iconpos="notext" data-role="button" data-icon="bullets"
						id="trips" onclick="displayTrips()">Trips</a></li>
					<li><a href="#" class="ui-btn ui-mini" data-theme="b"
						data-iconpos="notext" data-role="button" data-icon="power"
						id="logout" onclick="logout()">Logout</a></li>
				</ul>
			</div>
		</div>
		<div data-role="main" class="ui-content">
			<!-- The window to display selections -->
			<div id="displayWindow">
				<h1>main window</h1>
				<h2> The content of the main window is not yet determined</h2>
				<h2>the home page might be the default page </h2>
			</div>

			<!-- Friends Window -->
			<div id="friendWindow" hidden>
				<jsp:include page="Friends.jsp" ></jsp:include>
			</div>

			<!-- Trip Window -->
			<div id="tripWindow" hidden>
				<jsp:include page="Trips.jsp"></jsp:include>
			</div>

			<!-- Trip Window -->
			<div id="homeWindow" hidden>
			     <h1> Home page</h1>
				<jsp:include page="Home.jsp"></jsp:include>
			</div>
		</div>

		<div data-role="footer" data-theme="b" data-position="fixed">
			<p style='text-align: center; color: #00ff00;'>Trip Expenses</p>
		</div>
	</div>

</body>
</html>