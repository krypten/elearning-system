ReadMe

	How to setup Virtuoso on ubuntu
		1. sudo aptitude install git-core 
		2. git clone git://github.com/openlink/virtuoso-opensource.git
		3. cd ~/virtuoso-opensource
		4. git checkout stable/7 
		5. git pull origin stable/7
		6. ./autogen.sh
		7. sudo aptitude install aclocal autoconf autoheader automake bison build-essential checkinstall flex gawk gperf libiodbc2 libiodbc2-dev libssl-dev libtool python-dev 
		8. ./autogen.sh 
		9. ./configure --with-layout=debian
		10. make 
		11. sudo make install
		12. sudo virtuoso-t -fd -c /var/lib/virtuoso/db/virtuoso.ini

	How to access Virtuoso
		1. Open your browser
		2. Goto site localhost:8890/
		3. For login the default username/password combination is username : dba and password : dba

	Using Apache Jena for RDF data
		1. Download and install eclipse.
		2. Download and unzip Jena.
		3. Create a new Java project.
		4. Create a new class
		5. Add the Jena libraries 
			5.1 Goto Window > Preferences
			5.2 Open Java > Build Path > User Libraries
			5.3 Create a new User Library and name it "JenaLibs"
			5.4 Add Jars from the Jena extracted folder.
		6. Add the user library "JenaLibs" to the project.
		7. Add the desired code to work/extract/read/write the rdf data.
		8. Run the java application.

	Source of Ontology data used 
		Using SwetoDblp

		Information regrading the Data: ontology focused on bibliography data of publications from DBLP with additions that include affiliations, universities, and publishers.

		Source: http://knoesis.wright.edu/library/ontologies/swetodblp/  
