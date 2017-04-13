<?php

	include("connect.php");

	$email_id = $_GET['email'];
	$pwd=$_GET['password'];
	$q = "SELECT * FROM `user` WHERE `email`='".$email_id."'";

	$q_result = mysql_query($q)
	 or die("sdas".mysql_error());
	$f = mysql_fetch_array( $q_result);
	
	$auth = "false";
	
	
	if($f == null)
	{
		$auth = "false";
	}
	else if( $pwd == $f['password'] )
	{
	$auth = "true";
	}
	else
	{
		$auth = "false";
	}
	if($auth == "true"){
	
	$t = array('user_info' => (array('valid' => "true", 'user_name' => $f['name'] , 'user_email' => $f['email'] ,'user_mobile' => $f['mobile_no'] ,  'user_branch' => $f['branch'] , 'user_password' => $f['password'] , 'user_id' => $f['id'] )));
	print(json_encode($t));
	}
	else{
		$t = array('user_info' => (array('valid' => "false")));
		print(json_encode($t));
	}
	
	
?>