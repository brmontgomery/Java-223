#!/bin/bash

#Program name: Assignment 1
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

echo Compile Assignment1.java
javac Assignment1.java

echo Compile Assignment1frame.java
javac Assignment1frame.java

echo Compile Assignment1Functions.java
javac Assignment1Functions.java

echo Execute the Arithmetic program
java Assignment1

echo Completed. Terminating...
