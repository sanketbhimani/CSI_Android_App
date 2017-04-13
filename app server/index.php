<?php
if(isset($_POST['uname']) && isset($_POST['pass'])){
	
	if($_POST['uname']=="ssssss" && $_POST['pass']=="youcanwin@123"){
		session_start();
		$_SESSION['login'] = "true";
		header("location:home.php");
	}
}
?>


<html>

<body>
<form action="index.php" method="post">
<table>
<tr>
<td>username:</td><td> <input type="text" name="uname"></td>
</tr>
<tr>
<td>password:</td><td><input type="password" name="pass"></td>
</tr>
</table>
<input type="submit" value="submit">


</form>




</body>

</html>
