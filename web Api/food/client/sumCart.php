<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
    require_once('dbConnect.php');

    $usernamee = $_POST['username'];
    $sql_sum=" SELECT SUM(price) AS price FROM cart WHERE id_user='$usernamee' GROUP BY id_user";
    
    $r = mysqli_query($connection, $sql_sum);
    $num_rows = mysqli_num_rows($r);
    if ($num_rows > 0) 
    {
        $productarray = array();
        if (mysqli_num_rows($r) > 0) {
            while ($row = mysqli_fetch_assoc($r)) {
                $productarray[] = $row;
            }
        }
      
        echo json_encode(array("price" => $productarray));
    }

    else
{
    echo "error";
}
}
?>