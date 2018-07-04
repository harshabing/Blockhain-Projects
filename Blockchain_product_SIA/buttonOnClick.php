<!-- <!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body> -->
	<?php
$servername = "bdiplus.com:3306";
//$port=3306;
$username = "bdiapp";
$password = "bdiapp123";
$dbname = "bdiplus_blockchain";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "INSERT INTO Business_signup (Business_name,Address)
VALUES ('John', 'Doe')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>

<!-- </body>
</html> -->