<?php
session_start();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Courses</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="quiz.css" rel="stylesheet" type="text/css">
</head>
<body>
<?php
include("header.php");
include("database.php");
include("pagetop.php");
		
		echo '<div class="head1">Courses Available</div>';
		$rs=mysql_query("select * from subject");
		echo '<table align="center" id="list" cellpadding="20px" >';
		while($row=mysql_fetch_row($rs))
		{
			echo "<tr><td align=center ><a href=showtest.php?subid=$row[0]><font size=5>$row[1]</font></a>";
		}
		echo "</table>";	
		
include("pagebottom.php");

?>
</body>
</html>
