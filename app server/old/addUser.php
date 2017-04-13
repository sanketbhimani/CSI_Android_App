<?php

	include("connect.php");
	$name = $_GET['name'];
	$email = $_GET['email'];
	$password = $_GET['password'];
	$year = $_GET['year'];
	$mobile_no = $_GET['mobile_no'];
	$branch = $_GET['branch'];
	$is_csi_member = $_GET['is_csi_member']
	if($is_csi_member === "no"){
		$q = "INSERT INTO `user` (`name`, `email`, `branch`, `year`, `mobile_no`, `password`) VALUES ('".$name."','".$email."','".$branch."','".$year."','".$mobile_no."','".$password."')";
		mysql_query($q);
	}
	else if($is_csi_member == "yes"){
		$csi_id = $_GET['csi_id'];
		$q = "INSERT INTO `user` (`name`, `email`, `branch`, `year`, `mobile_no`, `password`,`csi_id`) VALUES ('".$name."','".$email."','".$branch."','".$year."','".$mobile_no."','".$password."','".$is_csi_member."')";
		mysql_query($q);
	}
	
	?>