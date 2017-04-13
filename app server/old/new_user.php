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
    New User
   </title>
    <link rel='stylesheet' type='text/css' href='stylesheet.css'>
   </head>
  <body>
   <center><br><br>
   <h1>New User</h1>
	<br>
    <form action="check_user.php" method="post">
	<table>
	<tr>
      <th><label for="name">First name</label></th>
      <th>:<input type="text" name="name"></th>
	</tr>
	<tr>
      <th><label for="lastname">Last name</label></th>
      <th>:<input type="text" name="lastname"></th>
	</tr>
	<tr>
      <th><label for="bday">Birthday</label></th>
      <th>:<input type="text" name="bday"></th>
      <th>(in dd-MM-yyyy format)</th>
	</tr>
	<tr>
      <th><label for="csiid">CSI Id</label></th>
      <th>:<input type="text" name="csiid"></th>
	</tr>
	<tr>
      <th><label for="email">Email</label></th>
      <th>:<input type="text" name="email"></th>
	</tr>
	<tr>
      <th><label for="mobileno">Contact Number</label></th>
      <th>:<input type="number" name="mobileno"></th>
	</tr>
	</table>
	<input type="submit" id="submit" class='button'></input>
  
	</form>
	<br><br>
	
	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>
   

  </body>
</html>
