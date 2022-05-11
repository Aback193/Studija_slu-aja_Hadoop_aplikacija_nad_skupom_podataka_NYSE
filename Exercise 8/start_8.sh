#!/bin/bash

echo "TOP 3 VOL by Day"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out8
yarn jar ex8.jar TopThreeStocksByVolumePerDayDriver /nyse_data /out8
echo "******************************************************************"
echo
hdfs dfs -cat /out8/trademonth=201401/part-r-00005 | head
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out8/trademonth=201402/part-r-00000 | head
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out8/trademonth=201403/part-r-00001 | head
echo
echo "******************************************************************"
