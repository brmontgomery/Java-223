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
  //File name: Assignment3Interface.java
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

public class Assignment3Interface extends JFrame
{   
    private final int UI_width = 1280;
    private final int UI_height = 720;

    //Variables for the speed of the ball  
    private double runner_speed_pix_per_tic;                 

    //Title panel objects
    private JPanel titlepanel;
    private final int titlepanel_width = UI_width;
    private final int titlepanel_height = 50;
    private JLabel titlelabel;

    //Control Panel Objects
    private JPanel buttonpanel;
    private final int controlpanel_width = UI_width;
    private final int controlpanel_height = 100;
    private JLabel refreshlabel;
    private JTextField refreshtextfield;
    private JLabel speedlabel;
    private JTextField speedtextfield;
    private JLabel directionlabel;
    private JTextField directiontextfield;
    private JButton startbutton;
    private JButton clearbutton;
    private JButton quitbutton;

    private JLabel positionLabel;
    private JLabel xPoslabel;
    private JTextField xPostextfield;
    private JLabel yPoslabel;
    private JTextField yPostextfield;

    //Graphics panel Objects
    private Assignment3MotionPanel graphicspanel;
    private final int graphicspanel_width = UI_width;
    private final int graphicspanel_height = UI_height - titlepanel_height - controlpanel_height;

    //Line segment components
    private String tempstring;
    private double speednumeric;
    private double directionNumeric;

    //Clock vars
    private Timer refreshclock;
    private Timer motionclock;
    private Buttonhandlerclass buttonhandler;
    private Clockhandlerclass clockhandler;
    private double refresh_clock_rate;
    private int refresh_clock_delay_interval;
    private final double motion_clock_rate = 99.873;
    private int motion_clock_delay_interval;
    private final double millisecondpersecond = 1000.0;       
    
    //program end monitor variable
    private boolean active = false;
    private boolean started = false;                    

    public Assignment3Interface()                      
        {
            //initial setup
            super("Straight Line Ball Bounce");
            setLayout(new BorderLayout());
            setSize(UI_width,UI_height);
            System.out.println("Welcome to Straight Line Ball Bounce");
            setLocationRelativeTo(null);
            setVisible(false);
            setResizable(false);

            runner_speed_pix_per_tic = 0.0;
            buttonhandler = new Buttonhandlerclass();

            //Setting up the three panels
            //Panel 1: Title Panel
            titlelabel = new JLabel("Straight Line Ball Bounce");
            titlepanel = new JPanel();
            titlepanel.setPreferredSize( new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.add(titlelabel);
            add(titlepanel,BorderLayout.NORTH);

            //Panel 2: Graphics Panel
            graphicspanel = new Assignment3MotionPanel();
            graphicspanel.setBackground(Color.BLUE);
            graphicspanel.setSize(graphicspanel_width,graphicspanel_height);
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
            refreshtextfield = new JTextField(10);
            refreshtextfield.setText("120.0");
            refreshtextfield.setBackground(Color.GREEN);
            refreshtextfield.setFont(new Font("Arial",Font.BOLD,12));
            refreshtextfield.setHorizontalAlignment(JTextField.CENTER);
            
            //speed monitor
            speedlabel = new JLabel("Speed (pix/sec");
            speedlabel.setForeground(Color.BLUE);
            speedtextfield = new JTextField(10);
            speedtextfield.setText("200.0");
            speedtextfield.setBackground(Color.GREEN);
            speedtextfield.setFont(new Font("Arial",Font.BOLD,12));
            speedtextfield.setHorizontalAlignment(JTextField.CENTER);

            //direction monitor
            directionlabel = new JLabel("Direction");
            directionlabel.setForeground(Color.BLUE);
            directiontextfield = new JTextField(10);
            directiontextfield.setText("45.0");
            directiontextfield.setBackground(Color.GREEN);
            directiontextfield.setFont(new Font("Arial",Font.BOLD,12));
            directiontextfield.setHorizontalAlignment(JTextField.CENTER);
            
            //position monitor
            positionLabel = new JLabel("Ball Location");
            xPoslabel = new JLabel("X = ");
            xPoslabel.setForeground(Color.BLUE);
            xPostextfield = new JTextField(4);
            xPostextfield.setText("300.0");
            xPostextfield.setBackground(Color.MAGENTA);
            yPoslabel = new JLabel("Y = ");
            yPoslabel.setForeground(Color.BLUE);
            yPostextfield = new JTextField(4);
            yPostextfield.setText("300.0");
            yPostextfield.setBackground(Color.MAGENTA);

            //add to panel
            buttonpanel = new JPanel();
            buttonpanel.setSize(controlpanel_width,controlpanel_height);
            buttonpanel.setBackground(Color.YELLOW);
            buttonpanel.setLayout(new FlowLayout());
            //row 1, columns 1-3
            buttonpanel.add(startbutton);
            buttonpanel.add(clearbutton);
            buttonpanel.add(quitbutton);

            //row 2, columns 1-3
            buttonpanel.add(speedlabel);
            buttonpanel.add(speedtextfield);
            buttonpanel.add(refreshlabel);
            buttonpanel.add(refreshtextfield);
            buttonpanel.add(speedlabel);
            buttonpanel.add(speedtextfield);
            buttonpanel.add(directionlabel);
            buttonpanel.add(directiontextfield);

            //column 4
            buttonpanel.add(positionLabel);
            buttonpanel.add(xPoslabel);
            buttonpanel.add(xPostextfield);
            buttonpanel.add(yPoslabel);
            buttonpanel.add(yPostextfield);

            add(buttonpanel,BorderLayout.SOUTH);

            //clock instance handler
            clockhandler = new Clockhandlerclass();

            //refresh timer
            refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/120.0);
            System.out.println("The refresh clock has a delay interval = " + refresh_clock_delay_interval + "ms."); // delete later
            refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);

            //motion timer
            motion_clock_delay_interval = (int)Math.round(millisecondpersecond/motion_clock_rate); 
            System.out.println("The motion clock has a delay interval = " + motion_clock_delay_interval + "ms."); //delete later
            motionclock = new Timer(motion_clock_delay_interval,clockhandler);
            
            //set up the graphics
            graphicspanel.initializeobjectsinpanel(0, 45.0, 300.0, 300.0);

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

                    //speed
                    tempstring = speedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        speednumeric = Double.parseDouble(tempstring);
                        runner_speed_pix_per_tic = speednumeric/motion_clock_rate;
                    }
                    else  
                    {
                        speednumeric = 20.0;
                        speedtextfield.setText("200.0");
                        runner_speed_pix_per_tic = speednumeric/motion_clock_rate;
                    }

                    tempstring = directiontextfield.getText();
                    if(tempstring.length()>0)
                    {
                        directionNumeric = Double.parseDouble(tempstring);
                    }
                    else  
                    {
                        directionNumeric = 45.0;
                        speedtextfield.setText("45.0");
                    }
                    
                    //graphics init and set initial state
                    graphicspanel.initializeobjectsinpanel(runner_speed_pix_per_tic, directionNumeric, 300, 300);
                    graphicspanel.repaint();
                    started = true;
                    active = false;
                }
                
                //if hasn't been paused
                if(active)
                    {
                        //pause
                        refreshclock.stop();
                        motionclock.stop();
                        startbutton.setText("Resume");
                        active = false;
                    }
                else
                    {   
                        //else resume
                        refreshclock.start();
                        motionclock.start();
                        startbutton.setText("Pause");
                        active = true;
                    }
            }
            //if clear button is pressed
            else if(event.getSource() == clearbutton)
            {
                //pause
                refreshclock.stop();
                motionclock.stop();
                active = false;

                //reset start button and state variables
                startbutton.setText("Start");
                started = false;

                //reset text buttons
                refreshtextfield.setText("120.0");
                speedtextfield.setText("200.0");
                directiontextfield.setText("45.0");

                //reset the ball and the positions
                xPostextfield.setText("300.0");
                yPostextfield.setText("300.0");

                //upload reset
                graphicspanel.initializeobjectsinpanel(runner_speed_pix_per_tic, 45.0, 300, 300);

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
            else if(event.getSource() == motionclock)
            {
                animation_continues = graphicspanel.moveball();
                xPostextfield.setText(String.format("%.2f", graphicspanel.getXPos()));
                yPostextfield.setText(String.format("%.2f", graphicspanel.getYPos()));
                directiontextfield.setText(String.format("%.2f", graphicspanel.getDirection()));

                //if reached home plate, reset animation
                if(!animation_continues)
                {
                    motionclock.stop();
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
