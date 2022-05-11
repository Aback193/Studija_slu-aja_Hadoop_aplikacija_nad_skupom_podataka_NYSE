#!/bin/bash

echo "Standard vs Compressed using Snappy codec"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out9
yarn jar ex9.jar FilterInDriver /nyse_data /out9

hdfs dfs -rmr -skipTrash /out91
yarn jar ex91.jar FilterInDriver /nyse_data /out91

# OUTPUT
echo
echo "******************************************************************"
hdfs dfs -ls /out9
echo "******************************************************************"
echo
echo "******************************************************************"
hdfs dfs -ls /out91
echo "******************************************************************"
echo
