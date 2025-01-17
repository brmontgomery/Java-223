//****************************************************************************************************************************
//Program name: "Assignment 4".  This program shows a cat chasing a mouse around a room.                                     *
//Copyright (C) 2021 Brian Montgomery.  All rights reserved.                                                                 *
//                                                                                                                           * 
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.  This program is distributed in the hope that it will be useful,   *
//but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   *
//the GNU General Public License for more details.  A copy of the GNU General Public License v3 is available here:           *
//<https://www.gnu.org/licenses/>.                                                                                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author: Brian Montgomery
//Email: brian_montgomery@csu.fullerton.edu

//Program information
  //Program name: Assignment 4
  //Programming language: Java
  //Files in this program: Assignment4.java (main), Assignmnet4Interface.java (UI frame), Assignment4MotionPanel.java (graphics panel), r.sh (Bash)
  //Date project began: Apr 18, 2021
  //Date of last update: Apr 19, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.                    
  //Purpose: This program shows a cat chasing a mouse around a room.
//
//This module
  //File name: Assignment4.java
  //Purpose of this file: This file contains the main function which will activate the UI.




import javax.swing.JFrame;

public class Assignment4
{   
    public static void main(String[] args)
        {
            System.out.println("Welcome to \"Cat and Mouse\" by Brian Montgomery.");
            System.out.println("Loading UI...");

            //create the frame
            JFrame assignment3frame = new Assignment4Interface();
            assignment3frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            assignment3frame.setVisible(true);
        }
  }