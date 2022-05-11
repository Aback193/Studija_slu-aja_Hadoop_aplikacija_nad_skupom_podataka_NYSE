#!/bin/bash

echo "11. Spajanje imena kompanije sa simbolom akcije"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out11
yarn jar ex11.jar StockCompanyJoinDistCacheDriver -files /home/ab/companylist_noheader.csv /nyse_data /out11
hdfs dfs -rmr -skipTrash /out112
yarn jar ex11_2.jar AddingCache
echo "******************************************************************"
echo
out=$(hdfs dfs -cat /out11/part-m-00000)
output1=$(echo "$out" | grep "Gamestop" | head -1)
output2=$(echo "$out" | grep "DPZ" | head -1)
output3=$(echo "$out" | grep "IBM" | head -1)
output4=$(echo "$out" | grep "DIS" | head -1)
output5=$(echo "$out" | grep "ORCL" | head -1)
output6=$(echo "$out" | grep "KO" | head -1)
output7=$(echo "$out" | grep "Master" | head -1)
echo $output1 
echo $output2
echo $output3
echo $output4
echo $output5
echo $output6
echo $output7
echo
echo "******************************************************************"

