#!/bin/bash

echo "List files inside input folder"
echo

# RUN JOB
echo "******************************************************************"
yarn jar ex2.jar ListFiles /
echo
echo "******************************************************************"
yarn jar ex2.jar ListFiles /nyse_data
echo
echo "******************************************************************"
