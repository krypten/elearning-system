#ReadMe

    ## Install and Setup in Project
    	1. Setup basic eclipse, database and php environment.
        2. Integrate Apache Jena in Eclipse.
        3. Integrate php-java-bridge in Eclipse.
        4. Copy the php code in /var/www/html/elearning or htdocs in xampp.
        5. Running the project
            5.1. Open Eclipse and run the Java code with Apache Tomcat Server on port 8086.
            5.2. Open http://localhost/elearning/ in browser.

    ## Setup eclipse environment
        1. Download and extract Eclipse Luna from eclipse.org/downloads.
        2. Install Eclipse in your platform.
        3. Install Web plugin
            3.1 Go to Help/Install new Software
            3.2 choose "All Available sites" and search for "server"
            3.3 You will see "Web, Xml, Java EE and OSGi Enterprise Development", Install this Software.

    ## Integration of Apache Jena in Eclipse.
    	1. Download and install eclipse.
		2. Download and unzip Jena from jena.apache.org/downloads/index.cgi
		3. Add the Jena libraries in the project class/ 
			3.1 Goto Window > Preferences
			3.2 Open Java > Build Path > User Libraries
			3.3 Create a new User Library and name it "JenaLibs"
			3.4 Add Jars from the Jena extracted folder.
		4. Add the user library "JenaLibs" to the project.
		5. Add the desired code to work/extract/read/write the rdf data.
		6. Run the java application.

    ## Integration of php/Java Bridge and Eclipse.
        1. Download and install eclipse.
        2. Download JavaBridge.jar and php-script.jar .
        4. Add JavaBridge.jar and php-script.jar in the project.
			4.1 Goto Window > Preferences
			4.2 Open Java > Build Path
			4.4 Add External Jars from the extracted folder
        5. Add Java.inc to elearning php directory and also change the port number in the file to port 8086.
        5. Copy JavaBridge.jar and php-servlet.jar to the /usr/share/java/ directory for ubuntu.

    ## Integration of Memcached in Ubuntu
        1. sudo apt-get install php5-memcached
        2. sudo apt-get install memcached
        3. Increase cache size in /etc/memcached.conf to 128
        4. Set the Memcached at port 11211.

    ## Integration of Memcached in  Windows
        1. Go to your php.ini file usually located in C:/xampp/php/php.ini and add extension=php_memcache.dll
        2. Download php_memcache.dll from https://pecl.php.net/package/memcache/3.0.8/windows and put it into your php ext folder. Usually C:/xampp/php/ext/
        3. Download  binary win32 from http://downloads.northscale.com/memcached-win32-1.4.4-14.zip.
        4. Unzip and put the memcache.exe file into a desired directory (e.g. c:/memcached/)
        5. Open command line in Windows Administrator Mode.
        6. Install the memcache service Type the following into the command line
            c:\memcached\memcached.exe -d install
        7. Start memcached Type the following into the command line
            c:\memcached\memcached.exe -d start, or net start “memcached Server”
        8. Restart Xampp Apache
        9. Replace lines in getSystem and setSystem functions in javabridge.php in php code directory.
            9.1 new Memcached() with new Memcache.
            9.2 ->addServer with ->connect

    ## Setup PHP Code 
        1. Install WAMP for windows and LAMP Stack for linux.
        2. Setup database using mysql.
        3. Change the host, user and password in database.php file.

    ## Setup Database
        1. For Linux
                mysql -u root -p < elearning/elearn.sql
        2. For Window
                Import sql file in WAMP.

	## Miscelleneous Information
        ### Source of Ontology data used 
		    Using SwetoDblp Ontology from datahub.io

		    This ontology focuses on bibliography data of publications from DBLP with additions that include affiliations, universities, and publishers.

    		Source: http://knoesis.wright.edu/library/ontologies/swetodblp/
