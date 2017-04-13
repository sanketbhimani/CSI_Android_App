<?php
	include("connect.php");
		
		$ename=$_GET["ename"];
		$name=$_GET["name"];
		$branch=$_GET["branch"];
		$sem=$_GET["sem"];
		$csiid=$_GET["csiid"];
		$email=$_GET["email"];
		$contact=$_GET["contact"];
	
	$q="INSERT INTO ".$ename." (name,branch,sem,csiid,email,contact) VALUES ('".$name."','".$branch."','".$sem."','".$csiid."','".$email."','".$contact."')";

	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());


	if($result == null)
	{
		$t = array( 'registered' => "false" );
	}
	else
	{
		$t = array( 'registered' => "true" );
		
	}
	print(json_encode($t));

?>