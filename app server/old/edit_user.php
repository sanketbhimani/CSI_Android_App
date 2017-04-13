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
		$q = "UPDATE  `user` SET `name` = '".$name."', `email` = '".$email."',  `branch` = '".$branch."', `year`= '".$year."', `mobile_no`= '".$mobile_no."', `password`='".$password."') WHERE `id`='".$_GET['userid']."'";
		mysql_query($q);
	}
	else if($is_csi_member == "yes"){
		$csi_id = $_GET['csi_id'];
		$q = "UPDATE  `user` SET `name` = '".$name."', `email` = '".$email."',  `branch` = '".$branch."', `year`= '".$year."', `mobile_no`= '".$mobile_no."', `password`='".$password."', `csi_id`='".$_GET['csi_id']."') WHERE `id`='".$_GET['userid']."'";
		mysql_query($q);
	}
	$id = mysql_insert_id();
	echo($id);
	?>