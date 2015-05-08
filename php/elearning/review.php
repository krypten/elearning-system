<?php
session_start(); // Start the php session
error_reporting(0);
extract($_POST);
extract($_SESSION);
extract($_GET);

include("database.php");

if($submit=='Finish')
{	
	mysql_query("delete from useranswer where sess_id='" . session_id() ."'") or die(mysql_error());
	unset($_SESSION[qn]);
	unset($_SESSION[qid]);
	unset($_SESSION[totalques]);
	header("Location: showtest.php?subid=".$subid);
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
//	$trueans = $_SESSION[trueans];
/*
	if($flag == 1)
	{
		echo "<h1 class=msg>Result</h1>";
		echo "<Table align=center><tr class=tot><td>Total Questions : <td>".$_SESSION[totalques];
		echo "<tr class=tans><td><font color='green'>Correct Answers : </font><td>".$_SESSION[trueans];
		$w=$_SESSION[totalques]-$_SESSION[trueans];
		echo "<tr class=fans><td><font color='red'>Wrong Answers : </font><td> ". $w;
		echo "</table>";

		unset($_SESSION[qn]);
		unset($_SESSION[sid]);
		$testid = $_SESSION[tid];
		unset($_SESSION[tid]);
		$trueans = $_SESSION[trueans];
		unset($_SESSION[trueans]);
		$flag = 0;
	}
*/
	echo "<h1 class=msg> Review Test Questions</h1>";

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
		$percent = 100.0*$trueans/$totalques;
		
		if($totalques > 0)
			mysql_query("update usertest set pretest_score=$percent where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
		$totalques = -1;

	}
	else
	{
		mysql_query("update usertest set test_given=1 where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
		$percent = 100.0*$trueans/$totalques;
		if($totalques > 0)
			mysql_query("update usertest set test_score=$percent where test_id='$testid' and login='$_SESSION[login]'") or die(mysql_error());
		$totalques = -1;
	}

// bridge code

	function getScore($percent) {
		if ($percent < 30) return -1;
		else if ($percent < 60) return 0;
		else if ($percent < 80) return 1;
		else return 2;
	}

	function getModuleName($testid, $cn) {
		$rs  = mysql_query("select test_name from test where test_id=". $testid, $cn) or die(mysql_error());	
		$row = mysql_fetch_row($rs);
		return $row[0];
	}

	function getTestID($module_name, $cn) {
		$rs  = mysql_query("select test_id from test where test_name like '%". $module_name . "%'", $cn) or die(mysql_error());	
		$row = mysql_fetch_row($rs);
		return $row[0];
	}

	function allowNewTest($oldTestId, $nextTestId, $login, $cn)
	{
		mysql_query("insert into usertest (login, test_id, available, pretest_given) values ('". $login ."',".$nextTestId .",1,1);",$cn) or die(mysql_error());
		mysql_query("update usertest set available=0 where login='". $login  ."' and test_id=" .$oldTestId. ";" ,$cn) or die(mysql_error());
	}

	if ($_SESSION[qn] == 0)
	{
		echo '<br/> Review <br/>';
		debug('Percentage : ', $percent);
		$score = getScore($percent);
		debug('Observation : ', $score);
		if($score >= 0)
		{
			$system = getSystem('system2');
			if($questype == 0)
			{
				$system->engine->init($score);
				debug('State Recommeded : ', $score);
				setSystem("module_state", $score);

				debug('Title : ', 'Introduction');
				setSystem("module_title", 'Introduction'); // TBD: change to moduleName
			}
			else
			{
				// debug('','');
				$state = getState($system->engine, $score, 7);
				debug('State Recommeded : ', $state);
				$module_id = $_SESSION['module_id'];
				// debug('Module Id : ', $module_id);
				$modules = getModules($system->engine, $_SESSION['domain_name'], 7);
				// var_dump($modules);
				// debug('Old Test ID : ', $testid);
				$nextTestId = getTestID($modules[$module_id + 1], $cn);
				// debug('Next Test ID : ', $nextTestId);
				
				allowNewTest($testid, $nextTestId, $_SESSION['login'], $cn);
				$_SESSION['module_id'] = $module_id + 1;
				$moduleName = getModuleName($nextTestId, $cn); // TBD : redundant
				setSystem("module_state", $state);
				
				$title = showLearningObject($system->engine, $moduleName, $state);
				debug('Title : ', $title);
				setSystem("module_title", $title); //  change to moduleName
			}
			setSystem('system2', $system);
		}
		else
		{
			//redirect
		}
	}
	
	$rs=mysql_query("select * from useranswer where sess_id='" . session_id() ."'",$cn) or die(mysql_error());
	// var_dump($rs);
	mysql_data_seek($rs,$_SESSION[qn]);
	// debug('Review Question Num: ', $_SESSION[qn] + 1);
	$row= mysql_fetch_row($rs);
	// var_dump($row);

	if($row[2] != null){
		echo '<br/>';
		echo "<form name=myfm method=get action=review.php>";
		echo "<table class=box align=center width=90%> <tr> <td width=30>&nbsp;<td> <table border=0>";
		$n=$_SESSION[qn]+1;
		echo "<tR><td><span class=style2>Question ".  $n .": $row[2]</style>";
		echo "<tr><td class=".($row[7]==1?'tans':'style8').">$row[3]";
		echo "<tr><td class=".($row[7]==2?'tans':'style8').">$row[4]";
		echo "<tr><td class=".($row[7]==3?'tans':'style8').">$row[5]";
		echo "<tr><td class=".($row[7]==4?'tans':'style8').">$row[6]";

		echo "<tr><td><input type=hidden name=subid value=". $subid . ">";
		if($_SESSION[qn]<mysql_num_rows($rs)-1)
		{
			echo "<tr><td><input type=submit name=submit value='Next Question'></form>";
		}
		echo "<tr><td><input type=submit name=submit value='Finish'></form>";
		echo "</table></table>";
	}
	
	// debug('','');
	include("pagebottom.php");

	?>
