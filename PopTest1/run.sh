#!/bin/bash

#Program name: PopTest1
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

echo Compile PopTest1.java
javac PopTest1.java

echo Compile PopTest1Frame.java
javac PopTest1Frame.java

echo Compile PopTest1Functions.java
javac PopTest1Functions.java

echo Execute the program
java PopTest1

echo Completed. Terminating...
