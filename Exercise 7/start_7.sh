#!/bin/bash

echo "Particioniranje i sortiranje based on the first field in custom key using comparator. Sortiranje postizemo negiranjem compareTo() vrednosti i dobijamo opadajuce sortiranje"
echo

# RUN JOB
hdfs dfs -rmr -skipTrash /out7
yarn jar ex7.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME -Dpart=simbol /nyse_data /out7
#yarn jar ex7.jar AvgStockVolMonthDriver -Dfilter.by.stockTicker=AA,GME -Dpart=mesec /nyse_data /out7
echo "******************************************************************"
echo
hdfs dfs -cat /out7/part-r-00001
echo
echo "******************************************************************"
echo
echo "******************************************************************"
echo
hdfs dfs -cat /out7/part-r-00002
echo
echo "******************************************************************"
