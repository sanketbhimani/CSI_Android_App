<?php
	include("connect.php");
		
		$ename=$_GET["ename"];
		$name=$_GET["name"];
		$csiid=$_GET["csiid"];
		$feedback=$_GET["feedback"];
	
	$q="UPDATE ".$ename." SET feedback='$feedback' WHERE csiid='".$csiid."' ";
	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());


	if($result == null)
	{
		$t = array( 'submited' => "false" );
	}
	else
	{
		$t = array( 'submited' => "true" );
		
	}
	print(json_encode($t));

?>