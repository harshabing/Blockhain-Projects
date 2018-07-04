<?php

$xx = file_get_contents("https://sia.pixeldra.in/api/file/3LiWaK/download") ; // URL of the file you want to download
    header("Content-type: text/plain");
    header("Content-Disposition: attachment; filename=test.txt");
    echo $xx;
?>