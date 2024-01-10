<?php

require_once('dbConnect.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
    $flagUserNamee = $_POST['flagUserName'];

    $sql = " SELECT * from user where user_name='$flagUserNamee'";

    $r = mysqli_query($connection,$sql);

    $num_rows = mysqli_num_rows($r);
    if ($num_rows > 0)
    {
        $productarray = array();
        if (mysqli_num_rows($r) > 0) {
            while ($row = mysqli_fetch_assoc($r)) {
                $productarray[] = $row;
            }
        }
        echo json_encode(array( "response" =>$productarray ));
    }
    else
    {
        echo "not found";
    }

}
else
{
    echo 'not post';
}
?>