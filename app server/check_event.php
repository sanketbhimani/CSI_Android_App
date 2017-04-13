<?php
session_start();
if(!isset($_SESSION['login'])){
	header('location:index.php');
}

?>


<?php
	if((!empty($_POST["name"])) && (!empty($_POST["text"])) && (!empty($_POST["date"])) && (!empty($_POST["time"])) && (!empty($_POST["venue"])) && (!empty($_POST["contact"])) && (!empty($_POST["fee"])) )
		{
		$name=$_POST["name"];
		$text=$_POST["text"];
		$date=$_POST["date"];
		$time=$_POST["time"];
		$venue=$_POST["venue"];
		$contact=$_POST["contact"];
		$no = $_POST["no"];
		$fee=$_POST["fee"];
		    		$file_tmp = null;
		if (isset($_FILES['image'])) {
    		$file_tmp =$_FILES['image']['tmp_name'];
  		} else {
    		echo "The file was not uploaded successfully.";
  		}
		include("connect.php");
		
		$q="INSERT INTO `event` (`name`,`text`,`date`,`time`,`venue`,`contact`,`fee`) VALUES ('".$name."','".$text."','".$date."','".$time."','".$venue."','".$contact."','".$fee."')";
		mysql_query($q) or die('Query1 failed: ' . mysql_error());
		$file_name = mysql_insert_id();
		move_uploaded_file($file_tmp,"images/".$file_name.".jpeg");
		$q1="CREATE TABLE ".$file_name."_reg"." (
		ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
			user_id VARCHAR(10)
			)";
			mysql_query($q1) or die('Query2 failed: ' . mysql_error());
		$q1="CREATE TABLE ".$file_name."_code"." (
		ID int NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
			code VARCHAR(10), 
			for_csi_mem BOOLEAN,
			used BOOLEAN,
			user_id VARCHAR(10)
			)";
		mysql_query($q1) or die('Query3 failed: ' . mysql_error());
		/*for($i=0;$i<$no;$i++){
			$rnd_str = range(0,9);
			shuffle($rnd_str);
			echo(implode("",$rnd_str)."<br>");
			$q = "INSERT INTO `".$file_name."_code"."` (`code`, `for_csi_mem`,`used`) VALUES ('".implode("",$rnd_str)."','".true."','".false."')";
			mysql_query($q) or die(mysql_error());
		}*/
		for($i=0;$i<$no;$i++){
			$rnd_str = range(0,9);
			shuffle($rnd_str);
			echo(implode("",$rnd_str)."<br>");
			$q = "INSERT INTO `".$file_name."_code"."` (`code`, `for_csi_mem`,`used`) VALUES ('".implode("",$rnd_str)."','".false."','".false."')";
			mysql_query($q) or die(mysql_error());
		}
		include("newEvent.php");
		}
	else{
		echo "<html><body><p id='p_check_user'>Invalid Inputs<p></body></html>";
		include("newEvent.php");
		}

?>