<?php

	include("connect.php");

	$id = $_GET['id'];
	$q = "SELECT * FROM `event` WHERE `eventid`=$id";

	$q_result = mysql_query($q)
	 or die("sdas".mysql_error());
	$f = mysql_fetch_array( $q_result);

	$t = array('event' => (array('validEvent' => "yes", 'eventid' => $f['eventid'] , 'name' => $f['name'] , 'text' => $f['text'] , 'date' => $f['date'] , 'time' => $f['time'] , 'venue' => $f['venue'] , 'contact' => $f['contact'] ,'fee' => $f['fee'])));

	if($f == null)
	{
		$t2 = array('event' => (array('validEvent' => "no")));
		print(json_encode($t2));
	}
	else
	{
		print(json_encode($t));
	}

?>