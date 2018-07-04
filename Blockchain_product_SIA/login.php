
<?php
			Session_start();
			// connect to database
			$db = mysqli_connect("bdiplus.com", "bdiapp","bdiapp123","bdiplus_blockchain");
		
			if (isset($_POST['Sign_In'])) {
				$uname = mysql_real_escape_string($_POST['username_log']);
				$passw = mysql_real_escape_string($_POST['pass_log']);

				//file_put_contents ('$usernaam.txt' ,'".$unaam."' );
				//$thefile = 'usernaam.txt';
               //$ttm =file_put_contents($thefile,$uname);
               // echo $ttm;

             // shell_exec("C:\wamp\www\pypl.pypl")

               
			   $password = md5($passw);
				$sql = "select username,password from Individual_signup where username='".$uname."' and password = '".$passw."'";
				$result = mysqli_query($db,$sql);

     			//$count = mysqli_num_rows($result);
				if(mysqli_num_rows($result) == 1 and $uname != null ) {
				 header("Location:TrialHomepage.html");
				}else{
				
				echo " Error Code: 101,  Oops. Something went wrong. Please try again";
				}
			}

				
				
?>
