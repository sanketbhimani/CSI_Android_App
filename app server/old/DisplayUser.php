<?php
echo($_SESSION['session']);
	if($_isset[$_SESSION['session']])
		{	
		header("location:index.html");
		}
?>

<html>
 <head>
   <title>
     User Details
   </title>
   <link rel='stylesheet' type='text/css' href='stylesheet.css'>
  </head>
  <body>
   <center><br><br>
   <h1>  User Details</h1>
   <div>
   <br>
   <br>
   <br>  
   <table border="1" cellpadding="0" cellspacing="0" width="90%">
   <tr>
   <th>Index</th>
   <th>First Name</th>
   <th>Last name</th>
   <th>Birthday</th>
   <th>CSI Id</th>
   <th>Email</th>
   <th>Contact No.</th>
   </tr>
   
   <?php
	include("connect.php");
	
	$q="SELECT * FROM user";
	$result=mysql_query($q) or die('Query "' . $q . '" failed: ' . mysql_error());
	$index=0;
    if(! $result ) 
	{
      die('Could not get data: ' . mysql_error());
	}
   $index++;
   while($row = mysql_fetch_array($result, MYSQL_ASSOC)) 
   {
      echo "<tr>
			<th>{$index}</th>
			<th>{$row['name']}</th>
			<th>{$row['lastname']}</th>
			<th>{$row['bday']}</th>
			<th>{$row['csiid']}</th>
			<th>{$row['email']}</th>
			<th>{$row['mobileno']}</th>
			</tr>";	
	$index++;
   }
   
   
   ?>
   
   <table>
   <br>
   <br>
	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>
   <br>
   <br>
   </div>
   </center>
  </body>
</html>