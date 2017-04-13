<?php

	if(!empty($_POST["csiid"]))
	{
		$csiid=$_POST["csiid"];
		
		include("connect.php");
		
	$q="SELECT * FROM user where csiid='".$csiid."'" ;
	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());
	$row=mysql_fetch_array($result);
	
	echo "
	<html>
	<head>
		<title>
		Update User
		</title>
    <link rel='stylesheet' type='text/css' href='stylesheet.css'>
   </head>
   	<center>
  <body>
			<h1>Change User</h1>
	<form action='update_User_DB.php' method='post'>
	<table>
	<tr>
      <th><label for='name'>First name</label></th>
      <th>:<input type='text' name='name' value =".$row['name']."></th>
	</tr>
	<tr>
      <th><label for='lastname'>Last name</label></th>
      <th>:<input type='text' name='lastname' value =".$row['lastname']." ></th>
	</tr>
	<tr>
      <th><label for='bday'>Birthday</label></th>
      <th>:<input type='text' name='bday' value =".$row['bday']."></th>
      <th>(in dd-MM-yyyy format)</th>
	</tr>
	<tr>
      <th><label for='csiid'>CSI Id</label></th>
      <th align='left'>:".$row['csiid']."</th>
	  <th><input type='hidden' name='csiid' value =".$row['csiid']." ></th>
	</tr>
	<tr>
      <th><label for='email'>Email</label></th>
      <th>:<input type='text' name='email' value =".$row['email']."></th>
	</tr>
	<tr>
      <th><label for='mobileno'>Contact Number</label></th>
      <th>:<input type='number' name='mobileno' value =".$row['mobileno']."></th>
	</tr>
	</table>
	<input type='submit' id='submit' class='button'></input>
	</form>
	
	<button id='home' class='button'><a href='home.php'>Home</a></button>
	<button id='logout' class='button'><a href='logout.php'>Logout</a></button>
	</body>
	</center>
	</html>";
	}
	else
	{
		echo " please, Enter CSI ID ";
		include("updateUser.php");
	}

?>
