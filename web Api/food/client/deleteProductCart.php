<?php
if($_SERVER['REQUEST_METHOD']=='POST') 
{
    require_once('dbConnect.php');

    $usernamee = $_POST['username'];
    $id_product = $_POST['id_product'];

    $sql ="DELETE  FROM  cart   WHERE id_user='$usernamee'  AND id_product = '$id_product' ";

$r = mysqli_query($connection,$sql);
if($r)
  {
    echo "محصول با موفقیت حذف شد";
  }
  else
  {
    echo "مشکلی پیش امده مجدد امتحان کنید";

  }

}
else
{
    echo "no post";
}


?>