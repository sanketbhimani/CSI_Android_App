<?php

	include("connect.php");

	$id = $_GET['id'];
	$u_id = $_GET['user_id'];
	
	$qq = "SELECT * FROM `".$id."_reg` WHERE `user_id` = '".$u_id."'";
	//echo($qq);
	$qq_result = mysql_query($qq);
	$num_rows = mysql_num_rows($qq_result);
	$selected = "";
	if($num_rows >0){
		$selected = "yes";
	}
	else{
		$selected = "no";
	}
	
	$q = "SELECT * FROM `event` WHERE `eventid`='".$id."'";

	$q_result = mysql_query($q)
	 or die("sdas".mysql_error());
	$f = mysql_fetch_array( $q_result);

	
	if($f == null)
	{
		$t2 = array('event' => (array('validEvent' => "no")));
		print(json_encode($t2));
	}
	else
	{
		$t = array('event' => (array('validEvent' => "yes", 'eventid' => $f['eventid'] , 'name' => $f['name'] , 'text' => $f['text'] , 'date' => $f['date'] , 'time' => $f['time'] , 'venue' => $f['venue'] , 'contact' => $f['contact'], 'selected' => $selected ,'fee' => $f['fee'])));

		print(json_encode($t));
	}

?>