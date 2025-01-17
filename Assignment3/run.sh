#!/bin/bash

#Program name: Assignment 4
#Author: Brian Montgomery
#Email: brian_montgomery@csu.fullerton.edu
#File name:  run.sh
#Preconditions:
#   1.  All source files are in one directory
#   2.  This file, run.sh, is in that same directory
#   3.  The shell is currently active in that same directory
#How to execute: Enter "sh run.sh" without the quotes.

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile Assignment3.java
javac Assignment3.java

echo Compile Assignment3Interface.java
javac Assignment3Interface.java

echo Compile Assignment3MotionPanel.java
javac Assignment3MotionPanel.java

echo Execute the Baserunner program
java Assignment3

echo Completed. Terminating...