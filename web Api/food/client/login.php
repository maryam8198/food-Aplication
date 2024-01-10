<?php
if(isset($_POST['username'])&& !empty($_POST['username'])
    && isset($_POST['password'])&& !empty($_POST['password']))
{
    if ($_SERVER['REQUEST_METHOD'] == 'POST')
    {
        $Username = $_POST['username'];
        $Password = $_POST['password'];
        require_once('dbConnect.php');

        $sql = "SELECT * FROM user WHERE user_name='$Username' and  password='$Password'";
        $r = mysqli_query($connection, $sql);
        $num_rows = mysqli_num_rows($r);
        if ($num_rows > 0)
        {
            echo("ok");
        }
        else if ($num_rows < 0)
        {
            echo("not found");
        }
    }
    else
    {
        echo "error";
    }
}
else
{
    echo "empity";
}
 ?>