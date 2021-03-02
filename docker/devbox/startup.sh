#!/bin/sh

alternatives --set java java-11-openjdk.x86_64

cd /opt/movie/local

svn import --non-interactive --no-auth-cache --username admin --password password -m "Initial checkin" http://subversion/svn/platform/trunk/applications/movie-application

svn co --force --non-interactive --no-auth-cache --username admin --password password http://subversion/svn/platform/trunk/applications/movie-application /opt/movie/local

svn propset svn:ignore .git .

mvn deploy:deploy-file -DrepositoryId=demoRepo -Durl=http://nexus:8081/repository/demo-release/ -Dfile=/tmp/jconn4.jar -DgroupId=com.sybase.jdbc4 -DartifactId=jconn -Dversion=4 -Dpackaging=jar

exit 0
