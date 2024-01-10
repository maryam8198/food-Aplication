<?php 

if ($_SERVER['REQUEST_METHOD'] == 'POST') 
{
    require_once('dbConnect.php');

    $userNamee = $_POST['username'];
    $address = $_POST['address'];

    $sql ="UPDATE user SET adress='$address'  WHERE user_name ='$userNamee' ";

    if (mysqli_query($connection, $sql))
        {
            echo("آدرس ذخیره شد");
        }
        else
        {
            echo("خطا رخ داده ");
        }
    

}

?>