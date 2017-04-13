<?php

	include("connect.php");

	$options = '<option>----SELECT----</option>';

	$filter=mysql_query("select name from event");
	while($row = mysql_fetch_array($filter)) {
	    $options .="<option>" . $row['name'] . "</option>";
	}

	$menu="
	<html>
	<head>
	   <title>
    	Select Event
   		</title>
    	<link rel='stylesheet' type='text/css' href='stylesheet.css'>
   	</head>
	<body>
  		 <center><br><br>
  	<form id='filter' name='filter' method='post' action='EventDetails.php'>
	  	  <p><label>Select Event : </label></p>
	     <select name='filter' id='filter'>
	      " . $options . "
	     </select></br>
	     <input type='submit'id='submit' class='button'></input>
	</form>
	<button id='home' class='button'><a href='home.php'>Home</a></button>
	<button id='logout' class='button'><a href='logout.php'>Logout</a></button>";

	echo $menu;


?>