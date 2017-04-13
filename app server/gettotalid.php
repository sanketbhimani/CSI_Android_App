<?php
include("connect.php");
$q = "SELECT * FROM `event`";
$q_result = mysql_query($q)
	 or die("sdas".mysql_error());
	 $i = "1";
	 $total = mysql_num_rows($q_result);
	 $t['event']['total_event'] = $total;
while($f = mysql_fetch_array( $q_result)){
	$t['event'][$i] = $f['eventid'];
	$i++;
}
  header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Methods: GET, POST, OPTIONS');
    header('Access-Control-Allow-Headers: Origin, Content-Type, Accept, Authorization, X-Request-With');
    header('Access-Control-Allow-Credentials: true');
print(json_encode($t));

?>