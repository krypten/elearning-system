<?php
session_start();
require("../database.php");
include("header.php");
error_reporting(1);
?>
<link href="../quiz.css" rel="stylesheet" type="text/css">
<?php
extract($_POST);

echo "<BR>";
if (!isset($_SESSION[alogin]))
{
	echo "<br><h2><div  class=head1>You are not Logged On Please Login to Access this Page</div></h2>";
	echo "<a href=index.php><h3 align=center>Click Here for Login</h3></a>";
	exit();
}
echo "<BR><h3 class=head1>Add Question </h3>";
if($_POST[submit]=='Save' || strlen($_POST['testid'])>0 )
{
extract($_POST);
mysql_query("insert into question (test_id,que_desc,ans1,ans2,ans3,ans4,true_ans,ques_type) values ('$testid','$addque','$ans1','$ans2','$ans3','$ans4','$anstrue','$questype')",$cn) or die(mysql_error());
echo "<p align=center><i><font color='blue'>Question Added Successfully.</font><i></p>";
unset($_POST);
}
?>
<SCRIPT LANGUAGE="JavaScript">
function check() {
mt=document.form1.addque.value;
if (mt.length<1) {
alert("Please Enter Question");
document.form1.addque.focus();
return false;
}
a1=document.form1.ans1.value;
if(a1.length<1) {
alert("Please Enter Answer1");
document.form1.ans1.focus();
return false;
}
a2=document.form1.ans2.value;
if(a1.length<1) {
alert("Please Enter Answer2");
document.form1.ans2.focus();
return false;
}
a3=document.form1.ans3.value;
if(a3.length<1) {
alert("Please Enter Answer3");
document.form1.ans3.focus();
return false;
}
a4=document.form1.ans4.value;
if(a4.length<1) {
alert("Please Enter Answer4");
document.form1.ans4.focus();
return false;
}
at=document.form1.anstrue.value;
if(at.length<1) {
alert("Please Enter True Answer");
document.form1.anstrue.focus();
return false;
}
qt=document.form1.questype.value;
if(qt.length<1) {
alert("Please Enter Question Type");
document.form1.questype.focus();
return false;
}
return true;
}
</script>

<div style="margin:auto;width:90%;height:500px;box-shadow:2px 1px 2px 2px #CCCCCC;text-align:left">
<form name="form1" method="post" onSubmit="return check();">
  <table width="80%"  align="center">
    <tr>
      <td width="24%" height="32"><div align="left"><strong>Test Name</strong></div></td>
      <td width="1%" height="5">  
      <td width="75%" height="32">
	  
	  <select name="testid" id="testid">
<?php
$rs=mysql_query("Select * from test order by test_name",$cn);
	  while($row=mysql_fetch_array($rs))
{
if($row[0]==$testid)
{
echo "<option value='$row[0]' selected>$row[2]</option>";
}
else
{
echo "<option value='$row[0]'>$row[2]</option>";
}
}
?>
      </select>
	  </tr>
	  <tr>
	  <td width="24%" height="32"><div align="left"><strong>Question Type</strong></div></td>
      <td width="1%" height="5">  
      <td width="75%" height="32">
	  <select name="questype" id="questype">
			<option value='0' selected>pretest</option>
			<option value='1'>beginner</option>
			<option value='2'>intermediate</option>
			<option value='3'>advanced</option>
      </select>
      </tr>
	<!--
	<tr>
		<td height="26"><strong>Question Type</strong></td>
		<td>&nbsp;</td>
		<td><input name="questype" type="text" id="questype" size="58" maxlength="58"></td>
		<td><font color='red'>0:pretest&nbsp;&nbsp;1:beginner&nbsp;&nbsp;2:intermediate&nbsp;&nbsp;3:advanced</font></td>
    </tr>
	-->
    <tr>
        <td height="26"><div align="left"><strong> Question </strong></div></td>
        <td>&nbsp;</td>
	    <td><textarea name="addque" cols="60" rows="2" id="addque"></textarea></td>
    </tr>
    <tr>
      <td height="26"><div align="left"><strong>Option 1 </strong></div></td>
      <td>&nbsp;</td>
      <td><input name="ans1" type="text" id="ans1" size="58" maxlength="58"></td>
    </tr>
    <tr>
      <td height="26"><strong>Option 2 </strong></td>
      <td>&nbsp;</td>
      <td><input name="ans2" type="text" id="ans2" size="58" maxlength="58"></td>
    </tr>
    <tr>
      <td height="26"><strong>Option 3 </strong></td>
      <td>&nbsp;</td>
      <td><input name="ans3" type="text" id="ans3" size="58" maxlength="58"></td>
    </tr>
    <tr>
      <td height="26"><strong>Option 4</strong></td>
      <td>&nbsp;</td>
      <td><input name="ans4" type="text" id="ans4" size="58" maxlength="58"></td>
    </tr>
    <tr>
      <td height="26"><strong>Correct Option No. </strong></td>
      <td>&nbsp;</td>
      <td><input name="anstrue" type="text" id="anstrue" size="1" maxlength="1"></td>
    </tr>
    <tr>
      <td height="26"></td>
      <td>&nbsp;</td>
      <td><input type="submit" name="submit" value="Add" ></td>
    </tr>
  </table>
</form>
<p>&nbsp; </p>
</div>