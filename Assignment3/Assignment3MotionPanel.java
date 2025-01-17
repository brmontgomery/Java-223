//****************************************************************************************************************************
//Program name: "Assignment 3".  This program shows a ball bouncing around the window.                                       *
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
  //Program name: Assignment 3
  //Programming language: Java
  //Files in this program: Assignment3.java (main), Assignmnet3Interface.java (UI frame), Assignment3MotionPanel.java (graphics panel), r.sh (Bash)
  //Date project began: Mar 14, 2021
  //Date of last update: Mar 17, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.                    
  //Purpose: This program shows a ball bouncing around the window
//
//This module
  //File name: Assignment3MotionPanel.java
  //Purpose of this file: This file contains the logic for the movement of the ball and the base lines
  
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class Assignment3MotionPanel extends JPanel
{    
    //ball vars
    private final double ballradius = 7.0;
    private final double balldiameter = 2.0*ballradius;
    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x;
    private int ball_upper_corner_integer_y;

    //line segment calc vars
    private double deltax;
    private double deltay;
    private boolean showBall = false;

    //ball move vars
    private double runner_speed_pix_per_tic;
    private double direction;
    private boolean successfulmove = true;

    //constructor
    public Assignment3MotionPanel()
    {
        setVisible(true);
        System.out.println("Graphics Panel has been constructed");
    }

    public void paintComponent(Graphics graphicarea)
    {   
        //set the paint area as the area wherethe diamond is
        super.paintComponent(graphicarea);
        setBackground(Color.BLUE);

        //paint the ball
        if(showBall == true) {
            graphicarea.setColor(Color.RED);
            graphicarea.fillOval(ball_upper_corner_integer_x,ball_upper_corner_integer_y, 
                                    (int)Math.round(balldiameter),(int)Math.round(balldiameter));
        }
    }

    //Inits ball coords and lines
    public void initializeobjectsinpanel(double runner_speed_pix_p_tic, double directionNumeric, double ballx, double bally )
    {   
        //set the runner's speed at the start
        runner_speed_pix_per_tic = runner_speed_pix_p_tic;
        direction = directionNumeric;
        ball_center_x = ballx; 
        ball_center_y = bally;

        recalculate();
    }

    public void recalculate()
    {   
            //calc direction
            deltax = Math.cos(Math.toRadians(direction))*runner_speed_pix_per_tic;
            deltay = -Math.sin(Math.toRadians(direction))*runner_speed_pix_per_tic;

            //set the ball location
            ball_upper_corner_x = ball_center_x - ballradius;   
            ball_upper_corner_y = ball_center_y - ballradius;  
            ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x); 
            ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);  
    }

    public boolean moveball()
    {    
        successfulmove = true;
        showBall = true;

        if(runner_speed_pix_per_tic == 0.0){
            return false;
        }

        //>^
        if (direction < 90) {
            //x direction
            if((deltax + ball_upper_corner_x + (2 * ballradius)) > (1280 - ballradius))
            {
                ball_center_x = (1280 - ballradius) - (((deltax + ball_upper_corner_x + ballradius) - (1280 - ballradius)));
                direction = 180 - direction;
            } else {
                ball_center_x += deltax;
            }

            //y direction
            if((deltay + ball_upper_corner_y) < (ballradius))
            {
                ball_center_y = (ballradius) + (deltay + ball_upper_corner_y);
                direction = 360 - direction;
            } else {
                ball_center_y += deltay;
            }
        }
        //<^
        else if (direction < 180) {
            //x direction
            if((ball_upper_corner_x + deltax) < ballradius)
            {
                ball_center_x = (2 * ballradius) - (ball_upper_corner_x + deltax);
                direction = 180 - direction;
            } else {
                ball_center_x += deltax;
            }

            //y direction
            if((deltay + ball_upper_corner_y) < (ballradius))
            {
                ball_center_y = (ballradius) + (((deltay + ball_upper_corner_y + ballradius) - (ballradius)));
                direction = 180 + (180 - direction);
            } else {
                ball_center_y += deltay;
            }
        }
        //<\/
        else if (direction < 270) {
            //x direction
            if((ball_upper_corner_x + deltax) < ballradius)
            {
                ball_center_x = (2 * ballradius) - (ball_upper_corner_x + deltax);
                direction = 360 - (direction - 180);
            } else {
                ball_center_x += deltax;
            }

            //y direction
            if((deltay + ball_upper_corner_y + (2 * ballradius)) > (570 - ballradius))
            {
                ball_center_y = (570 - ballradius) - (((deltay + ball_upper_corner_y + ballradius) - (570 - ballradius)));
                direction = 180 - (direction - 180);
            } else {
                ball_center_y += deltay;
            }
        } 
        //>\/
        else {
            //x direction
            if((deltax + ball_upper_corner_x + (2 * ballradius)) > (1280 - ballradius))
            {
                ball_center_x = (1280 - ballradius) - (((deltax + ball_upper_corner_x + ballradius) - (1280 - ballradius)));
                direction = 180 + (360 - direction);
            } else {
                ball_center_x += deltax;
            }

            //y direction
            if((deltay + ball_upper_corner_y + (2 * ballradius)) > (570 - ballradius))
            {
                ball_center_y = (570 - ballradius) - (((deltay + ball_upper_corner_y + ballradius) - (570 - ballradius)));
                direction = 180 - (direction - 180);
            } else {
                ball_center_y += deltay;
            }
        }

        //recalculate the next move
        recalculate();

        return successfulmove;
    }

    public double getXPos() {
        return ball_center_x;
    }

    public double getYPos() {
        return ball_center_y;
    }

    public double getDirection() {
        return direction;
    }
}