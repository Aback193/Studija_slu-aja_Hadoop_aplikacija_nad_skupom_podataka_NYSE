#!/bin/bash

echo "Get average volume traded for each stock per month from NYSE dataset"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out1
yarn jar ex1.jar FilterInDriver /nyse_data /out1

# OUTPUT
echo "******************************************************************"
hdfs dfs -cat /out1/part-r-00000 | head
echo "******************************************************************"
hdfs dfs -cat /out1/part-r-00000 | tail
echo "******************************************************************"
