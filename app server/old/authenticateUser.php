<?php

	include("connect.php");

	$email_id = $_GET['email'];
	$pwd=$_GET['password'];
	$q = "SELECT `password` FROM `user` WHERE `email`='".$email_id."'";

	$q_result = mysql_query($q)
	 or die("sdas".mysql_error());
	$f = mysql_fetch_array( $q_result);
	
	if($f == null)
	{
		echo("false");
	}
	else if( $pwd == $f['password'] )
	{
	echo("true" );
	}
	else
	{
		echo("false" );
	}
	
?>