<?php

include("connect.php");


	
	$q = "SELECT * FROM stop_app";
		$r = mysql_query($q);
		$a = mysql_fetch_array($r);
		$t = array('stop_app' => (array('stop' => $a['stop'], 'message' => $a['message'])));
		print(json_encode($t));

	
	?>