#!/bin/bash

echo "Izlista sve fajlove koji u nazivu imaju nyse_201[1-4].*"
echo

# RUN JOB
echo "******************************************************************"
yarn jar ex2.jar FilePattern /
echo
echo "******************************************************************"
yarn jar ex2.jar FilePattern /nyse_data
echo
echo "******************************************************************"
