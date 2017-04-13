<?php
	include("connect.php");
	
		$name=$_POST["name"];
		$text=$_POST["text"];
		$date=$_POST["date"];
		$time=$_POST["time"];
		$venue=$_POST["venue"];
		$contact=$_POST["contact"];
		$fee=$_POST["fee"];
		$eventid=$_POST["eventid"];
		
	if (is_uploaded_file($_FILES['myimage']['tmp_name']) && $_FILES['myimage']['error']==0) {
    	$imagetmp=addslashes(file_get_contents($_FILES['myimage']['tmp_name']));
    	$imagename=$_FILES['myimage']['name'];
  	} else {
    	echo "The file was not uploaded successfully.";
    	echo "(Error Code:" . $_FILES['my-file']['error'] . ")";
  	}

		//$imagename
		//$imagetmp=addslashes(file_get_contents($imagename);

	$q="UPDATE event SET name='$name' , text ='$text' , date = '$date' , time = '$time' , venue = '$venue' , 
			contact = '$contact' , fee = '$fee' , imagename='$imagename' , imagetmp='$imagetmp' WHERE eventid='$eventid' ";

	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());

	if(empty($result))
	{
		echo "Wrong " ;
	}
	include("DisplayEvent.php")
?>