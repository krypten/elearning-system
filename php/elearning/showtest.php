<?php
session_start();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Test</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="quiz.css" rel="stylesheet" type="text/css">
</head>
<body>
	<?php
	include("header.php");
	include("database.php");
	include("pagetop.php");

	extract($_GET);
	extract($_SESSION);
	$rs1=mysql_query("select * from subject where sub_id=$subid");
	$row1=mysql_fetch_array($rs1);

// bridge code
	if (!isset($_SESSION['init_module'])) 
	{
		$system = getSystem("system2");
		startModule($system, 7);
		$_SESSION['module_id'] =  0;
		$_SESSION['domain_name'] = $row1[1];
		setSystem('system2', $system);

		$_SESSION['init_module'] = true;
	}

	echo "<h1 align=center><font color=blue> $row1[1]</font></h1>";
	$rs=mysql_query("select * from test where sub_id=$subid");
	if(mysql_num_rows($rs)<1)
	{
		echo "<br><br><h2 class=head2 align=center>Sorry!! Course not Available</h2>";
		exit;
	}
	echo "<table class='box' align=center width='90%'><tr><td id='det' width='60%'>";
	echo $row1[2]."<br>";
	echo "</td><td><table align=center id='pane'>";


	while($row=mysql_fetch_row($rs))
	{
		$rs2=mysql_query("select * from usertest where test_id=$row[0] and login='$_SESSION[login]'");

		if(mysql_num_rows($rs2)<1)
		{
			//echo "<br><br><h2 class=head2 align=center>Sorry!! Could not perform usertest query</h2>";
			//exit;
		}
		$row2=mysql_fetch_row($rs2);

		if($row2[3]==1)
			echo "<tr><td class='box' align='center'><a id='selected' href=showtest2.php?testi=$row[0]&subi=$subid&details=".rawurlencode($row[4]).">$row[2]</a>";
		else
			echo "<tr><td class='box' align='center'><font size=3>$row[2]</font>";
	}
	echo "</table></td></tr></table>";
	include("pagebottom.php");
	?>
</body>
</html>
