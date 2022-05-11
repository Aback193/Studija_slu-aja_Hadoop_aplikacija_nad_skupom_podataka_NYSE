#!/bin/bash

echo "Filtriranje input fajlova CMD arg, SETUP [AA][GME] simboli"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out34
yarn jar ex34.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME /nyse_data /out34
echo "******************************************************************"
echo
hdfs dfs -cat /out34/part-r-00001
echo
echo "******************************************************************"
