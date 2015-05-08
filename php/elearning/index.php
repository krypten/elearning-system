<?php
	session_start();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>IIITA</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="quiz.css" rel="stylesheet" type="text/css">
</head>

<body>
	<?php
	include("header.php");
	include("database.php");
	extract($_POST);

	if(isset($submit))
	{
		$rs=mysql_query("select * from user where login='$loginid' and pass='$pass'");
		if(mysql_num_rows($rs)<1)
		{
			$found="N";
		}
		else
		{
			$_SESSION[login]=$loginid;
		}
	}
	if (isset($_SESSION[login]))
	{
		// Java Bridge code
		if (!isset($_SESSION['init_domain'])) 
		{
			echo '<br/>Bridge initialising <br/>';
			$system = createBridge();
			setSystem("system2", $system);
			echo '<br/>Bridge ended <br/>';
			$system = getSystem('system2');

			$_SESSION['init_domain'] = true; 
		}

		header ("Location: sublist.php");		/*
		echo "<div class='header' align='right'><strong><a href='index.php'> Home </a>| <a href='signout.php'>Signout</a></strong>";
		echo "<h1 align='center'>Adaptive E-learning System</h1></div>";
		echo 
		'<div class="page"><table border="0" height="100%" width="100%">
		<tr valign="top">
		<td class="sidebar" border="1px" width="15%">
		<h3><a href="sublist.php" class="style4">Courses</a></h3>
		</td>
		<td class="content">';

		echo "<h2 class='head1'> Courses Available</h2>";
		$rs=mysql_query("select * from subject");
		echo "<table align='center' cellpadding='20px' >";
		while($row=mysql_fetch_row($rs))
		{
			echo "<tr><td align=center ><a href=showtest.php?subid=$row[0]><font size=4>$row[1]</font></a>";
		}
		echo "</table>";	

		echo '</td></tr></table></div>';
		exit;
		*/
	}


	?>
	<table id="loginframe">
		<tr>
			<td colspan="2"><div align="center" class="style1"><h2>User Login</h2></div></td>
		</tr>
		<tr>
			<td >
				<div align="center">
				<h1>Adaptive E-learning System</h1>
				<blockquote>
					<h3 align="center" class="style5">
						<span class="style7">
							<i>An Ontology Based Personalized E-Learning System</i>
						</span>
					</h3>
				</blockquote>
				</div>
			</td>
			<td valign="top"><form name="form1" method="post" action="">
				<table align="center">
					<tr>
						<td><span class="style2">User ID</span></td>
						<td><input name="loginid" type="text" id="loginid2"></td>
					</tr>
					<tr>
						<td><span class="style2">Password</span></td>
						<td><input name="pass" type="password" id="pass2"></td>
					</tr>
					<tr>
						<td colspan="2"><span class="errors">
							<?php
							if(isset($found))
							{
								echo "Invalid Username or Password";
							}
							?>
						</span></td>
					</tr>
					<tr>
						<td colspan=2 align=center class="errors">
							<input name="submit" type="submit" id="submit" value="Login"></td>
							<br>
						</tr>
						<tr>
							<td><div align="center"><h3><a href="signup.php">Register</a></h3></div></td>
							<td align="right"><h3><a href="admin/index.php">Admin Login</a><h3></td>
						</tr>
					</table>
				</form></td>
			</tr>
		</table>

	</body>
	</html>
