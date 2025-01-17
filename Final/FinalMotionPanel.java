//****************************************************************************************************************************
//Program name: "Final".  This program shows the earth orbiting the sun                                   *
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
  //Program name: Final
  //Programming language: Java
  //Files in this program: Final.java (main), FinalInterface.java (UI frame), FinalMotionPanel.java (graphics panel), r.sh (Bash)
  //Date project began: Apr 18, 2021
  //Date of last update: Apr 19, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.                    
  //Purpose: This program shows the earth orbiting the sun   
//
//This module
  //File name: FinalMotionPanel.java
  //Purpose of this file: This file contains the logic for the movement of the Earth
  
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.xml.namespace.QName;

import java.awt.Color;
import java.awt.Dimension;

public class FinalMotionPanel extends JPanel
{    
    //ball vars
    private double timeSoFar;
    private double delta_t;
    private double earthballradius;
    private double earthballdiameter;
    private double earth_ball_center_x;
    private double earth_ball_center_y;
    private double earth_ball_upper_corner_x;
    private double earth_ball_upper_corner_y;
    private int earth_ball_upper_corner_integer_x;
    private int earth_ball_upper_corner_integer_y;
    private double earth_orbit_distance;

    private double marsballradius;
    private double marsballdiameter;
    private double mars_ball_center_x;
    private double mars_ball_center_y;
    private double mars_ball_upper_corner_x;
    private double mars_ball_upper_corner_y;
    private int mars_ball_upper_corner_integer_x;
    private int mars_ball_upper_corner_integer_y;
    private double mars_orbit_distance;

    private double moonballradius;
    private double moonballdiameter;
    private double moon_ball_center_x;
    private double moon_ball_center_y;
    private double moon_ball_upper_corner_x;
    private double moon_ball_upper_corner_y;
    private int moon_ball_upper_corner_integer_x;
    private int moon_ball_upper_corner_integer_y;
    private double moon_orbit_distance;

    //line segment calc vars
    private double earthdeltax;
    private double earthdeltay;
    private boolean showBall = false;

    //earth move vars
    private boolean earthsuccessfulmove = true;

    //ball vars
    private double sunballradius;
    private double sunballdiameter;
    private double sun_ball_center_x;
    private double sun_ball_center_y;
    private double sun_ball_upper_corner_x;
    private double sun_ball_upper_corner_y;
    private int sun_ball_upper_corner_integer_x;
    private int sun_ball_upper_corner_integer_y;

    //constructor
    public FinalMotionPanel()
    {
        setVisible(true);
        setPreferredSize(new Dimension(1280,600));
        System.out.println("Graphics Panel has been constructed");
    }

    public void paintComponent(Graphics graphicarea)
    {   
        //set the paint area as the area wherethe diamond is
        super.paintComponent(graphicarea);
        setBackground(Color.BLUE);

        //paint the balls
        if(showBall == true) {
            graphicarea.setColor(Color.GREEN);
            graphicarea.fillOval(earth_ball_upper_corner_integer_x,earth_ball_upper_corner_integer_y, 
                                    (int)Math.round(earthballdiameter),(int)Math.round(earthballdiameter));
            graphicarea.setColor(Color.RED);
            graphicarea.fillOval(mars_ball_upper_corner_integer_x,mars_ball_upper_corner_integer_y, 
                                    (int)Math.round(marsballdiameter),(int)Math.round(marsballdiameter));
            graphicarea.setColor(Color.GRAY);
            graphicarea.fillOval(moon_ball_upper_corner_integer_x,moon_ball_upper_corner_integer_y, 
                                    (int)Math.round(moonballdiameter),(int)Math.round(moonballdiameter));
            graphicarea.setColor(Color.YELLOW);
            graphicarea.fillOval(sun_ball_upper_corner_integer_x,sun_ball_upper_corner_integer_y, 
                                    (int)Math.round(sunballdiameter),(int)Math.round(sunballdiameter));
        }
    }

    //Inits ball coords and lines
    public void initializeobjectsinpanel(double deltat, boolean reset, double earthballx, double earthbally, double earthorbitdistance, double sunballx, double sunbally, int earthradius, int sunradius, double marsballx, double marsbally)
    {   
        //set the sun and earth's speeds at the start
        if(reset) {
            timeSoFar = 0;
        }
        delta_t = deltat;
        earth_orbit_distance = earthorbitdistance;
        earth_ball_center_x = earthballx; 
        earth_ball_center_y = earthbally;
        earthballradius = earthradius;
        earthballdiameter = earthballradius*2;

        mars_orbit_distance = earthorbitdistance * 2;
        mars_ball_center_x = marsballx; 
        mars_ball_center_y = marsbally;
        marsballradius = earthradius / 1.5;
        marsballdiameter = marsballradius*2;

        moon_orbit_distance = earthorbitdistance / 3;
        moon_ball_center_x = earth_ball_center_x - moon_orbit_distance; 
        moon_ball_center_y = earth_ball_center_y;
        moonballradius = earthradius / 3;
        moonballdiameter = moonballradius*2;

        sunballradius = sunradius;
        sunballdiameter = sunballradius*2;
        sun_ball_center_x = sunballx; 
        sun_ball_center_y = sunbally;
        sun_ball_upper_corner_x = sun_ball_center_x - sunballradius;   
        sun_ball_upper_corner_y = sun_ball_center_y - sunballradius;  
        sun_ball_upper_corner_integer_x = (int)Math.round(sun_ball_upper_corner_x); 
        sun_ball_upper_corner_integer_y = (int)Math.round(sun_ball_upper_corner_y);  

        recalculateEarth();
        recalculateMars();
        recalculateMoon();
    }

    public void recalculateEarth()
    {   
        //calc direction
        earth_ball_center_x = (-Math.cos(timeSoFar) * earth_orbit_distance) + sun_ball_center_x;
        earth_ball_center_y = Math.sin(timeSoFar) * earth_orbit_distance + sun_ball_center_y;

        //function to reverse calcs to get speed per second (if clock speed is 100)
        System.out.println(/*undo the delta_t calcs and cos/sin normalization by multiplying delta_t and approx clock speed*/ delta_t * 100 /*tick distance*/ * Math.sqrt(Math.pow((-Math.cos(delta_t) * earth_orbit_distance), 2) + Math.pow((Math.sin(delta_t) * earth_orbit_distance), 2)));
        
        //set the ball location
        earth_ball_upper_corner_x = earth_ball_center_x - earthballradius;   
        earth_ball_upper_corner_y = earth_ball_center_y - earthballradius;  
        earth_ball_upper_corner_integer_x = (int)Math.round(earth_ball_upper_corner_x); 
        earth_ball_upper_corner_integer_y = (int)Math.round(earth_ball_upper_corner_y);  
    }

    public void recalculateMars()
    {   
        //calc direction
        mars_ball_center_x = (-Math.cos(timeSoFar/2) * mars_orbit_distance) + sun_ball_center_x;
        mars_ball_center_y = Math.sin(timeSoFar/2) * mars_orbit_distance + sun_ball_center_y;

        //function to reverse calcs to get speed per second (if clock speed is 100)
        System.out.println(/*undo the delta_t calcs and cos/sin normalization by multiplying delta_t and approx clock speed*/ delta_t * 100 /*tick distance*/ * Math.sqrt(Math.pow((-Math.cos(delta_t/2) * mars_orbit_distance), 2) + Math.pow((Math.sin(delta_t/2) * mars_orbit_distance), 2)));
        
        //set the ball location
        mars_ball_upper_corner_x = mars_ball_center_x - marsballradius;   
        mars_ball_upper_corner_y = mars_ball_center_y - marsballradius;  
        mars_ball_upper_corner_integer_x = (int)Math.round(mars_ball_upper_corner_x); 
        mars_ball_upper_corner_integer_y = (int)Math.round(mars_ball_upper_corner_y);  
    }

    public void recalculateMoon()
    {   
        //calc direction
        moon_ball_center_x = (-Math.cos(timeSoFar*28) * moon_orbit_distance) + earth_ball_center_x;
        moon_ball_center_y = Math.sin(timeSoFar*28) * moon_orbit_distance + earth_ball_center_y;

        //function to reverse calcs to get speed per second (if clock speed is 100)
        System.out.println(/*undo the delta_t calcs and cos/sin normalization by multiplying delta_t and approx clock speed*/ delta_t * 100 /*tick distance*/ * Math.sqrt(Math.pow((-Math.cos(delta_t*28) * moon_orbit_distance), 2) + Math.pow((Math.sin(delta_t*28) * moon_orbit_distance), 2)));
        
        //set the ball location
        moon_ball_upper_corner_x = moon_ball_center_x - moonballradius;   
        moon_ball_upper_corner_y = moon_ball_center_y - moonballradius;  
        moon_ball_upper_corner_integer_x = (int)Math.round(moon_ball_upper_corner_x); 
        moon_ball_upper_corner_integer_y = (int)Math.round(moon_ball_upper_corner_y);  
    }

    public boolean moveEarthBall()
    {   
        timeSoFar += delta_t;
        earthsuccessfulmove = true;
        showBall = true;

        //recalculate the next move
        recalculateEarth();
        recalculateMars();
        recalculateMoon();

        return earthsuccessfulmove;
    }

    //getter funcs
    public double getEarthXPos() {
        return earth_ball_center_x;
    }

    public double getEarthYPos() {
        return earth_ball_center_y;
    }

    public double getMarsXPos() {
        return earth_ball_center_x;
    }

    public double getMarsYPos() {
        return earth_ball_center_y;
    }

    public double getSunXPos() {
        return sun_ball_center_x;
    }

    public double getSunYPos() {
        return sun_ball_center_y;
    }
}