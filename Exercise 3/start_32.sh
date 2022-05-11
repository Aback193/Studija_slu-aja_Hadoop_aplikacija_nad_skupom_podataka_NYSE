#!/bin/bash

echo "Filtriranje input fajlova samo na skup podataka iz 2014 godine tokom mapiranja [GME] simbol"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out32
yarn jar ex32.jar AvgStockVolMonthDriver /nyse_data /out32
echo "******************************************************************"
echo
hdfs dfs -cat /out32/part-r-00001
echo
echo "******************************************************************"
