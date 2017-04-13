<?php
session_start();
if(!isset($_SESSION['login'])){
	header('location:index.php');
}

?>
<html>
<body>
<a href="newEvent.php">add event</a><br>
<a href="total_events.php">view ragistration</a><br>
<a href="stop_app.php">stop application</a><br>
<a href="logout.php">logout</a>
</body>

</html>