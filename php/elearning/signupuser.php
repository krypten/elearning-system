<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Signup</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="quiz.css" rel="stylesheet" type="text/css">
</head>

<body>
<?php
include("header.php");
extract($_POST);
include("database.php");
$rs=mysql_query("select * from user where login='$lid'");
if (mysql_num_rows($rs)>0)
{
	echo '<br><br><br><div class=head1 id="signup">Login Id Already Exists</div>';
	exit;
}
$query="insert into user(user_id,login,pass,username,email) values('$uid','$lid','$pass','$name','$email')";
$rs=mysql_query($query)or die("Could Not Perform the Query");
/*
$newrs=mysql_query("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1") or die("Could Not Perform the limit Query");
if(mysql_num_rows($newrs)<1)
{
	echo "<br><br><h2 class=head2 align=center>Error in fetching new id</h2>";
	exit;
}
$newid=mysql_fetch_row($newrs);
*/
//initializing tests
$query="insert into usertest(login,test_id,available) values('$lid',13,1)";
$rs=mysql_query($query)or die("Could Not Perform the Query");
$query="insert into usertest(login,test_id,available) values('$lid',14,1)";
$rs=mysql_query($query)or die("Could Not Perform the Query");

echo "<br><br><br><div class=msg>Account for User : <b><font color='black'>$lid</font></b> created Sucessfully</div>";
echo "<br><div class=msg>Please Login <a href=index.php>Here</a></div>";
?>
</body>
</html>

