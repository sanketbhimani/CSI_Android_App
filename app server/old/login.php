<?php
	
	if( $_POST['username'] != '' && $_POST['password'] != '')
	{
	$uname = $_POST['username'];
	$pass = $_POST['password'];
	

	//include("connect.php");

	//$q = "SELECT password FROM `admindb` WHERE `uname`='".$uname."'";

	//$q_result = mysql_query($q)
	 //or die("sdas".mysql_error());
	//$f = mysql_fetch_array( $q_result);

		if( $pass == "aaa"  )
		{
		session_start();
		$_SESSION['session'] = "admin";
		header("location:home.php");
		}
		else
		{
			header("location:index1.html");
		}
	}
	else{
		header("location:index1.html");
	}
?>