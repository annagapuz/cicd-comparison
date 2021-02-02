#!/bin/sh

alternatives --set java java-11-openjdk.x86_64

cd /opt/movie || exit 1

svn co --non-interactive --no-auth-cache --username admin --password password http://subversion/svn/movie-service/trunk MovieService

exit 0