<?php
$host = "localhost";
$uname = "root";
$pass = "";

$dbhandle = mysql_connect($host,$uname,$pass)
  or die("Unable to connect to MySQL");

mysql_select_db("csi",$dbhandle) 
  or die("Could not select examples");
?>