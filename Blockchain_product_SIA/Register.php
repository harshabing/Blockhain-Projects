
<?php
 
			Session_start();
			// connect to database
			$db = mysqli_connect("bdiplus.com", "bdiapp","bdiapp123","bdiplus_blockchain");

			if (isset($_POST['Sign_Up']))
{ 
 $inFirstname = $_POST["Fname1"]; // as the method type in the form is "post" we are using $_POST otherwise it would be $_GET[]
 $inLastname = $_POST["Lname1"];
 $inEmail = $_POST["email1"];
 $inAddress = $_POST["add1"];
 $inPhone = $_POST["Pnum1"];
 $inUsername = $_POST["Username1"];
 $inPassword = $_POST["Password1"];
 
 //$encryptPassword = password_hash($inPassword, PASSWORD_DEFAULT);

 $sql = ("INSERT INTO Individual_signup(First_Name,Last_Name,Email,Address_Information,Phone_Number,Username,Password) VALUES('".$inFirstname."','".$inLastname."','".$inEmail."','".$inAddress."','".$inPhone."','".$inUsername."','".$inPassword."')"); //Fetching all the records with input credentials
// $stmt->bind_param("ssssisi", $inFirstname,$inLastname,$inEmail,$inAddress,$inPhone,$inUsername,$inPassword); //Where s indicates string type. You can use i-integer, d-double
 //$stmt->execute();
 $result = mysqli_query($db,$sql);
 //$result = $stmt->affected_rows;
 //$stmt -> close();
 $db -> close(); 
 
 if($result > 0)
 {
 header("location: index.html"); // user will be taken to the success page
 }
 else
 {
 echo "Oops. Something went wrong. Please try again"; 
 }
}
?>
</body> 
</html>