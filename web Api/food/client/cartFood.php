<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
     require_once('dbConnect.php');

     $id=$_POST['id'];
     $user_name = $_POST['user_name'];
     $price = $_POST['price'];
     $pic = $_POST['pic'];


     $sql = " INSERT INTO cart (id_product , id_user, price,pic_product) 
              VALUES ('$id', '$user_name', '$price','$pic')";

              
        if (mysqli_query($connection, $sql))
        {
            echo("ok");
        }
        else
        {
            echo("falid");
        }
}
else {
    echo "error";
}

?>