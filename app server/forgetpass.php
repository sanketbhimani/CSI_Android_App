<?php

include("connect.php");
	$email = $_GET['email'];
	$q = "SELECT * FROM `user` WHERE `email`='".$email."'";
	$r = mysql_query($q);
	
	if(mysql_num_rows($r)==0){
		echo("false");
	}
	else{
		$a = mysql_fetch_array($r);
		include('mail/class.phpmailer.php');
		$mail = new PHPMailer();
$mail->IsSMTP();
$mail->SMTPDebug = 1;
$mail->SMTPAuth = true;
$mail->SMTPSecure = 'tls';
$mail->Host = "smtp.gmail.com";
$mail->Port = 587;
$mail->IsHTML(true);
$mail->Username = "snk.bhimani.jnd@gmail.com";
$mail->Password = "SNK.bhimani3";
$mail->SetFrom("snk.bhimani.jnd@gmail.com");
$mail->Subject = "Your password for CSI account";
$mail->Body = "Your password is: "+$a['password'];
	$mail->AddAddress($email);
$mail->Send();
echo("true");
	}
	
	
	?>