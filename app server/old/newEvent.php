
<html>
 <head>
   <title>
    New Event
   </title>
   <link rel='stylesheet' type='text/css' href='stylesheet.css'>
  </head>
  <body>
   <center>
   <h1>New Event</h1>
   <div>
	<br>
    <form action="check_event.php" method="post">
	<table>
	<tr>
		<th align="left"><label for="name">Name</label></th>
		<th align="left">:<input type="text" name="name"></th>
	</tr>
	<tr>
		<th align="left"><label for="image">Image</label></th>
		<th align="left">:<input type="file" name="image"></th>
	</tr>
	<tr>
		<th align="left"><label for="text">Description</label>
		<th align="left">:<textarea  name="text" rows="4" cols="32"></textarea>
	</tr>
	<tr>
		<th align="left"><label for="date">Date</label>
		<th align="left">:<input type="text" name="date">
	 </tr>
	<tr>
		<th align="left"><label for="time">Time</label>
		<th align="left">:<input type="text" name="time" >
	</tr>
	<tr>
		<th align="left"><label for="venue">Venue</label>
		<th align="left">:<input type="text" name="venue" >
	</tr>
	<tr>
		<th align="left"><label for="contact">Contact</label>
		<th align="left">:<textarea name="contact" rows="4" cols="32"></textarea>
	</tr>
	<tr>
		<th align="left"><label for="fee">Registrastion fee</label>
		<th align="left">:<input type="text" name="fee">
	</tr>
	</table>
	   	<button id="submit" class='button'>Submit</button>
	</form><br><br>
   </div>
 	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>  

   </center>
  </body>
</html>
