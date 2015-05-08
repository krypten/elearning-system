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
$rs1=mysql_query("select * from subject where sub_id=$subi");
$row1=mysql_fetch_array($rs1);
echo "<h1 align=center><font color=blue> $row1[1]</font></h1>";
$rs=mysql_query("select * from test where sub_id=$subi and test_id=$testi");
if(mysql_num_rows($rs)<1)
{
	echo "<br><br><h2 class=head1>Sorry!! No tests Available</h2>";
	exit;
}
$arr=mysql_fetch_array($rs);
echo "<table align=center width='90%' cellpadding=10px><tr><td width='70%' align=center>";
echo $details."";

/*

PDF DIRECTORY

$dir = "pdf/sample/";

// Open a directory, and read its contents
if (is_dir($dir)){
  if ($dh = opendir($dir)){
    while (($file = readdir($dh)) !== false){
		if($file!='.' && $file!='..')
      //echo "filename:" . $file.";
	  echo "<a href='".$dir."'><font size=5 color=red>PDF</font></a><br>";
    }
    closedir($dh);
  }
}
*/

	$rs2=mysql_query("select * from usertest where test_id=$testi and login='$_SESSION[login]'");
	if(mysql_num_rows($rs2)<1)
	{
		//echo "<br><br><h2 class=head2 align=center>Sorry!! Could not perform usertest query</h2>";
		//exit;
	}
	$row2=mysql_fetch_row($rs2);



if($row2[4] == 0) // pretest
{
	echo "<a href=quiz.php?testid=$arr[0]&subid=$subi&questype=0><font color='blue'><b>Start PreTest</b></font></a><hr>";
}
else // module test
{
	// from bridge
	$state = java_cast(getSystem('module_state'),"integer");
	$questype = $state + 1;
	debug('Type : ', $questype);

	$title = getSystem('module_title');
	
	echo "<a href=quiz.php?testid=$arr[0]&subid=$subi&questype=". $questype ."><font color='blue'><b>Take Test</b></font></a><hr>";
	//pdf show
	$rs2=mysql_query("select sub_name from subject where sub_id=$subi");
	$row2=mysql_fetch_array($rs2);
	$subject = $row2[0];
	
	$rs2=mysql_query("select test_name from test where sub_id=$subi and test_id=$testi");
	$row2=mysql_fetch_array($rs2);
	$module = $row2[0];
	
echo "<object data='pdf/".$subject."/"."$module"."/".$title.".pdf"."' type=application/pdf width=90% align=center height=100%>";
}
echo "</td><td><table id=pane align=center cellpadding='10px'>";

$rs=mysql_query("select * from test where sub_id=$subi");
while($row=mysql_fetch_row($rs))
{
	$rs2=mysql_query("select * from usertest where test_id=$testi and login='$_SESSION[login]'");
	
	if(mysql_num_rows($rs2)<1)
	{
		//echo "<br><br><h2 class=head2 align=center>Sorry!! Could not perform usertest query</h2>";
		//exit;
	}
	$row2=mysql_fetch_row($rs2);
	
	if($row[0]==$testi && $row2[3]==1)
	echo "<tr><td class=box align=left><a id=selected href=showtest2.php?testi=$testi&subi=$subi&details=".rawurlencode($row[4]).">$row[2]</a>";
	else
	echo "<tr><td class=box align=left><font size=3>$row[2]</font>";
}
echo "</table></td></tr></table>";
//echo '<iframe src="pdf/".$subject."/".$module."/".$title.".pdf" style="border:0px #FFFFFF none;" name="myiFrame" scrolling="yes" frameborder="0" marginheight="0px" marginwidth="0px" height="100%" width="100%"></iframe></td>
	//</tr></table>';
include("pagebottom.php");
?>
</body>
</html>
