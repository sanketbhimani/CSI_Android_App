<?php
	include("connect.php");

	$eventid=$_POST["eventid"];

	$q="DELETE from event WHERE eventid='$eventid' ";

	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());

	if(empty($result))
	{
		echo "Wrong " ;
	}
	include("DisplayEvent.php")
?>