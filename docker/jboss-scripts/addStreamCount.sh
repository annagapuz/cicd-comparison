#!/bin/bash

while :
do
	RANDOM_MOVIE=$(( ( RANDOM % 10 )  + 1 ))
  RANDOM_COUNT=$(( ( RANDOM % 20 )  + 1 ))
  curl --location --request PUT http://jboss-service:8080/movie-service/movies/1/streamCount/$RANDOM_COUNT
	sleep 30
done
