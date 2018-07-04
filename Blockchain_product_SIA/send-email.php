<?php
if (isset($_POST['sendreq'])) {
require 'class.phpmailer.php';
$mail = new PHPMailer();
$mail->IsSMTP();
$mail->Mailer = 'smtp';
$mail->SMTPAuth = true;
$mail->SMTPDebug = 2;
$mail->Host = 'smtp.gmail.com'; // "ssl://smtp.gmail.com" didn't worked
$mail->Port = 465;
//$mail->SMTPSecure = 'ssl';
//or try these settings (worked on XAMPP and WAMP):
// $mail->Port = 587;
$mail->SMTPSecure = 'tls';
$emailshare = mysql_real_escape_string($_POST['email_sharing']);
$fnamesharing = mysql_real_escape_string($_POST['fname_sharing']);
$lnamesharing = mysql_real_escape_string($_POST['lname_sharing']);
 $mail->SMTPOptions = array(
    'ssl' => array(
        'verify_peer' => false,
        'verify_peer_name' => false,
        'allow_self_signed' => true
    )
);
$mail->Username = "harshbing@gmail.com";
$mail->Password = "Hlaoesah41";
 
$mail->IsHTML(true); // if you are going to send HTML formatted emails
$mail->SingleTo = true; // if you want to send a same email to multiple users. multiple emails will be sent one-by-one.
 
$mail->From = "harshbing@gmail.com";
$mail->FromName = "bdiBlock";
 
$mail->addAddress($emailshare,"User 1");
 
$mail->Subject = "SignUp Request from your family member!";
$mail->Body = "Hey,<br /><br /> ".$fnamesharing." ".$lnamesharing.". Hope you are doing great today! Your family member has requested you to SignUp on BdiBlock to Share an important informtion with you. God, you must be Special!  Link to SignUp: http://bdiplus.com/iana/blockchain/ ";

 
if(!$mail->Send())
    echo "Message was not sent <br />PHPMailer Error: " . $mail->ErrorInfo;
else
    header("Location:index.html");
}
?>