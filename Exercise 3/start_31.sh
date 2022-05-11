#!/bin/bash

echo "Filtriranje input fajlova samo na skup podataka iz 2014 godine"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out31
yarn jar ex31.jar FilterInDriver /nyse_data /out31
echo "******************************************************************"
echo
hdfs dfs -cat /out31/part-r-00000 | head
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out31/part-r-00000 | tail
echo
echo "******************************************************************"
