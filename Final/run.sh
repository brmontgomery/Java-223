#!/bin/bash

#Program name: Final
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

echo Compile Final.java
javac Final.java

echo Compile FinalInterface.java
javac FinalInterface.java

echo Compile FinalMotionPanel.java
javac FinalMotionPanel.java

echo Execute the Baserunner program
java Final

echo Completed. Terminating...