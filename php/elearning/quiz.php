<?php
session_start(); // Start the php session
error_reporting(1);
include("database.php");
extract($_POST);
extract($_GET);
extract($_SESSION);
/*$rs=mysql_query("select * from question where test_id=$tid",$cn) or die(mysql_error());
if($_SESSION[qn]>mysql_num_rows($rs))
{
unset($_SESSION[qn]);
exit;
}*/
if(isset($subid) && isset($testid))
{
$_SESSION[sid]=$subid;
$_SESSION[tid]=$testid;
$_SESSION[qid]=$questype;
header("location:quiz.php");
}
if(!isset($_SESSION[sid]) || !isset($_SESSION[tid]))
{
	header("location: index.php");
}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Online Quiz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="quiz.css" rel="stylesheet" type="text/css">
</head>

<body>
	
<?php
include("header.php");
include("pagetop.php");

$url = "quiz.php?submit=Get+Result";
$timeout = 40; // quiz timeout

//unset pretest time to 0 for main test
if(!isset($_SESSION[qn]))
{
	mysql_query("update usertest set pretest_time=0 where test_id=$tid and login='$_SESSION[login]'") or die(mysql_error());
}
$rs=mysql_query("select UNIX_TIMESTAMP(pretest_time) from usertest where test_id=$tid and login='$_SESSION[login]'",$cn) or die(mysql_error());
$row= mysql_fetch_row($rs);

if($row[0] == 0)
{
	$time = time() + $timeout;
	$mysqldate = date( 'Y-m-d H:i:s', $time );
	$time = strtotime( $mysqldate );
	echo $time1;
	mysql_query("update usertest set pretest_time=FROM_UNIXTIME($time) where test_id=$tid and login='$_SESSION[login]'") or die(mysql_error());
}
else
{
	$timeout = $row[0] - time();
}
?>

<?php

if(!isset($submit) || $submit != 'Get Result'){

echo '<meta http-equiv="refresh" content="'.$timeout .';url='.$url.'">

	<h3 style="text-align: right;"><a href="' . $url .'"></a> 
	<div id = "timer"></div>
	<!--<form name="counter" ><input type="text" name="d2" size="5" disabled></form>-->
	</h3>
<script>
 var seconds='.$timeout.'
function display(){ 
    document.getElementById("timer").innerHTML="Time:" + seconds
	seconds-=1
    setTimeout("display()",1000) 
} 

display() 
</script> 

<script type="text/javascript">
  setTimeout(function(){window.location.replace("<?php echo($url); ?>";}, <?php echo($timeout * 1000); ?>);
</script>';

}
?>
<?php
$rs=mysql_query("select  * from question where test_id=$tid and ques_type=$_SESSION[qid]",$cn) or die(mysql_error());

$totalques = 0;
while($row=mysql_fetch_row($rs))
{
	$totalques++;
}
$_SESSION[totalques] = $totalques;

if(!isset($_SESSION[qn]))
{
	$_SESSION[qn]=0;
	mysql_query("delete from useranswer where sess_id='" . session_id() ."'") or die(mysql_error());
	$_SESSION[trueans]=0;
	
}
else
{	
		if($submit=='Next Question' && isset($ans))
		{
				mysql_data_seek($rs,$_SESSION[qn]);
				$row= mysql_fetch_row($rs);	
				mysql_query("insert into useranswer(sess_id, test_id, que_des, ans1,ans2,ans3,ans4,true_ans,your_ans) values ('".session_id()."', $tid,'$row[2]','$row[3]','$row[4]','$row[5]', '$row[6]','$row[7]','$ans')") or die(mysql_error());
				if($ans==$row[7])
				{
							$_SESSION[trueans]=$_SESSION[trueans]+1;
				}
				$_SESSION[qn]=$_SESSION[qn]+1;
		}
		else if($submit=='Get Result')
		{
				if (isset($ans))
				{
					mysql_data_seek($rs,$_SESSION[qn]);
					$row= mysql_fetch_row($rs);	
					mysql_query("insert into useranswer(sess_id, test_id, que_des, ans1,ans2,ans3,ans4,true_ans,your_ans) values ('".session_id()."', $tid,'$row[2]','$row[3]','$row[4]','$row[5]', '$row[6]','$row[7]','$ans')") or die(mysql_error());
					if($ans==$row[7])
					{
								$_SESSION[trueans]=$_SESSION[trueans]+1;
					}
					$_SESSION[qn]=$_SESSION[qn]+1;
				}
				echo "<h1 class=msg>Result</h1>";
				echo "<Table align=center><tr class=tot><td>Total Questions : <td>".$totalques;
				echo "<tr class=tans><td><font color='green'>Correct Answers : </font><td>".$_SESSION[trueans];
				$w=$totalques-$_SESSION[trueans];
				echo "<tr class=fans><td><font color='red'>Wrong Answers : </font><td> ". $w;
				echo "</table>";
				
				echo "<h1 align=center><a href=review.php?testid=".$_SESSION[tid]."&"."subid=".$_SESSION[sid]."&questype=".$_SESSION[qid]."&totalques=".$totalques."&trueans=".$_SESSION[trueans]."&flag=0> Review Question</a> </h1>";
				unset($_SESSION[qn]);
				
				/*
				unset($_SESSION[sid]);
				unset($_SESSION[tid]);
				unset($_SESSION[trueans]);
				*/
				exit;
		}
}
$rs=mysql_query("select * from question where test_id=$tid and ques_type=$_SESSION[qid]",$cn) or die(mysql_error());
if($_SESSION[qn]>mysql_num_rows($rs)-1)
{
unset($_SESSION[qn]);
echo "<h1 class=head1>Some Error  Occured</h1>";
//session_destroy();
echo "Please <a href=index.php> Start Again</a>";

exit;
}
mysql_data_seek($rs,$_SESSION[qn]);
$row= mysql_fetch_row($rs);
echo "<form name=myfm method=get action=quiz.php>";
echo "<table class=box width=80%> <tr> <td width=30>&nbsp;<td> <table border=0>";
$n=$_SESSION[qn]+1;
echo "<tR><td><span class=style2>Question ".  $n .": $row[2]</style>";
echo "<tr><td class=style8><input type=radio name=ans value=1>$row[3]";
echo "<tr><td class=style8> <input type=radio name=ans value=2>$row[4]";
if($row[5] != null)
echo "<tr><td class=style8><input type=radio name=ans value=3>$row[5]";
if($row[5] != null)
echo "<tr><td class=style8><input type=radio name=ans value=4>$row[6]";
if($_SESSION[qn]<mysql_num_rows($rs)-1)
echo "<tr><td><input type=submit name=submit value='Next Question'></form>";
else
echo "<tr><td><input type=submit name=submit value='Get Result'></form>";
echo "</table></table>";

include("pagebottom.php");
?>
</body>
</html>