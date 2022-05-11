#!/bin/bash

echo "Izlista sve fajlove koji u nazivu imaju nyse_201[1-4].*"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /merge
yarn jar ex2.jar CopyMerge /nyse_data /merge
echo "******************************************************************"
echo
hdfs dfs -cat /merge | head
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /merge | tail
echo
echo "******************************************************************"
