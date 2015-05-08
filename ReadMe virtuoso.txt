#Read Me
	## How to setup Virtuoso on Ubuntu
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

	## How to access Virtuoso
		1. Open your browser
		2. Goto site localhost:8890/
		3. For login the default username/password combination is username : dba and password : dba
