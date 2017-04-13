	<?php
	session_start();
	if(!isset($_SESSION['login'])){
		header('location:index.php');
	}
	
	if(!isset($_GET['id']) || !isset($_GET['name'])){
		header('location:total_events.php');
	}

	?>
	

<html>

<body>
 	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>  

<h2> registration for <?php echo($_GET['name']); ?></h2>
<table border="1">
<tr>
<th>id</th>
<th>name</th>
<th>branch</th>
<th>year</th>
<th>email</th>
<th>mobile no</th>
<th>code</th>
<tr>
<?php
$id = $_GET['id'];
include("connect.php");

$q = "SELECT * FROM user WHERE id IN (SELECT id FROM ".$id."_reg)";
$r = mysql_query($q);
while($f = mysql_fetch_array($r)){
	?>
	<tr>
	<td><?php echo($f['id']); ?></td>
	<td><?php echo($f['name']); ?></td>
	<td><?php echo($f['branch']); ?></td>
	<td><?php echo($f['year']); ?></td>
	<td><?php echo($f['email']); ?></td>
	<td><?php echo($f['mobile_no']); ?></td>
	<td><?php  $q1 = "select code from ".$id."_code where user_id = '".$f['id']."'";
$r1 = mysql_query($q1);
while($a = mysql_fetch_array($r1)){
	echo($a['code']."<br>");
}

	?></td>
	
	</tr>
	
	
	<?php
}



?>
</table>
 	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>  

</body>

</html>