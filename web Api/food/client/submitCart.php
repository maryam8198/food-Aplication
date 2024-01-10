<?php
if($_SERVER['REQUEST_METHOD']=='POST')
 {
    require_once('dbConnect.php');
    $usernamee = $_POST['username'];

    $sql1 = "SELECT * FROM user WHERE adress='null' AND user_name='$usernamee'";

    $r = mysqli_query($connection, $sql1);

        $num_rows = mysqli_num_rows($r);
        if ($num_rows <=0)
        {  
            $sql=("DELETE FROM cart WHERE id_user='$usernamee' ");

            $r = mysqli_query($connection, $sql);
            if ($r)
            {
                echo "ارسال شد ";
            }
            else
            {
                echo "خطایی رخ داده";
            }
        }
        else
        {
            echo "ابتدا اطلاعات خود را کامل کنید ";
        }

  
 }

 ?>