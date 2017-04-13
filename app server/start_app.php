<?php
session_start();
if(!isset($_SESSION['login'])){
	header('location:index.php');
}
include("connect.php");


		$q = "update stop_app set stop = '0'";
		mysql_query($q);
		$q = "update stop_app set message = ''";
		mysql_query($q);
	header('location:stop_app.php');

?>
