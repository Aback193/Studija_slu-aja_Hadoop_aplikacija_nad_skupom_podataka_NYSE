#!/bin/bash

echo "Definisan particioner => svaki simbol ide u svoj output fajl"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out4
yarn jar ex4.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME /nyse_data /out4
echo "******************************************************************"
echo
hdfs dfs -cat /out4/part-r-00000
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out4/part-r-00001
echo
echo "******************************************************************"
