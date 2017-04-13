<?php
	if((!empty($_POST["name"])) && (!empty($_POST["text"])) && (!empty($_POST["date"])) && (!empty($_POST["time"])) && (!empty($_POST["venue"])) && (!empty($_POST["contact"])) && (!empty($_POST["fee"])) )
		{
		$name=$_POST["name"];
		$text=$_POST["text"];
		$date=$_POST["date"];
		$time=$_POST["time"];
		$venue=$_POST["venue"];
		$contact=$_POST["contact"];
		$fee=$_POST["fee"];
		    		$file_tmp = null;
		if (isset($_FILES['image']) {
    		$file_tmp =$_FILES['image']['tmp_name'];
  		} else {
    		echo "The file was not uploaded successfully.";
  		}
		include("connect.php");
		
		$q="INSERT INTO event (name,text,date,time,venue,contact,fee) VALUES ('$name','$text','$date','$time','$venue','$contact','$fee')";
		mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());
		$file_name = mysql_insert_id();
		move_uploaded_file($file_tmp,"images/".$file_name);
		$q1="CREATE TABLE ".$name." (
			name VARCHAR(50), 
			branch VARCHAR(50),
			sem VARCHAR(50),
			csiid VARCHAR(50),
			email VARCHAR(50),
			contact VARCHAR(50),
			feedback VARCHAR(100)
			)";
			mysql_query($q1) or die('Query "' . $q . '" failed: ' . mysql_error());
		
		include("newEvent.php");
		}
	else{
		echo "<html><body><p id='p_check_user'>Invalid Inputs<p></body></html>";
		include("newEvent.php");
		}

?>