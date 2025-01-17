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
  //File name: FinalInterface.java
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

public class FinalInterface extends JFrame
{   
    //programmer values
    private final double refreshrate = 120.0;
	private final double earthclockrate = 99.873;
	private final int earthdiameter = 30;
	private final int sundiameter = 100;
	private final double earthorbitradius = 100;  //==>  call it R (earth orbit radius)

    private final int UI_width = 1280;
    private final int UI_height = 720;

    //Variables for the speed of the ball  
    private double earth_speed_pix_per_tic;                

    //Title panel objects
    private JPanel titlepanel;
    private final int titlepanel_width = UI_width;
    private final int titlepanel_height = 50;
    private JLabel titlelabel;

    //Control Panel Objects
    private JPanel buttonpanel;
    private final int controlpanel_width = UI_width;
    private final int controlpanel_height = 50;
    private JLabel earthpeedlabel;
    private JTextField earthspeedtextfield;
    private JLabel earthxposlabel;
    private JTextField earthxpostextfield;
    private JLabel earthyposlabel;
    private JTextField earthypostextfield;
    private JButton startbutton;
    private JButton resetbutton;
    private JButton quitbutton;

    //Graphics panel Objects
    private FinalMotionPanel graphicspanel;
    private final int graphicspanel_width = UI_width;
    private final int graphicspanel_height = UI_height - titlepanel_height - controlpanel_height;

    //Line segment components
    private String tempstring;
    private double earthspeed;
    private double sunxPos = 700.0;
    private double sunyPos = 300.0;

    //Clock vars
    private Timer refreshclock;
    private Timer earthmotionclock;
    private Buttonhandlerclass buttonhandler;
    private Clockhandlerclass clockhandler;
    private int refresh_clock_delay_interval;
    private int earth_motion_clock_delay_interval;
    private final double millisecondpersecond = 1000.0;       
    
    //program end monitor variable
    private boolean active = false;
    private boolean started = false;                    

    public FinalInterface()                      
        {
            //initial setup
            super("Earth Orbit");
            setLayout(new BorderLayout());
            setSize(UI_width,UI_height);
            System.out.println("Welcome to Earth Orbit");
            System.out.println(graphicspanel_width);
            System.out.println(graphicspanel_height);
            setLocationRelativeTo(null);
            setVisible(false);
            setResizable(false);

            earth_speed_pix_per_tic = 0.0;
            buttonhandler = new Buttonhandlerclass();

            //Setting up the three panels
            //Panel 1: Title Panel
            titlelabel = new JLabel("Earth Orbit");
            titlepanel = new JPanel();
            titlepanel.setPreferredSize( new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.setMinimumSize(new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.setMaximumSize(new Dimension(titlepanel_width,titlepanel_height));
            titlepanel.add(titlelabel);
            add(titlepanel,BorderLayout.NORTH);

            //Panel 2: Graphics Panel
            graphicspanel = new FinalMotionPanel();
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
            resetbutton = new JButton("Reset");
            resetbutton.addActionListener(buttonhandler);

            //quit button
            quitbutton = new JButton("Exit");
            quitbutton.setBackground(Color.RED);
            quitbutton.addActionListener(buttonhandler);
            
            //earth speed monitor
            earthpeedlabel = new JLabel("Earth Speed (pix/sec");
            earthpeedlabel.setForeground(Color.BLUE);
            earthspeedtextfield = new JTextField(6);
            earthspeedtextfield.setText("40.0");
            earthspeedtextfield.setBackground(Color.GREEN);
            earthspeedtextfield.setFont(new Font("Arial",Font.BOLD,12));
            earthspeedtextfield.setHorizontalAlignment(JTextField.CENTER);

            //cat speed monitor
            earthxposlabel = new JLabel("Earth X Position (Java Coords)");
            earthxposlabel.setForeground(Color.BLUE);
            earthxpostextfield = new JTextField(6);
            earthxpostextfield.setText(String.format("%.2f", sunxPos + earthorbitradius));
            earthxpostextfield.setBackground(Color.GREEN);
            earthxpostextfield.setFont(new Font("Arial",Font.BOLD,12));
            earthxpostextfield.setHorizontalAlignment(JTextField.CENTER);

            //cat direction monitor
            earthyposlabel = new JLabel("Earth Y Position (Java Coords)");
            earthyposlabel.setForeground(Color.BLUE);
            earthypostextfield = new JTextField(6);
            earthypostextfield.setText("400.0");
            earthypostextfield.setBackground(Color.GREEN);
            earthypostextfield.setFont(new Font("Arial",Font.BOLD,12));
            earthypostextfield.setHorizontalAlignment(JTextField.CENTER);

            //add to panel
            buttonpanel = new JPanel();
            buttonpanel.setPreferredSize(new Dimension(controlpanel_width,controlpanel_height));
            buttonpanel.setBackground(Color.YELLOW);
            buttonpanel.setLayout(new FlowLayout());
            //row 1, columns 1-3
            buttonpanel.add(startbutton);
            buttonpanel.add(resetbutton);
            buttonpanel.add(quitbutton);

            //row 2, columns 1-3
            buttonpanel.add(earthpeedlabel);
            buttonpanel.add(earthspeedtextfield);
            buttonpanel.add(earthxposlabel);
            buttonpanel.add(earthxpostextfield);
            buttonpanel.add(earthyposlabel);
            buttonpanel.add(earthypostextfield);

            add(buttonpanel,BorderLayout.SOUTH);

            //clock instance handler
            clockhandler = new Clockhandlerclass();

            //refresh timer
            refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/refreshrate);
            System.out.println("The refresh clock has a delay interval = " + refresh_clock_delay_interval + "ms."); // delete later
            refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);

            //motion timers
            earth_motion_clock_delay_interval = (int)Math.round(millisecondpersecond/earthclockrate); 
            earthmotionclock = new Timer(earth_motion_clock_delay_interval,clockhandler);
            
            //set up the graphics
            graphicspanel.initializeobjectsinpanel(((earth_speed_pix_per_tic)/earthorbitradius), true, sunxPos, sunyPos - earthorbitradius, earthorbitradius, sunxPos, sunyPos, earthdiameter/2, sundiameter/2, sunxPos, sunyPos - (2 * earthorbitradius));
        
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
                    //earth speed
                    tempstring = earthspeedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        earthspeed = Double.parseDouble(tempstring);
                        earth_speed_pix_per_tic = earthspeed/earthclockrate;
                    }
                    else  
                    {
                        earthspeed = 40.0;
                        earthspeedtextfield.setText("40.0");
                        earth_speed_pix_per_tic = earthspeed/earthclockrate;
                    }
                    //graphics init and set initial state
                    graphicspanel.initializeobjectsinpanel(((earth_speed_pix_per_tic)/earthorbitradius), false, graphicspanel.getEarthXPos(), graphicspanel.getEarthYPos(), earthorbitradius, sunxPos, sunyPos, earthdiameter/2, sundiameter/2, graphicspanel.getMarsXPos(), graphicspanel.getMarsYPos());
                    graphicspanel.repaint();
                    started = true;
                    active = false;
                }
                
                //if hasn't been paused
                if(active)
                {
                    //pause
                    refreshclock.stop();
                    earthmotionclock.stop();
                    startbutton.setText("Resume");
                    active = false;
                }
                else
                {   
                    //else resume
                    refreshclock.start();
                    earthmotionclock.start();
                    startbutton.setText("Pause");
                    active = true;

                    //mouse speed
                    tempstring = earthspeedtextfield.getText();
                    if(tempstring.length()>0)
                    {
                        earthspeed = Double.parseDouble(tempstring);
                        earth_speed_pix_per_tic = earthspeed/earthclockrate;
                    }
                    else  
                    {
                        earthspeed = 40.0;
                        earthspeedtextfield.setText("40.0");
                        earth_speed_pix_per_tic = earthspeed/earthclockrate;
                    }
                    
                    //graphics init and set initial state
                    graphicspanel.initializeobjectsinpanel(((earth_speed_pix_per_tic)/earthorbitradius), false, graphicspanel.getEarthXPos(), graphicspanel.getEarthYPos(), earthorbitradius, sunxPos, sunyPos, earthdiameter/2, sundiameter/2, graphicspanel.getMarsXPos(), graphicspanel.getMarsYPos());
                    graphicspanel.repaint();
                }
            }
            //if clear button is pressed
            else if(event.getSource() == resetbutton)
            {
                //pause
                refreshclock.stop();
                earthmotionclock.stop();
                active = false;

                //reset start button and state variables
                startbutton.setText("Start");
                started = false;

                //reset text buttons
                earthspeedtextfield.setText("40.0");
                earthxpostextfield.setText(String.format("%.2f", sunxPos + earthorbitradius));
                earthxpostextfield.setText("400.0");

                //upload reset
                graphicspanel.initializeobjectsinpanel(((earth_speed_pix_per_tic)/earthorbitradius), true, sunxPos, sunyPos - earthorbitradius, earthorbitradius, sunxPos, sunyPos, earthdiameter/2, sundiameter/2, sunxPos, sunyPos - (2 * earthorbitradius));

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
            else if(event.getSource() == earthmotionclock)
            {
                animation_continues = graphicspanel.moveEarthBall();

                //if cat catches mouse, stop animating
                if(!animation_continues)
                {
                    earthmotionclock.stop();
                    refreshclock.stop();

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
