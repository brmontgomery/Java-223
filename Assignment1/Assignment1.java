//****************************************************************************************************************************
//Program name: "Assignment 1".  This program calculates the amount due to an employee for their salary and hours worked     *
//Copyright (C) 2021 Brian Montgomery                                                                                        *
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.                                                                    *
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
//warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.     *
//A copy of the GNU General Public License v3 is available here:  <https://www.gnu.org/licenses/>.                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author information:
  //Author: Brian Montgomery
  //Mail: brian_montgomery@fcsu.fullerton.edu

//Program information:
  //Program name: Assignment1
  //Programming language: Java
  //Files: Assignment1.java, Assignment1frame.java, Assignment1Functions.java
  //Date project began: 2021-Feb-2.
  //Date of last update: 2021-Feb-13.
  //Status: Finished; testing completed.
  //Purpose: This program is designed to calculate the payment needed for a particular employee for the hours they have worked 
  //and their hourly wage
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: Assignemnt1.java
  //Compile : javac Assignment1.java
  //This module activates the User Interface (UI)

import javax.swing.JFrame;
public class Assignment1
{
    public static void main(String[] args)
    {
      //initialize the program and initial frame
      Assignment1frame myframe = new Assignment1frame();
      myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myframe.setSize(600,400);
      myframe.setVisible(true);
    }
}
  