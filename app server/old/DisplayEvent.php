<?php
/*echo($_SESSION['session']);
	if($_isset[$_SESSION['session']])
		{	
		header("location:index.html");
		}*/
?>
<html>
 <head>
   <title>
    Event Details
   </title>
   <link rel='stylesheet' type='text/css' href='stylesheet.css'>
  </head>
  <body>

   <center>
   <h1> Event Details</h1>
   <div>
   <br>
   <br>
   <br>  
   <table border="1" cellpadding="0" cellspacing="0" width="90%">
   <tr>
   <th>Index</th>
   <th>Image</th>
   <th>Description</th>
   <th>Date</th>
   <th>Time</th>
   <th>Venue</th>
   <th>Contact</th>
   <th>Registration fee</th>
   </tr>
   
      <?php
	include("connect.php");
	
	$q="SELECT * FROM event";
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
			<th>{$row['text']}</th>
			<th>{$row['date']}</th>
			<th>{$row['time']}</th>
			<th>{$row['venue']}</th>
			<th>{$row['contact']}</th>
			<th>{$row['fee']}</th>
			</tr>";	
	$index++;
   }
   
   
   ?>
   
   </table>
   <br>
   <br>
	<button id="home" class='button'><a href="home.php">Home</a></button>
	<button id="logout" class='button'><a href="logout.php">Logout</a></button>
	
	</center>
  </body>
</html>
