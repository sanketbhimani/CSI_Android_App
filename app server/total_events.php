<?php
session_start();
if(!isset($_SESSION['login'])){
	header('location:index.php');
}

?>

<html>
<body>
<h2>all events</h2>
<?php
	include("connect.php");
	$q = "SELECT * FROM `event`";
$q_result = mysql_query($q);
while($f = mysql_fetch_array( $q_result)){
	echo("<a href='view_reg.php?id=".$f['eventid']."&name=".$f['name']."' >".$f['name']."</a><br>");
}


?>
 	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>  

</body>
</html>