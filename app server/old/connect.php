<?php
$username = "root";
$hostname = "localhost"; 

$dbhandle = mysql_connect("$hostname", "$username", "") 
  or die("Unable to connect to MySQL");

mysql_select_db("CSI",$dbhandle) 
  or die("Could not select examples");
?>