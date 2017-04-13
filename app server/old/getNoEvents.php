<?php

	include("connect.php");

	$q = "SELECT * FROM `event` ";

	$q_result = mysql_query($q)
	 or die("sdas".mysql_error());
	$f = mysql_num_rows($q_result);
	$t = array( 'no' => "$f" );

	if($f == null)
	{
		echo("null");
	}
	else
	{
		print(json_encode($t));
	}

?>