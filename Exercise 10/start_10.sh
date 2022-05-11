#!/bin/bash

echo "TOP 3 VOL by Day"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out10
yarn jar ex10.jar TopThreeStocksByVolumePerDayDriver /nyse_data /out10

# NO TRADE DAYS WILL BE PRINTED OUT
