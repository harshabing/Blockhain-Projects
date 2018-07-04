<?php
// The message
if (isset($_POST['sendreqs'])) {
$message = "oh come on,this is just for testing purpose dont be mad at me. will make this a lot cool, pretty soon ;) ";

// In case any of our lines are larger than 70 characters, we should use wordwrap()
$message = wordwrap($message, 70);

// Send
mail('harshabing@gmail.com', 'My Subject', $message);
}
?> 