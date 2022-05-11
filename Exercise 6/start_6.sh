#!/bin/bash

echo "Kustomizovan particioner => CMD arg simbol ili mesec"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out6
yarn jar ex6.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME -Dpart=simbol /nyse_data /out6
#yarn jar ex6.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME -Dpart=mesec /nyse_data /out6
echo "******************************************************************"
echo
hdfs dfs -cat /out6/part-r-00001
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out6/part-r-00002
echo
echo "******************************************************************"
