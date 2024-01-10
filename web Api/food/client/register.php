<?php
if(isset($_POST['username'])&& !empty($_POST['username']) && 
isset($_POST['display_name'])&& !empty($_POST['display_name'])
  && isset($_POST['phone'])&& !empty($_POST['phone']) && 
  isset($_POST['password'])&& !empty($_POST['password']))
{
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $userName = $_POST['username'];
        $display_name = $_POST['display_name'];
        $phone = $_POST['phone'];
        $password = $_POST['password'];

        require_once('dbConnect.php');

        $sql_insertUser = "INSERT INTO user(user_name,display_name,phone,password,adress) 
		VALUES ('$userName','$display_name','$phone','$password','null')";

        if (mysqli_query($connection, $sql_insertUser))
        {
            echo("user is added");
        }
        else
        {
            echo("falid");
        }


    } 
    else {
        echo 'error';
    }
}
else
{
    echo "empity";
}
?>