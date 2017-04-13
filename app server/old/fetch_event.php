<?php

	if(!empty($_POST["eventid"]))
	{
		$eventid=$_POST["eventid"];
		
		include("connect.php");
		
	$q="SELECT * FROM event where eventid=$eventid" ;
	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());
	$row=mysql_fetch_array($result);
	
	echo "
	<html>
	<head>
		<title>
		Update Event
		</title>
    <link rel='stylesheet' type='text/css' href='stylesheet.css'>
   </head>
   	<center>
  <body>
			<h1>Change Event Details</h1>
	<form action='update_Event_DB.php' method='post' enctype='multipart/form-data'>
	<table>
	<tr>
      <th align='left'><label for='eventid'>Event Id</label></th>
      <th align='left'>:".$row['eventid']."</th>
	  <th><input type='hidden' name='eventid' value =".$row['eventid']." ></th>
	</tr>
	<tr>
		<th align='left'><label for='name'>Name</label></th>
		<th align='left'>:<input type='text' name='name' value =".$row['name']."></th>
	</tr>
	<tr>
		<th align='left'><label for='image'>Image</label></th>
		<th align='left'>:<input type='file' name='myimage' id='myimage'></th>
	</tr>
	<tr>
		<th align='left'><label for='text'>Description</label>
		<th align='left'>:<textarea  name='text' rows='4' cols='32' >{$row['text']}</textarea></th>
	</tr>
	<tr>
		<th align='left'><label for='date'>Date</label>
		<th align='left'>:<input type='text' name='date' value =".$row['date']."></th>
	 </tr>
	<tr>
		<th align='left'><label for='time'>Time</label>
		<th align='left'>:<input type='text' name='time' value =".$row['time']."></th>
	</tr>
	<tr>
		<th align='left'><label for='venue'>Venue</label>
		<th align='left'>:<input type='text' name='venue' value =".$row['venue']."></th>
	</tr>
	<tr>
		<th align='left'><label for='contact'>Contact</label>
		<th align='left'>:<textarea type='text' name='contact' rows='4' cols='32'>{$row['contact']}</textarea></th>
	</tr>
	<tr>
		<th align='left'><label for='fee'>Registrastion fee</label>
		<th align='left'>:<input type='text' name='fee' value =".$row['fee']."></th>
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
		echo " please, Enter Event ID ";
		include("findEvent.php");
	}

?>
