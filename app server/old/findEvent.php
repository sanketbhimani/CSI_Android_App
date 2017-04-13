<?php
echo($_SESSION['session']);
	if($_isset[$_SESSION['session']])
		{	
		header("location:index.html");
		}
?>

<html>
 <head>
   <title>
    Find User
   </title>
    <link rel='stylesheet' type='text/css' href='stylesheet.css'>
   </head>
  <body>
   <center><br><br>
   <h1>Find User</h1>
	<br>
    <form action="fetch_event.php" method="post">
	<table>
	<tr>
      <th><label for="eventid">Event Id</label></th>
      <th>:<input type="text" name="eventid"></th>
	</tr>
	</table>
		  <th>	<input type="submit" id="submit" class='button'></input> 	</th>
	</form>
	<br><br>
	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>
   

  </body>
</html>
