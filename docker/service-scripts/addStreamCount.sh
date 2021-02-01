#!/bin/bash

while :
do
	RANDOM_MOVIE=$(( ( RANDOM % 10 )  + 1 ))
  RANDOM_COUNT=$(( ( RANDOM % 20 )  + 1 ))
  echo "Adding count of $RANDOM_COUNT to movie id $RANDOM_MOVIE\n"
  curl --location --request PUT http://jboss:8080/movie-service/movies/$RANDOM_MOVIE/streamCount/$RANDOM_COUNT
	sleep 30
done
