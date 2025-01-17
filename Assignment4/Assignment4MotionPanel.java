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
  //File name: Assignment4MotionPanel.java
  //Purpose of this file: This file contains the logic for the movement of the ball and the base lines
  
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class Assignment4MotionPanel extends JPanel
{    
    //ball vars
    private int height;
    private final double mouseballradius = 7.0;
    private final double mouseballdiameter = 2.0*mouseballradius;
    private double mouse_ball_center_x;
    private double mouse_ball_center_y;
    private double mouse_ball_upper_corner_x;
    private double mouse_ball_upper_corner_y;
    private int mouse_ball_upper_corner_integer_x;
    private int mouse_ball_upper_corner_integer_y;

    //line segment calc vars
    private double mousedeltax;
    private double mousedeltay;
    private boolean showBall = false;

    //mouse move vars
    private double mouse_speed_pix_per_tic;
    private double mousedirection;
    private boolean mousesuccessfulmove = true;

    //ball vars
    private final double catballradius = 14.0;
    private final double catballdiameter = 2.0*catballradius;
    private double cat_ball_center_x;
    private double cat_ball_center_y;
    private double cat_ball_upper_corner_x;
    private double cat_ball_upper_corner_y;
    private int cat_ball_upper_corner_integer_x;
    private int cat_ball_upper_corner_integer_y;

    //line segment calc vars
    private double catdeltax;
    private double catdeltay;

    //cat move vars
    private double cat_speed_pix_per_tic;
    private double catdirection;
    private boolean catsuccessfulmove = true;

    private double distanceBetween;

    //constructor
    public Assignment4MotionPanel(int height1)
    {
        height = height1;
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
            graphicarea.setColor(Color.RED);
            graphicarea.fillOval(mouse_ball_upper_corner_integer_x,mouse_ball_upper_corner_integer_y, 
                                    (int)Math.round(mouseballdiameter),(int)Math.round(mouseballdiameter));
            graphicarea.setColor(Color.YELLOW);
            graphicarea.fillOval(cat_ball_upper_corner_integer_x,cat_ball_upper_corner_integer_y, 
                                    (int)Math.round(catballdiameter),(int)Math.round(catballdiameter));
        }
    }

    //Inits ball coords and lines
    public void initializeobjectsinpanel(double mouse_speed_pix_p_tic, double mousedirectionNumeric, double mouseballx, double mousebally, double cat_speed_pix_p_tic, double catballx, double catbally)
    {   
        //set the cat and mouse's speeds at the start
        mouse_speed_pix_per_tic = mouse_speed_pix_p_tic;
        mousedirection = mousedirectionNumeric;
        mouse_ball_center_x = mouseballx; 
        mouse_ball_center_y = mousebally;

        cat_speed_pix_per_tic = cat_speed_pix_p_tic;
        cat_ball_center_x = catballx; 
        cat_ball_center_y = catbally;

        recalculateMouse();
        recalculateCat();
    }

    public void recalculateMouse()
    {   
        //calculate distance between
        distanceBetween = Math.sqrt(Math.pow(cat_ball_center_x - mouse_ball_center_x, 2) + Math.pow(cat_ball_center_y - mouse_ball_center_y, 2)) - catballradius - mouseballradius;
        if(distanceBetween <= 0){
            mousesuccessfulmove = false;
        }
        //calc direction
        mousedeltax = Math.cos(Math.toRadians(mousedirection))*mouse_speed_pix_per_tic;
        mousedeltay = -Math.sin(Math.toRadians(mousedirection))*mouse_speed_pix_per_tic;

        //set the ball location
        mouse_ball_upper_corner_x = mouse_ball_center_x - mouseballradius;   
        mouse_ball_upper_corner_y = mouse_ball_center_y - mouseballradius;  
        mouse_ball_upper_corner_integer_x = (int)Math.round(mouse_ball_upper_corner_x); 
        mouse_ball_upper_corner_integer_y = (int)Math.round(mouse_ball_upper_corner_y);  
    }

    public void recalculateCat()
    {   
            //calculate distance between
            distanceBetween = Math.sqrt(Math.pow(cat_ball_center_x - mouse_ball_center_x, 2) + Math.pow(cat_ball_center_y - mouse_ball_center_y, 2)) - catballradius - mouseballradius;
            if(distanceBetween <= 0){
                catsuccessfulmove = false;
            }

            //calc direction
            catdeltax = Math.cos(Math.toRadians(catdirection))*cat_speed_pix_per_tic;
            catdeltay = -Math.sin(Math.toRadians(catdirection))*cat_speed_pix_per_tic;

            //set the ball location
            cat_ball_upper_corner_x = cat_ball_center_x - catballradius;   
            cat_ball_upper_corner_y = cat_ball_center_y - catballradius;  
            cat_ball_upper_corner_integer_x = (int)Math.round(cat_ball_upper_corner_x); 
            cat_ball_upper_corner_integer_y = (int)Math.round(cat_ball_upper_corner_y);  
    }

    public boolean moveMouseBall()
    {    
        mousesuccessfulmove = true;
        showBall = true;

        //>^
        if (mousedirection < 90) {
            //x direction
            if((mousedeltax + mouse_ball_upper_corner_x + (4 * mouseballradius)) > (1280))
            {
                mouse_ball_center_x = 1280 - (3 * mouseballradius);
                mousedirection = 180 - mousedirection;
            } else {
                mouse_ball_center_x += mousedeltax;
            }

            //y direction
            if((mousedeltay + mouse_ball_upper_corner_y) < 0)
            {
                mouse_ball_center_y = (mouseballradius);
                mousedirection = 360 - mousedirection;
            } else {
                mouse_ball_center_y += mousedeltay;
            }
        }
        //<^
        else if (mousedirection < 180) {
            //x direction
            if((mouse_ball_upper_corner_x + mousedeltax) < 0)
            {
                mouse_ball_center_x = mouseballradius;
                mousedirection = 180 - mousedirection;
            } else {
                mouse_ball_center_x += mousedeltax;
            }

            //y direction
            if((mousedeltay + mouse_ball_upper_corner_y) < 0)
            {
                mouse_ball_center_y = mouseballradius;
                mousedirection = 180 + (180 - mousedirection);
            } else {
                mouse_ball_center_y += mousedeltay;
            }
        }
        //<\/
        else if (mousedirection < 270) {
            //x direction
            if((mouse_ball_upper_corner_x + mousedeltax) < 0)
            {
                mouse_ball_center_x = mouseballradius;
                mousedirection = 360 - (mousedirection - 180);
            } else {
                mouse_ball_center_x += mousedeltax;
            }

            //y direction
            if((mousedeltay + mouse_ball_upper_corner_y) > height - 50)
            {
                System.out.println((mousedeltay + mouse_ball_upper_corner_y + (2 * mouseballradius)));
                mouse_ball_center_y = (height - 50 + mouseballradius);
                System.out.println(mouse_ball_center_y + mouseballradius);
                mousedirection = 180 - (mousedirection - 180);
            } else {
                mouse_ball_center_y += mousedeltay;
            }
        } 
        //>\/
        else {
            //x direction
            if((mousedeltax + mouse_ball_upper_corner_x + (4 * mouseballradius)) > (1280))
            {
                mouse_ball_center_x = 1280 - (3 * mouseballradius);
                mousedirection = 180 - mousedirection;
            } else {
                mouse_ball_center_x += mousedeltax;
            }

            //y direction
            if((mousedeltay + mouse_ball_upper_corner_y) > height - 50)
            {
                System.out.println((mousedeltay + mouse_ball_upper_corner_y + (2 * mouseballradius)));
                mouse_ball_center_y = (height - 50 + mouseballradius);
                System.out.println(mouse_ball_center_y + mouseballradius);
                mousedirection = 180 - (mousedirection - 180);
            } else {
                mouse_ball_center_y += mousedeltay;
            }
        }

        //recalculate the next move
        recalculateMouse();

        return mousesuccessfulmove;
    }

    public boolean moveCatBall()
    {    
        catsuccessfulmove = true;
        showBall = true;

        //get direction for cat
        catdirection = -(double) Math.toDegrees(Math.atan2(mouse_ball_center_y - cat_ball_center_y, mouse_ball_center_x - cat_ball_center_x));
        
        if(catdirection < 0){
            catdirection = 360 + catdirection;
        }

        cat_ball_center_x += catdeltax;
        cat_ball_center_y += catdeltay;

        //recalculate the next move
        recalculateCat();

        return catsuccessfulmove;
    }

    //getter funcs
    public double getMouseXPos() {
        return mouse_ball_center_x;
    }

    public double getMouseYPos() {
        return mouse_ball_center_y;
    }

    public double getMouseDirection() {
        return mousedirection;
    }

    public double getCatXPos() {
        return cat_ball_center_x;
    }

    public double getCatYPos() {
        return cat_ball_center_y;
    }

    public double getCatDirection() {
        return catdirection;
    }

    public double getDistanceBetween() {
        return distanceBetween;
    }
}