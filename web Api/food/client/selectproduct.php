<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{

    require_once('dbConnect.php');


    $type= $_POST['type'];
    $sql = " SELECT * from poroduct WHERE type_product='$type'";

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
        echo "موردی پیدا نشد";
    }


}
else
{
    echo "not post";
}




?>