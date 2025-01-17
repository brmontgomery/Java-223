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
  //and their hourly wage.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

  //This module
  //File name: Assignemnt1Functions.java
  //Compile : javac Assignment1Functions.java
  //This is the lowest level module. It only includes the functions required for calculating the final values.
  //This module (class) is called from the Assignment1frame class.


public class Assignment1Functions
{
    //calculate grossPay from regular and overtime pays
    public static float getGrossPay(float regularPay, float overtimePay)
    {
        float grossPay;
        grossPay = regularPay + overtimePay;
        return grossPay;
    }

    //calculate overtime Pay if above 40 hours
    public static float getOvertimePay(float hours, float payrate)
    {   
        float overtimePay;
        if(hours <= 40.0f)
            return 0.0f;
        else
            overtimePay = ((hours - 40.0f) * payrate) * 1.5f;
        return overtimePay;
    }

    //calculate regular pay for 40 hours or less
    public static float getRegularPay(float hours, float payrate)
    {   
        float regularPay;
        if(hours < 40.0f)
            regularPay = hours * payrate;
        else
            regularPay = 40.0f * payrate;
        return regularPay;
    }
}