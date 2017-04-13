<?php
	include("connect.php");
	
		$name=$_POST["name"];
		$lastname=$_POST["lastname"];
		$bday=$_POST["bday"];
		$csiid=$_POST["csiid"];
		$email=$_POST["email"];
		$mobileno=$_POST["mobileno"];
	
	$q="UPDATE user SET name='$name' , lastname='$lastname' , bday='$bday' , email = '$email' , mobileno = '$mobileno' WHERE csiid='$csiid' ";
	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());

	$q1="UPDATE userdb SET password='$bday' WHERE csiid='$csiid' ";
	$result=mysql_query($q1) or die('Query "' . $q . '" failed: ' . mysql_error());

	if(empty($result))
	{
		echo "Wrong " ;
	}
	include("DisplayUser.php")
?>