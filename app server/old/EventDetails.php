<?php
	$eventname=$_POST["filter"];
	
	if ( $eventname=="----SELECT----")
	{
		echo "Select an Event to proceed";
		include("Events.php");
	}
	else
	{
		include("connect.php");

		$q="SELECT * from ".$eventname;
		$result=mysql_query($q);
		$table="<table border=1' cellpadding='0' cellspacing='0' width='90%''>
				<tr>
				<th>No</th>
				<th>Name</th>
				<th>Branch</th>
				<th>Semester</th>
				<th>Contact</th>
				</tr>";
		$index=1;
		while ($row = mysql_fetch_array($result)) {
			$table.="<tr>
			<th>{$index}</th>
			<th>{$row['name']}</th>
			<th>{$row['branch']}</th>
			<th>{$row['sem']}</th>
			<th>{$row['contact']}</th>
			</tr>";
			$index++;
		}
	}

	echo $table;

?>