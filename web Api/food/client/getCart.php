<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
    require_once('dbConnect.php');

    $usernamee = $_POST['username'];
    $flag = $_POST['flag'];
    
    if($flag == 1)
    {
        $sql = " SELECT * from cart where id_user='$usernamee'";
        $sql_sum=" SELECT SUM(price) FROM cart WHERE id_user='$usernamee'";
    }
    else if($flag == 0)
    {
        $sql = " SELECT * from order where id_user='$usernamee'";

    }

    $r = mysqli_query($connection, $sql);

    $num_rows = mysqli_num_rows($r);
    if ($num_rows > 0) 
    {
        $productarray = array();
        if (mysqli_num_rows($r) > 0) {
            while ($row = mysqli_fetch_assoc($r)) {
                $productarray[] = $row;
            }
        }
      
        echo json_encode(array("result" => $productarray));
        
    }
    else
    {
        echo "موردی پیدا نشد";
    }

}
else
{
    echo "error";
}

?>