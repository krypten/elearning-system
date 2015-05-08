<?php
//ini_set('display_errors','off');	
	function sec_session_start() 
	{
        $session_name = 'f**k_you_chaiti'; // Set a custom session name
        $secure = false; // Set to true if using https.
        $httponly = true; // This stops javascript being able to access the session id. 
 
        ini_set('session.use_only_cookies', 1); // Forces sessions to only use cookies. 
        $cookieParams = session_get_cookie_params(); // Gets current cookies params.
        session_set_cookie_params($cookieParams["lifetime"], $cookieParams["path"], $cookieParams["domain"], $secure); 
        session_name($session_name); // Sets the session name to the one set above.
        session_start(); // Start the php session
        session_regenerate_id(); // regenerated the session, delete the old one.  
	}
	
	function san($var)
	{
		$var = stripslashes($var);
		$var = htmlentities($var);
		$var = strip_tags($var);
		//$var = mysql_real_escape_string($var);
		$var = trim($var);
		$var = htmlspecialchars($var);
		return $var;
	}
	
	function get_client_ip() {
		 $ipaddress = '';
		 if (getenv('HTTP_CLIENT_IP'))
			 $ipaddress = getenv('HTTP_CLIENT_IP');
		 else if(getenv('HTTP_X_FORWARDED_FOR'))
			 $ipaddress = getenv('HTTP_X_FORWARDED_FOR');
		 else if(getenv('HTTP_X_FORWARDED'))
			 $ipaddress = getenv('HTTP_X_FORWARDED');
		 else if(getenv('HTTP_FORWARDED_FOR'))
			 $ipaddress = getenv('HTTP_FORWARDED_FOR');
		 else if(getenv('HTTP_FORWARDED'))
			$ipaddress = getenv('HTTP_FORWARDED');
		 else if(getenv('REMOTE_ADDR'))
			 $ipaddress = getenv('REMOTE_ADDR');
		 else
			 $ipaddress = 'UNKNOWN';

		 return $ipaddress; 
	}
	
	function getNotification()
	{
		$dbh = initDb();
		/*$sth = $dbh->prepare("SELECT content FROM notification WHERE status=\"1\"");
		//$sth->execute();
		//$stmt = $db->query('SELECT * FROM table');
		//$results = $sth->fetchAll(PDO::FETCH_ASSOC);
		var_dump($results);*/
		/*$stmt = $dbh->query("SELECT content FROM notification WHERE status=\"1\"");
 
		while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
			echo '<li>'.$row['content'].'</li>'; //etc...
		}*/
		
	}
	
	function getAddr($var)
	{
		$dbh = initDb();
		$sth = $dbh->prepare("SELECT addr FROM sites WHERE page=\"$var\"");
		$sth->execute();
		//$stmt = $db->query('SELECT * FROM table');
		$results = $sth->fetchAll(PDO::FETCH_NUM);
		$dbh = null;
		
		return $results[0][0];
		
	}
	
	function initDb() {
		$db = new PDO('mysql:host=localhost;dbname=imp;charset=utf8', 'imp', 'CgYuUIXS');
		$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
		return $db;
	}
	function initDb_Attend() {
		$db = new PDO('mysql:host=localhost;dbname=robita;charset=utf8', 'imp', 'CgYuUIXS');
		$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
		return $db;
	}
	
	function getQuote($num)
	{
		switch($num) {
			case 1:
				echo '"The question of whether computers can think is like the question of whether submarines can swim." <a>- Edsger W. Dijkstra</a>';
				break;
			case 2:
				echo '"It can be argued that the computer is humanity’s attempt to replicate the human brain. This is perhaps an unattainable goal. However, unattainable goals often lead to outstanding accomplishment." <a>- Ammaar Shaukat Reshi</a>';
				break;
			default:
				echo 'Powered by <a href="http://www.goodreads.com/">GoodReads</a>';
				break;
		}
	}
function check_login($user, $pwd)
{
		if (!$user || !$pwd) return false;
		unset($_SESSION["show_login_error"]);
		$ds = ldap_connect("ldap.iiita.ac.in");
		ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3); 
		$a = ldap_search($ds, "dc=iiita, dc=ac, dc=in", "uid=$user");
		$b= ldap_get_entries($ds, $a);
		$dn = $b[0]["dn"];
		$x = ldap_bind($ds, $dn, $pwd) or die(set_error());
		if ($x) {
			$_SESSION["uid"] = strtoupper($user);
			//$_SESSION["uid"] = "IRO2012001";
			$_SESSION["uname"] = $b[0]["gecos"][0];
			update_login_record();
			$_SESSION["new_login"] = 1;
			ldap_close($ds);
			return TRUE;
		} else {
			
			return FALSE;	ldap_close($ds);
		}
	
}

function adc_attack() {
	/*Checking for cross-site attacks*/
	$ref = $_SERVER['HTTP_REFERER'];
	$ref = preg_replace("/http:\/\//i", "", $ref);
	$ref = preg_replace("/^www\./i", "", $ref );
	$ref = preg_replace("/\/.*/i", "", $ref );
	$domain = $_SERVER['HTTP_HOST'];
	$referer = $ref ;
	if ($referer == $domain) return true;
	else return false;
}

function getBatch() {
	$id = substr($_SESSION["uid"], 0, 3);
	if ($_SESSION["uid"] == "PAVAN") return "it";
	switch ($id) {
		case "IIT":
			return "it";
		case "IEC":
			return "ec";
		case "IBI":
			return "bi";
		case "IIS":
			return "is";
		case "ISE":
			return "se";
		case "IWC":
			return "wcc";
		case "IRO":
			return "ro";
		case "IHC":
			return "hci";
		case "IMI":
			return "mi";
		case "ICE":
			return "ce";
		case "RIT":
			return "it";
		default:
			return "";
	}
}
function check_exist($column, $value, $db, $table)
	{
		$sql = "SELECT `id` FROM `$table` WHERE `$column` = \"$value\"";
		$stmt = $db->prepare($sql);
		$stmt->execute();
		$row = $stmt->fetch(PDO::FETCH_NUM);
		if ($row) return $row[0];
		else return "";
		
	
	}
	
function check_volunteer()
{
	$dbh = initDb();
	if (check_exist("uid", $_SESSION["uid"], $dbh, "sc_volunteer")) {
		$dbh = null;
		return TRUE;
	} else {
		$dbh = null;
		return FALSE;
	}
}
function set_error() 
{
	$_SESSION["login_error"] = $_SESSION["login_error"] + 1;
	$url = $_SERVER["HTTP_REFERER"];
	header('Location: '.$url);
	exit(0);
}

function update_login_record() 
{
	$_SESSION["show_login_error"] = 0;
	$dbh = initDb();
	$id = $_SESSION["uid"];
	$ip = get_client_ip();
	$sql = "INSERT INTO login VALUES (\"\", \"$id\", \"$ip\", NOW())";
	$stmt = $dbh->prepare($sql);
	$stmt->execute();
	$dbh = null;
	
}
function spamcheck($field)
  {
  //filter_var() sanitizes the e-mail
  //address using FILTER_SANITIZE_EMAIL
  $field=filter_var($field, FILTER_SANITIZE_EMAIL);

  //filter_var() validates the e-mail
  //address using FILTER_VALIDATE_EMAIL
  if(filter_var($field, FILTER_VALIDATE_EMAIL))
    {
    return TRUE;
    }
  else
    {
    return FALSE;
    }
  }
  
function last_login_record()
{
	$dbh = initDb();
	$var = check_exist("uid", $_SESSION["uid"], $dbh, "login");
	if ($var) {
		$sql = 'SELECT  ipaddr, uptime 
FROM  `login` 
WHERE uid =  "'.$_SESSION["uid"].'"
ORDER  BY uptime DESC 
LIMIT 1 , 1';

		$stmt = $dbh->prepare($sql);
		$res = $stmt->execute();
		if ($res) {
			$row = $stmt->fetch(PDO::FETCH_ASSOC);
			if ($row) return 'Hello '.$_SESSION["uid"].' ! You have last logged in from '.$row["ipaddr"].' on '.date('M j Y g:i A', strtotime($row["uptime"])).' ';
			else return 'Hello '.$_SESSION["uid"].' ! Welcome to IMP for the First time :) ';
		} else {
			return 'Hello '.$_SESSION["uid"].' ! Welcome to IMP :) ';
		}
	} else {
		return 'Hello '.$_SESSION["uid"].' ! Welcome to IMP for the First time :) ';
	}
	$dbh = null;
}

function show_login_stats()
{
	$dbh = initDb();
			$date = date('Y-m-d H:i:s', strtotime('-1 day')); //24 hours ago
			$sql = "SELECT COUNT(DISTINCT uid) as no FROM login WHERE uptime > '".$date."'";
			$stmt = $dbh->prepare($sql);
			$stmt->execute();
			$row_1 = $stmt->fetchAll(PDO::FETCH_ASSOC);
			//var_dump($row_1);
			$date = date('Y-m-d H:i:s', strtotime('-7 day')); //24 hours ago
			$sql = "SELECT COUNT(DISTINCT uid) as no FROM login WHERE uptime > '".$date."'";
			$stmt = $dbh->prepare($sql);
			$stmt->execute();
			$row_7 = $stmt->fetchAll(PDO::FETCH_ASSOC);
			$date = date('Y-m-d H:i:s', strtotime('-30 day')); //24 hours ago
			$sql = "SELECT COUNT(DISTINCT uid) as no FROM login WHERE uptime > '".$date."'";
			$stmt = $dbh->prepare($sql);
			$stmt->execute();
			$row_30 = $stmt->fetchAll(PDO::FETCH_ASSOC);
			$dbh = null;
			echo '
			<div class="imp_box">
            	<h2><span></span>Activities @ IMP</h2>
                
                <div class="body" >
                    <p style="text-align:justify;color:#c85d16;font-weight:bold;">No. of Users in past 24 hrs: '.$row_1[0]["no"].'</p>
					<p style="text-align:justify;">No. of Users in past 7 days: '.$row_7[0]["no"].'</p>
					<p style="text-align:justify;">No. of Users in past 1 month: '.$row_30[0]["no"].'</p>
                </div>
            	<div class="box_bottom"><span></span></div>
            </div>
			
			';
}

function toggle_button($var)
{
	if ($var != -1) {
		echo '
				<div class="onoffswitch">
					<input type="checkbox" name="'.$var.'" class="onoffswitch-checkbox" id="myonoffswitch'.$var.'" checked>
					<label class="onoffswitch-label" for="myonoffswitch'.$var.'">
					<div class="onoffswitch-inner"></div><div class="onoffswitch-switch"></div>
					</label>
				</div>
	';
	} else {
		echo '
				<div class="disableswitch">
					<input type="checkbox" name="'.$var.'" class="disableswitch-checkbox" id="mydisableswitch'.$var.'" disabled>
					<label class="disableswitch-label" for="mydisableswitch'.$var.'">
					<div class="disableswitch-inner"></div><div class="disableswitch-switch"></div>
					</label>
				</div>
	';
	}
}

	function is_faculty($uid)
	{
		$ds = ldap_connect("ldap.iiita.ac.in");
		ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3); 
		$a = ldap_search($ds, "dc=iiita,dc=ac,dc=in","(&(uid=$uid)(objectClass=posixAccount))");
		$b= ldap_get_entries($ds, $a);
		$dn = $b[0]["dn"];
		$res = FALSE;
		if (strpos($dn,'Faculty') !== false) {
			$res = TRUE;
		} else if (strpos($dn,'faculty') !== false) {
			$res = TRUE;
		}
		ldap_close($ds);
		return $res;
	}
	
	function is_employee($uid)
	{
		$ds = ldap_connect("ldap.iiita.ac.in");
		ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3); 
		$a = ldap_search($ds, "dc=iiita,dc=ac,dc=in","(&(uid=$uid)(objectClass=posixAccount))");
		$b= ldap_get_entries($ds, $a);
		$dn = $b[0]["dn"];
		$res = FALSE;
		if (strpos($dn,'Employee') !== false) {
			$res = TRUE;
		} else if (strpos($dn,'employee') !== false) {
			$res = TRUE;
		}
		ldap_close($ds);
		return $res;
	}
	
	function is_student($uid)
	{
		//$uid="RS110";
		$ds = ldap_connect("ldap.iiita.ac.in");
		ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3); 
		$a = ldap_search($ds, "dc=iiita,dc=ac,dc=in","(&(uid=$uid)(objectClass=posixAccount))");
		$b= ldap_get_entries($ds, $a);
		$dn = $b[0]["dn"];
		$res = FALSE;
		if (strpos($dn,'student') !== false) {
			$res = TRUE;
		} else if (strpos($dn,'Student') !== false) {
			$res = TRUE;
		} else if (strpos($dn, 'rgiit') !== false) {
			$res = TRUE;
		}
		ldap_close($ds);
		return $res;
	}
	
	function show_faculty()
	{
		$ds = ldap_connect("ldap.iiita.ac.in");  // must be a valid LDAP server!
		ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3); 
		$bd = ldap_bind($ds);
		$ou = "ou=Faculty,ou=Employee,dc=iiita,dc=ac,dc=in";
		$array = array();
		$search = ldap_search($ds, "$ou", "(&(uid=*)(objectclass=posixAccount))", $array);
		//$search = ldap_search($ds, "ou=rgiit,dc=iiita,dc=ac,dc=in", "(&(uid=*)(objectclass=posixAccount))");
		$str = "";
		ldap_sort($ds, $search, $str);
		$entries = ldap_get_entries($ds, $search);
		$count = count($entries);
		$result = array();
		$array=array();
		for ($i = 0; $i < $count; $i++) {
			if (! in_array($entries[$i]["gecos"][0], $array)) {
				$array[$i] = $entries[$i]["gecos"][0]; //Just to avoid redundancy
				$result[$i] = array($entries[$i]["uid"][0] => $entries[$i]["gecos"][0]);
				
			}
		}
		//var_dump($result);
		ldap_close($ds);
		return $result;
	}
	
	function get_uid_name($user) {
		$ds = ldap_connect("ldap.iiita.ac.in");
		ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3); 
		$a = ldap_search($ds, "dc=iiita, dc=ac, dc=in", "uid=$user");
		$b= ldap_get_entries($ds, $a);
		ldap_close($ds);
		return $b[0]["gecos"][0];
	}

?>