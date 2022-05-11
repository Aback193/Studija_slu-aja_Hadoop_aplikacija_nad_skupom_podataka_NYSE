#!/bin/bash

echo "11.2 Add local files to distributed cache"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out11_2
yarn jar ex11_2.jar DCWCwithStopWordsDriver hdfs://192.168.122.207:10001/cache/cache1.txt hdfs://192.168.122.207:10001/cache/cache2.txt -skip hdfs://192.168.122.207:10001/cache/cache_patterns.txt /out11_2
output1=$(hdfs dfs -cat /cache/cache1.txt)
output2=$(hdfs dfs -cat /cache/cache2.txt)
output3=$(hdfs dfs -cat /cache/cache_patterns.txt)
echo "****************************** INPUT 1 ************************************"
echo $output1
echo "****************************** INPUT 1 ************************************"
echo "****************************** INPUT 2 ************************************"
echo $output2
echo "****************************** INPUT 2 ************************************"
echo "****************************** INPUT PATTERN FOR EXCLUSION ************************************"
echo $output3
echo "****************************** INPUT PATTERN FOR EXCLUSION ************************************"
echo "******************************** FINAL OUTPUT WITH WORDCOUNT AND EXCLUSION [drvo] **********************************"
hdfs dfs -cat /out11_2/part-r-00000
echo "******************************** FINAL OUTPUT WITH WORDCOUNT AND EXCLUSION [drvo] **********************************"

