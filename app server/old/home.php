
<?php
session_start();

	if(!isset($_SESSION['session']))
		{	
		header("location:index.html");
		}
?>


<html>
 <head>
   <title>
    CSI Admin Page home
   </title>
    <link rel='stylesheet' type='text/css' href='stylesheet.css'>
  </head>
  <body>
  
   <center>
   <h1>Home</h1>
	<div>
    <br>
	<table cellpadding="0" cellspacing="0" width="50%">
	<tr>
		<th align="left"><button id="newUser" class='button'>New User</button></th>
		<th align="left"><button id="newEvent" class='button'>New Event</button></th>
	</tr>
	<br>
	<br>
	<tr>
		<th align="left"><button id="displayUser" class='button'>Display User Details</button></th>
		<th align="left"><button id="displayEvent" class='button'>Display Event Details</button></th>
	</tr>
	<tr>
		<th align="left"><button id="updateUser" class='button'>Update User Details</button></th>
		<th align="left"><button id="updateEvent" class='button'>Update Event Details</button></th>
	</tr>
	<tr>
		<th align="left"><button id="deleteUser" class='button'>Delete User</button></th>
		<th align="left"><button id="deleteEvent" class='button'>Delete Event</button></th>
	</tr>
	
	<tr>
		<th align="left"><button id="event" class='button'>Registration Details</button></th>
		<th align="left"><button id="feedback" class='button'>Feedback</button></th>
	</tr>
	</table>
	  <br><br>
	  <button id="logout"class='button'>Log Out</button>
   </div>
   <center>
	
	<script type="text/javascript">
    document.getElementById("newUser").onclick = function () {
        location.href = "new_user.php";	};

		document.getElementById("displayUser").onclick = function () {
        location.href = "displayUser.php";}
		
		document.getElementById("newEvent").onclick = function () {
        location.href = "newEvent.php";}
		
		document.getElementById("displayEvent").onclick = function () {
        location.href = "displayEvent.php";}

		document.getElementById("updateUser").onclick = function () {
        location.href = "findUser.php";}

		document.getElementById("updateEvent").onclick = function () {
        location.href = "findEvent.php";}

		document.getElementById("event").onclick = function () {
        location.href = "Events.php";}

        document.getElementById("feedback").onclick = function () {
        location.href = "feedbackdetails.php";}

        document.getElementById("deleteUser").onclick = function () {
        location.href = "findUser_delete.php";}
		
		document.getElementById("deleteEvent").onclick = function () {
        location.href = "findEvent_delete.php";}
	
		document.getElementById("logout").onclick = function () {
        location.href = "logout.php";}
	
	</script>
  
  </body>
</html>
