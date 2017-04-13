<?php
	include("connect.php");
	if(isset($_GET['code']) && $_GET['userid'] && $_GET['eventid']){
		$q = "SELECT `csiid` FROM `user` WHERE `id`='".$_GET['userid']."'";
		$r = mysql_query($q);
		$a = mysql_fetch_array($r);
		$q1 = "SELECT * FROM `".$_GET['eventid']."_code`  WHERE `code` = '".$_GET['code']."' AND `used`='0'";
		$r1 = mysql_query($q1);
		$num_rows = mysql_num_rows($r1);
		$a1 = mysql_fetch_array($r1);
		if($num_rows > 0){
			if($a1['for_csi_mem'] == "1"){
				if($a['csiid'] != "none"){
					$q3 = "UPDATE `".$_GET['eventid']."_code` SET `used` = '".true."', `user_id` = '".$_GET['userid']."' WHERE `code` = '".$_GET['code']."'";
					mysql_query($q3);
					$q = "INSERT INTO `".$_GET['eventid']."_reg` (`user_id`) VALUES ('".$_GET['userid']."')";
					mysql_query($q);
					echo("true");
				}
				else{
					echo("false");
				}
			}
			else{
				$q3 = "UPDATE `".$_GET['eventid']."_code` SET `used` = '".true."', `user_id` = '".$_GET['userid']."' WHERE `code` = '".$_GET['code']."'";
				mysql_query($q3);
				$q = "INSERT INTO `".$_GET['eventid']."_reg` (`user_id`) VALUES ('".$_GET['userid']."')";
					mysql_query($q);
				echo("true");
			}
		}
		else{
			echo("false");
		}
	} 
?>