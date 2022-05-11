#!/bin/bash

echo "Kustomizovan particioner => svaki simbol ide u svoj output fajl"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out5
yarn jar ex5.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME /nyse_data /out5
echo "******************************************************************"
echo
hdfs dfs -cat /out5/part-r-00001
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out5/part-r-00002
echo
echo "******************************************************************"
