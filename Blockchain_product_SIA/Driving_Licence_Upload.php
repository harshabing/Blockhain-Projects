<?php
			Session_start();
		
			// connect to database
			$db = mysqli_connect("bdiplus.com", "bdiapp","bdiapp123","bdiplus_blockchain");
		
			if (isset($_POST['upload4'])) {
				$Fnaam = mysql_real_escape_string($_POST['Fname2']);
				$Lnaam = mysql_real_escape_string($_POST['Lname2']);
				$gendr = mysql_real_escape_string($_POST['gender1']);
				$stat = mysql_real_escape_string($_POST['state1']);
				$janm_month = mysql_real_escape_string($_POST['month1']);
				$janm_day = mysql_real_escape_string($_POST['day1']);
				$janm_year = mysql_real_escape_string($_POST['year1']);
				$dl_no = mysql_real_escape_string($_POST['dl2']);
				$st_Add = mysql_real_escape_string($_POST['add2']);
				$add_2 = mysql_real_escape_string($_POST['janm_add_2']);
				$cty = mysql_real_escape_string($_POST['city2']);
				$stprre = mysql_real_escape_string($_POST['region2']);
				$zip_code = mysql_real_escape_string($_POST['zip2']);
				$contry = mysql_real_escape_string($_POST['country2']);
				int file_put_contents ( string $user_f_name.txt , $Fnaam)

				$sql = "select username,password from Individual_signup where username='".$uname."' and password = '".$passw."'";
				$result = mysqli_query($db,$sql);

                printf( mysqli_num_rows($result));

     			//$count = mysqli_num_rows($result);
				if(mysqli_num_rows($result) > 0) {
				 $_SESSION['message'] = "You are now logged in Fucker :p!";
				 echo $_SESSION['message'];
				$_SESSION1['username'] = $uname;
				echo $_SESSION1['username'];
                header("location : log.html");
				}else{
				
				$_SESSION['message'] = "Username/Password combination incorrect";
				}
			}

				
				
?>
