<?php
session_start();
error_reporting(0);
extract($_POST);
extract($_SESSION);
extract($_GET);

include("database.php");
$sumbit = ' ';
if($submit=='Finish')
{	
	mysql_query("delete from useranswer where sess_id='" . session_id() ."'") or die(mysql_error());
	unset($_SESSION[qn]);
	header("Location: sublist.php");
	exit;
}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Review</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="quiz.css" rel="stylesheet" type="text/css">
</head>

<body>
<?php
include("header.php");
include("pagetop.php");
echo "<h1 class=head1> Review Test Question</h1>";

if(!isset($_SESSION[qn]))
{
		$_SESSION[qn]=0;
}
else if($submit=='Next Question' )
{
	$_SESSION[qn]=$_SESSION[qn]+1;
	
}


if($questype==0)
{
	mysql_query("update usertest set pretest_given=1 where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
	
	$newrs=mysql_query("SELECT score FROM result ORDER BY test_date DESC LIMIT 1") or die("Could Not Perform the limit Query");
	if(mysql_num_rows($newrs)<1)
	{
		echo "<br><br><h2 class=head2 align=center>Error in fetching new id</h2>";
		exit;
	}
	$newid=mysql_fetch_row($newrs);
	$score = 100.0*$newid[0]/$totalques;
	if($totalques > 0)
	mysql_query("update usertest set pretest_score=$score where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
	$totalques = -1;
	
}
else
{
	mysql_query("update usertest set test_given=1 where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
	$newrs=mysql_query("SELECT score FROM result ORDER BY test_date DESC LIMIT 1") or die("Could Not Perform the limit Query");
	if(mysql_num_rows($newrs)<1)
	{
		echo "<br><br><h2 class=head2 align=center>Error in fetching new id</h2>";
		exit;
	}
	$newid=mysql_fetch_row($newrs);
	$score = 100.0*$newid[0]/$totalques;
	if($totalques > 0)
	mysql_query("update usertest set test_score=$score where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
	$totalques = -1;
}


$rs=mysql_query("select * from useranswer where sess_id='" . session_id() ."'",$cn) or die(mysql_error());
mysql_data_seek($rs,$_SESSION[qn]);
$row= mysql_fetch_row($rs);
echo "<form name=myfm method=post action=review.php>";
echo "<table width=100%> <tr> <td width=30>&nbsp;<td> <table border=0>";
$n=$_SESSION[qn]+1;
echo "<tR><td><span class=style2>Que ".  $n .": $row[2]</style>";
echo "<tr><td class=".($row[7]==1?'tans':'style8').">$row[3]";
echo "<tr><td class=".($row[7]==2?'tans':'style8').">$row[4]";
echo "<tr><td class=".($row[7]==3?'tans':'style8').">$row[5]";
echo "<tr><td class=".($row[7]==4?'tans':'style8').">$row[6]";
if($_SESSION[qn]<mysql_num_rows($rs)-1)
echo "<tr><td><input type=submit name=submit value='Next Question'></form>";
else
echo "<tr><td><input type=submit name=submit value='Finish'></form>";

echo "</table></table>";
include("pagebottom.php");


?>
