<?php
	include("connect.php");

	$csiid=$_POST["csiid"];

	$q="DELETE from user WHERE csiid='$csiid' ";

	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());

	if(empty($result))
	{
		echo "Wrong " ;
	}
	include("DisplayUser.php")
?>