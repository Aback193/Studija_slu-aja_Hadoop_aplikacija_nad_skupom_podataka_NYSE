#!/bin/bash

echo "Filtriranje input fajlova CMD arg, tokom mapiranja [AA] simbol"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out33
yarn jar ex33.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA /nyse_data /out33
echo "******************************************************************"
echo
hdfs dfs -cat /out33/part-r-00000
echo
echo "******************************************************************"
