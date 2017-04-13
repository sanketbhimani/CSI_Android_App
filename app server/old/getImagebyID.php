<?php

	include("connect.php");

	$id = $_GET['id'];
	$q = "SELECT imagetmp FROM `event` WHERE `eventid`=$id";


	$result = mysql_query($q) or die(mysql_error());  
	$row = mysql_fetch_array($result);

	header("Content-type: image/jpeg");
	echo $row['imagetmp'];

?>