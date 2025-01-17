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
  //File name: Assignment4Interface.java
  //Purpose of this file: This file contains the UI and the reference to the graphics of the runner.
  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Assignment4Interface extends JFrame
{   
    private final int UI_width = 1280;
    private final int UI_height = 720;

    //Variables for the speed of the ball  
    private double mouse_speed_pix_per_tic;  
    private double cat_speed_pix_per_tic;                

    //Title panel objects
    private JPanel titlepanel;
    private final int titlepanel_width = UI_width;
    private final int titlepanel_height = 50;
    private JLabel titlelabel;

    //Control Panel Objects
    private JPanel buttonpanel;
    private final int controlpanel_width = UI_width;
    private final int controlpanel_height = 50;
    private JLabel refreshlabel;
    private JTextField refreshtextfield;
    private JLabel mousespeedlabel;
    private JTextField mousespeedtextfield;
    private JLabel mousedirectionlabel;
    private JTextField mousedirectiontextfield;
    private JLabel catspeedlabel;
    private JTextField catspeedtextfield;
    private JLabel catdirectionlabel;
    private JTextField catdirectiontextfield;
    private JButton startbutton;
    private JButton clearbutton;
    private JButton quitbutton;

    private JLabel betweendistanceLabel;
    private JTextField betweendistancefield;

    //Graphics panel Objects
    private Assignment4MotionPanel graphicspanel;
    private final int graphicspanel_width = UI_width;
    private final int graphicspanel_height = UI_height - titlepanel_height - controlpanel_height;

    //Line segment components
    private String tempstring;
    private double mousespeed;
    private double catspeed;
    private double mousedirection;
    private double catdirection;

    //Clock vars
    private Timer refreshclock;
    private Timer mousemotionclock;
    private Timer catmotionclock;
    private Buttonhandlerclass buttonhandler;
    private Clockhandlerclass clockhandler;
    private double refresh_clock_rate;
    private int refresh_clock_delay_interval;
    private final double mouse_motion_clock_rate = 99.873;
    private int mouse_motion_clock_delay_interval;
    private final double cat_motion_clock_rate = 99.873;
    private int cat_motion_clock_delay_interval;
    private final double millisecondpersecond = 1000.0;       
    
    //program end monitor variable
    private boolean active = false;
    private boolean started = false;                    

    public Assignment4Interface()                      
        {
            //initial setup
            super("Cat and Mouse");
            setLayout(new BorderLayout());
            setSize(UI_width,UI_height);
            System.out.println("Welcome to Cat and Mouse");
            System.out.println(graphicspanel_width);
            System.out.println(graphicspanel_height);
            setLocationRelativeTo(null);
            setVisible(false);
            setResizable(false);

            mouse_speed_pix_per_tic = 0.0;
            cat_speed_pix_per_tic = 0.0;
            buttonhandler = new Buttonhandlerclass();

            //Setting up the three panels
            //Panel 1: Title Panel
            titlelabel = new JLabel("Cat and Mouse");
            titlepanel = new JPanel();
            titlepanel.setPreferredSize( new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.setMinimumSize(new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.setMaximumSize(new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.add(titlelabel);
            add(titlepanel,BorderLayout.NORTH);

            //Panel 2: Graphics Panel
            graphicspanel = new Assignment4MotionPanel(graphicspanel_height);
            graphicspanel.setBackground(Color.BLUE);
            graphicspanel.setPreferredSize( new Dimension(graphicspanel_width,graphicspanel_height));
            graphicspanel.setMinimumSize(new Dimension(graphicspanel_width,graphicspanel_height));
            graphicspanel.setMaximumSize(new Dimension(graphicspanel_width,graphicspanel_height));
            add(graphicspanel,BorderLayout.CENTER);

            //Panel 3: Control Panel
            //start button
            startbutton = new JButton("Start");
            startbutton.addActionListener(buttonhandler);

            //clear button
            clearbutton = new JButton("Clear");
            clearbutton.addActionListener(buttonhandler);

            //quit button
            quitbutton = new JButton("Exit");
            quitbutton.setBackground(Color.RED);
            quitbutton.addActionListener(buttonhandler);

            //refresh setting
            refreshlabel = new JLabel("Refresh Rate (Hz)");
            refreshlabel.setForeground(Color.BLUE);
            refreshtextfield = new JTextField(6);
            refreshtextfield.setText("");
            refreshtextfield.setBackground(Color.GREEN);
            refreshtextfield.setFont(new Font("Arial",Font.BOLD,12));
            refreshtextfield.setHorizontalAlignment(JTextField.CENTER);
            
            //mouse speed monitor
            mousespeedlabel = new JLabel("Mouse Speed (pix/sec");
            mousespeedlabel.setForeground(Color.BLUE);
            mousespeedtextfield = new JTextField(6);
            mousespeedtextfield.setText("");
            mousespeedtextfield.setBackground(Color.GREEN);
            mousespeedtextfield.setFont(new Font("Arial",Font.BOLD,12));
            mousespeedtextfield.setHorizontalAlignment(JTextField.CENTER);

            //mouse direction monitor
            mousedirectionlabel = new JLabel("Mouse Direction");
            mousedirectionlabel.setForeground(Color.BLUE);
            mousedirectiontextfield = new JTextField(6);
            mousedirectiontextfield.setText("");
            mousedirectiontextfield.setBackground(Color.GREEN);
            mousedirectiontextfield.setFont(new Font("Arial",Font.BOLD,12));
            mousedirectiontextfield.setHorizontalAlignment(JTextField.CENTER);

            //cat speed monitor
            catspeedlabel = new JLabel("Cat Speed (pix/sec");
            catspeedlabel.setForeground(Color.BLUE);
            catspeedtextfield = new JTextField(6);
            catspeedtextfield.setText("200.0");
            catspeedtextfield.setBackground(Color.GREEN);
            catspeedtextfield.setFont(new Font("Arial",Font.BOLD,12));
            catspeedtextfield.setHorizontalAlignment(JTextField.CENTER);

            //cat direction monitor
            catdirectionlabel = new JLabel("Cat Direction");
            catdirectionlabel.setForeground(Color.BLUE);
            catdirectiontextfield = new JTextField(6);
            catdirectiontextfield.setText("");
            catdirectiontextfield.setBackground(Color.GREEN);
            catdirectiontextfield.setFont(new Font("Arial",Font.BOLD,12));
            catdirectiontextfield.setHorizontalAlignment(JTextField.CENTER);
            
            //distance monitor
            betweendistanceLabel= new JLabel("Cat/Mouse distance");
            betweendistanceLabel.setForeground(Color.BLUE);
            betweendistancefield = new JTextField(4);
            betweendistancefield.setText("");
            betweendistancefield.setBackground(Color.MAGENTA);

            //add to panel
            buttonpanel = new JPanel();
            buttonpanel.setPreferredSize(new Dimension(controlpanel_width,controlpanel_height));
            buttonpanel.setBackground(Color.YELLOW);
            buttonpanel.setLayout(new FlowLayout());
            //row 1, columns 1-3
            buttonpanel.add(startbutton);
            buttonpanel.add(clearbutton);
            buttonpanel.add(quitbutton);

            //row 2, columns 1-3
            buttonpanel.add(refreshlabel);
            buttonpanel.add(refreshtextfield);
            buttonpanel.add(mousespeedlabel);
            buttonpanel.add(mousespeedtextfield);
            buttonpanel.add(mousedirectionlabel);
            buttonpanel.add(mousedirectiontextfield);
            buttonpanel.add(catspeedlabel);
            buttonpanel.add(catspeedtextfield);
            buttonpanel.add(catdirectionlabel);
            buttonpanel.add(catdirectiontextfield);

            //column 4
            buttonpanel.add(betweendistanceLabel);
            buttonpanel.add(betweendistancefield);

            add(buttonpanel,BorderLayout.SOUTH);

            //clock instance handler
            clockhandler = new Clockhandlerclass();

            //refresh timer
            refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/120.0);
            System.out.println("The refresh clock has a delay interval = " + refresh_clock_delay_interval + "ms."); // delete later
            refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);

            //motion timers
            mouse_motion_clock_delay_interval = (int)Math.round(millisecondpersecond/mouse_motion_clock_rate); 
            cat_motion_clock_delay_interval = (int)Math.round(millisecondpersecond/cat_motion_clock_rate); 
            mousemotionclock = new Timer(mouse_motion_clock_delay_interval,clockhandler);
            catmotionclock = new Timer(cat_motion_clock_delay_interval,clockhandler);
            
            //set up the graphics
            graphicspanel.initializeobjectsinpanel(0, 45.0, 300.0, 300.0, 0, 100.0, 100.0);
        
            setVisible(true);
        }

    private class Buttonhandlerclass implements ActionListener
    {    
        public void actionPerformed(ActionEvent event)
        {
            //when start button is pressed
            if(event.getSource() == startbutton)
            {
                System.out.println("The Start button was clicked");
                startbutton.setText("Pause");
                //when on home plate
                if (started == false)
                {
                    //do initialization
                    //refresh
                    tempstring = refreshtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        refresh_clock_rate = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        refresh_clock_rate = 120.0;
                        refreshtextfield.setText("120.0");
                    }

                    //mouse speed
                    tempstring = mousespeedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        mousespeed = Double.parseDouble(tempstring);
                        mouse_speed_pix_per_tic = mousespeed/mouse_motion_clock_rate;
                    }
                    else  
                    {
                        mousespeed = 0.0;
                        mousespeedtextfield.setText("0.0");
                        mouse_speed_pix_per_tic = mousespeed/mouse_motion_clock_rate;
                    }

                    //mouse direction
                    tempstring = mousedirectiontextfield.getText();
                    if(tempstring.length()>0)
                    {
                        mousedirection = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        mousedirection = 0.0;
                        mousedirectiontextfield.setText("0.0");
                    }

                    //cat speed
                    tempstring = catspeedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        catspeed = Double.parseDouble(tempstring);
                        cat_speed_pix_per_tic = catspeed/cat_motion_clock_rate;
                    }
                    else  
                    {
                        catspeed = 200.0;
                        catspeedtextfield.setText("200.0");
                        cat_speed_pix_per_tic = catspeed/cat_motion_clock_rate;
                    }

                    //cat direction
                    tempstring = catdirectiontextfield.getText();
                    if(tempstring.length()>0)
                    {
                        catdirection = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        catdirection = 0.0;
                        catdirectiontextfield.setText("0.0");
                    }
                    
                    //graphics init and set initial state
                    graphicspanel.initializeobjectsinpanel(mouse_speed_pix_per_tic, mousedirection, 300, 300, cat_speed_pix_per_tic, 100, 100);
                    graphicspanel.repaint();
                    started = true;
                    active = false;
                }
                
                //if hasn't been paused
                if(active)
                {
                    //pause
                    refreshclock.stop();
                    mousemotionclock.stop();
                    catmotionclock.stop();
                    startbutton.setText("Resume");
                    active = false;
                }
                else
                {   
                    //else resume
                    refreshclock.start();
                    mousemotionclock.start();
                    catmotionclock.start();
                    startbutton.setText("Pause");
                    active = true;

                    //refresh clock speed
                    tempstring = refreshtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        refresh_clock_rate = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        refresh_clock_rate = 120.0;
                        refreshtextfield.setText("120.0");
                    }

                    //mouse speed
                    tempstring = mousespeedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        mousespeed = Double.parseDouble(tempstring);
                        mouse_speed_pix_per_tic = mousespeed/mouse_motion_clock_rate;
                    }
                    else  
                    {
                        mousespeed = 0.0;
                        mousespeedtextfield.setText("0.0");
                        mouse_speed_pix_per_tic = mousespeed/mouse_motion_clock_rate;
                    }

                    //mouse direction
                    tempstring = mousedirectiontextfield.getText();
                    if(tempstring.length()>0)
                    {
                        mousedirection = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        mousedirection = 0.0;
                        mousespeedtextfield.setText("0.0");
                    }

                    //cat speed
                    tempstring = catspeedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        catspeed = Double.parseDouble(tempstring);
                        cat_speed_pix_per_tic = catspeed/cat_motion_clock_rate;
                    }
                    else  
                    {
                        catspeed = 200.0;
                        catspeedtextfield.setText("200.0");
                        cat_speed_pix_per_tic = catspeed/cat_motion_clock_rate;
                    }

                    //cat direction
                    tempstring = catdirectiontextfield.getText();
                    if(tempstring.length()>0)
                    {
                        catdirection = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        catdirection = 0.0;
                        catspeedtextfield.setText("0.0");
                    }
                    
                    //graphics init and set initial state
                    graphicspanel.initializeobjectsinpanel(mouse_speed_pix_per_tic, mousedirection, graphicspanel.getMouseXPos(), graphicspanel.getMouseYPos(), cat_speed_pix_per_tic, graphicspanel.getCatXPos(), graphicspanel.getCatYPos());
                    graphicspanel.repaint();
                    }
            }
            //if clear button is pressed
            else if(event.getSource() == clearbutton)
            {
                //pause
                refreshclock.stop();
                mousemotionclock.stop();
                catmotionclock.stop();
                active = false;

                //reset start button and state variables
                startbutton.setText("Start");
                started = false;

                //reset text buttons
                refreshtextfield.setText("");
                mousespeedtextfield.setText("");
                mousedirectiontextfield.setText("");
                catspeedtextfield.setText("");
                catdirectiontextfield.setText("");

                //reset the ball and the positions
                betweendistancefield.setText("");

                //upload reset
                graphicspanel.initializeobjectsinpanel(0, 0.0, 300, 300, 0, 100, 100);

                graphicspanel.repaint();
            }
            //if quit button is pressed
            else if(event.getSource() == quitbutton)
            {
                //exit
                System.out.println("Quitting");
                System.exit(0);
            }
            else
            {
                System.out.printf("%s\n","Unkown button error...");
            }
        }
    }

    private class Clockhandlerclass implements ActionListener
    {    
        public void actionPerformed(ActionEvent event)
        {
            //game loop powered by clocks
            boolean animation_continues = false;
            if(event.getSource() == refreshclock)
            {
                //if still going, step the visuals forward
                graphicspanel.repaint();
            }
            //calculate the movement of the ball
            else if(event.getSource() == mousemotionclock)
            {
                animation_continues = graphicspanel.moveMouseBall();
                mousedirectiontextfield.setText(String.format("%.2f", graphicspanel.getMouseDirection()));
                betweendistancefield.setText(String.format("%.2f", graphicspanel.getDistanceBetween()));

                //if cat catches mouse, stop animating
                if(!animation_continues)
                {
                    mousemotionclock.stop();
                    catmotionclock.stop();
                    refreshclock.stop();

                    graphicspanel.repaint();

                    startbutton.setText("Start");
                }
            }
            else if(event.getSource() == catmotionclock)
            {
                animation_continues = graphicspanel.moveCatBall(); 
                catdirectiontextfield.setText(String.format("%.2f", graphicspanel.getCatDirection()));
                betweendistancefield.setText(String.format("%.2f", graphicspanel.getDistanceBetween()));

                //if reached home plate, reset animation
                if(!animation_continues)
                {
                    mousemotionclock.stop();
                    catmotionclock.stop();
                    refreshclock.stop();

                    //ensure that the ballis at home plate 
                    graphicspanel.repaint();

                    startbutton.setText("Start");
                }
            }
            else
            {
            System.out.printf("%s\n","There is a bug in one of the clocks.");
            }
        }
    }
}
