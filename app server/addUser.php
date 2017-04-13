<?php

	include("connect.php");
	$name = $_GET['name'];
	$email = $_GET['email'];
	$password = $_GET['password'];
	$mobile_no = $_GET['mobile_no'];
	$branch = $_GET['branch'];
	$year = $_GET['year'];
	$is_csi_member = $_GET['is_csi_member'];
	$qq = "SELECT * FROM `user` WHERE `email` = '".$email."'";
	$rr = mysql_query($qq);
	if(mysql_num_rows($rr)==0){
		
			if($is_csi_member == "no"){
		$q = "INSERT INTO `user` (`name`, `email`, `branch`, `mobile_no`, `password`, `year`) VALUES ('".$name."','".$email."','".$branch."','".$mobile_no."','".$password."', '".$year."')";
		mysql_query($q)
		or die(mysql_error());
	}
	else if($is_csi_member == "yes"){
		$csi_id = $_GET['csi_id'];
		$q = "INSERT INTO `user` (`name`, `email`, `branch`, `mobile_no`, `password`,`csiid`, `year`) VALUES ('".$name."','".$email."','".$branch."','".$mobile_no."','".$password."','".$_GET['csi_id']."','".$year."')";
		mysql_query($q)
		or die(mysql_error());
	}
	echo("yes");
	}
	else{
		echo("no");
	}
	
	

	
	
	?>