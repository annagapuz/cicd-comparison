#!/bin/sh

alternatives --set java java-11-openjdk.x86_64

cd /opt/movie || exit 1

svn co --non-interactive --no-auth-cache --username admin --password password http://subversion/svn/movie-service/trunk MovieService

svn co --non-interactive --no-auth-cache --username admin --password password http://subversion/svn/movie-web/trunk MovieWeb

svn co --non-interactive --no-auth-cache --username admin --password password http://subversion/svn/pipeline/trunk Pipeline

mvn deploy:deploy-file -DrepositoryId=demoRepo -Durl=http://nexus:8081/repository/demo-release/ -Dfile=/tmp/jconn4.jar -DgroupId=com.sybase.jdbc4 -DartifactId=jconn -Dversion=4 -Dpackaging=jar

exit 0
