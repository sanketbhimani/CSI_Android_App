<?php
	if((!empty($_POST["name"])) && (!empty($_POST["lastname"])) && (!empty($_POST["bday"]))  && (!empty($_POST["csiid"])) && (!empty($_POST["email"])) && (!empty($_POST["mobileno"])) )
		{
		$name=$_POST["name"];
		$lastname=$_POST["lastname"];
		$bday=$_POST["bday"];
		$csiid=$_POST["csiid"];
		$email=$_POST["email"];
		$mobileno=$_POST["mobileno"];
		
		include("connect.php");
		
		$q="INSERT INTO user (name,lastname,bday,csiid,email,mobileno) VALUES ('$name','$lastname','$bday','$csiid','$email','$mobileno')";
		mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());

		$q1="INSERT into userdb(csiid,password) VALUES ('$csiid','$bday')";
		mysql_query($q1) or die('Query "' . $q1 . '" failed: ' . mysql_error());
		include("new_user.php");
		}
	else{
		
		echo "<html><body><p id='p_check_user'>Invalid Inputs<p></body></html>";
		include("new_user.php");
		}

?>