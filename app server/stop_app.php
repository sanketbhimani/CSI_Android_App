<?php
session_start();
if(!isset($_SESSION['login'])){
	header('location:index.php');
}
include("connect.php");

if(isset($_POST['msg']) && isset($_POST['stop'])){
	$stop = $_POST['stop'];
	$msg = $_POST['msg'];
	if($stop == "stop application"){
		$q = "update stop_app set stop = '1'";
		mysql_query($q);
		$q = "update stop_app set message = '".$msg."'";
		mysql_query($q);
	}
}

?>

<html>
<body>
<?php
$q1 = "select * from stop_app";
$r = mysql_query($q1);
$a = mysql_fetch_array($r);
if($a['stop'] == '0'){
	


?>
<form action="stop_app.php" method="post">
message:<textarea name="msg"></textarea><br>

type "stop application" <input type="text" name="stop">
<input type="submit" value="stop app">
</form>

<?php
}
else{
	
	?>
	application is stoped <br>
	message: "<?php echo($a['message']); ?>"
	
	<br>
	<a href="start_app.php">start app</a>
	<?php
}

?>
<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>  

</body>
</html>