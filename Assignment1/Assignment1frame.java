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
  //File name: Assignment1frame.java
  //Compile : javac Assignment1frame.java
  //Purpose: This class defines the user interface
  //This module (class) is called from the Assignment1 class.

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.text.DecimalFormat;

public class Assignment1frame extends JFrame
{
     //panels
     private JPanel panel1;
     private JPanel panel2;
     private JPanel panel3;
     private JPanel panel4;

     //panel 1 vars
     private JPanel businessNamePanel;
     private JLabel businessNameLabel;
     private JPanel payrollPanel;
     private JLabel payrollLabel;

     //panel 2 vars
     private JPanel empNamePanel;
     private JLabel empNameLabel;
     private JTextField empNameTextField;
     private JPanel hoursWorkedPanel;
     private JLabel hoursWorkedLabel;
     private JTextField hoursWorkedTextField;
     private JPanel payRatePanel;
     private JLabel payRateLabel;
     private JTextField payRateTextField;

     //panel 3 vars
     private JPanel empNamePanel2;
     private JLabel empNameLabel2;
     private JLabel empNameLabelFinal;
     private JPanel regularPayPanel;
     private JLabel regularPayLabel;
     private JLabel regularPayLabelFinal;
     private JPanel overtimePayPanel;
     private JLabel overtimePayLabel;
     private JLabel overtimePayLabelFinal;
     private JPanel grossPayPanel;
     private JLabel grossPayLabel;
     private JLabel grossPayLabelFinal;

     //panel 4 vars
     private JButton clearButton;
     private JButton computeButton;
     private JButton quitButton;

     //calculation vars
     private String str1;
     private float hours;
     private float payrate;
     private float overtimePay;
     private float regularPay;
     private float grossPay;
     private int leng;

     //constructor for Assignment1Frame
     public Assignment1frame()
     {
          //initialize frame
          super("Assignment 1 Frame");
          setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
          setTitle("Program 1");
     
          //init panel1
          panel1 = new JPanel();
          panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
          panel1.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel1.setBackground(Color.YELLOW);

          //init panel1's objects
          //businessNamePanel
          businessNamePanel = new JPanel();
          businessNamePanel.setLayout(new FlowLayout());
          businessNamePanel.setMaximumSize(new Dimension(600, 60));
          businessNamePanel.setBackground(Color.YELLOW);
          businessNameLabel = new JLabel("Montgomery Monkey Business");
          businessNameLabel.setMaximumSize(new Dimension(300, 20));
          //payrollPanel
          payrollPanel = new JPanel();
          payrollPanel.setLayout(new FlowLayout());
          payrollPanel.setMaximumSize(new Dimension(600, 60));
          payrollPanel.setBackground(Color.YELLOW);
          payrollLabel = new JLabel("Payroll System");
          payrollLabel.setMaximumSize(new Dimension(300, 20));

          //set panel1's objects
          businessNameLabel.setForeground(Color.BLUE);
          businessNamePanel.add(businessNameLabel);
          payrollLabel.setForeground(Color.BLUE);
          payrollPanel.add(payrollLabel);

          //put panel1 together
          panel1.add(businessNamePanel);
          panel1.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel1.add(payrollPanel);
          add(panel1);  
     
          //init panel2
          panel2 = new JPanel();
          panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
          panel2.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel2.setBackground(Color.BLACK);

          //init panel2's objects
          //empNamePanel
          empNamePanel = new JPanel();
          empNamePanel.setBackground(Color.BLACK);
          empNamePanel.setLayout(new FlowLayout());
          empNamePanel.setMaximumSize(new Dimension(600, 60));
          empNameLabel = new JLabel("Employee Name");
          empNameTextField = new JTextField(40);
          empNameTextField.setMaximumSize(new Dimension(300, 20));
          //hoursWorkedPanel
          hoursWorkedPanel = new JPanel();
          hoursWorkedPanel.setBackground(Color.BLACK);
          hoursWorkedPanel.setLayout(new FlowLayout());
          hoursWorkedPanel.setMaximumSize(new Dimension(600, 60));
          hoursWorkedLabel = new JLabel("Hours Worked");
          hoursWorkedTextField = new JTextField(40);
          hoursWorkedTextField.setMaximumSize(new Dimension(300, 20));
          //payRatePanel
          payRatePanel = new JPanel();
          payRatePanel.setBackground(Color.BLACK);
          payRatePanel.setLayout(new FlowLayout());
          payRatePanel.setMaximumSize(new Dimension(600, 60));
          payRateLabel = new JLabel("Hourly Pay Rate");
          payRateTextField = new JTextField(40);
          payRateTextField.setMaximumSize(new Dimension(300, 20));

          //put panels together
          //empNamePanel
          empNameLabel.setForeground(Color.WHITE);
          empNamePanel.add(empNameLabel);
          empNamePanel.add(empNameTextField);
          //hoursWorkedPanel
          hoursWorkedLabel.setForeground(Color.WHITE);
          hoursWorkedPanel.add(hoursWorkedLabel);
          hoursWorkedPanel.add(hoursWorkedTextField);
          //payRatePanel
          payRateLabel.setForeground(Color.WHITE);
          payRatePanel.add(payRateLabel);
          payRatePanel.add(payRateTextField);

          //put panel 2 together
          panel2.add(empNamePanel);
          panel2.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel2.add(hoursWorkedPanel);
          panel2.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel2.add(payRatePanel);
          add(panel2);  

          //init panel3
          panel3 = new JPanel();
          panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
          panel3.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel3.setBackground(Color.GREEN);

          //init panel3's objects
          //empname
          empNamePanel2 = new JPanel();
          empNamePanel2.setBackground(Color.GREEN);
          empNamePanel2.setLayout(new FlowLayout());
          empNamePanel2.setMaximumSize(new Dimension(600, 60));
          empNameLabel2 = new JLabel("Name of Employee: ");
          empNameLabelFinal = new JLabel("");
          //regularPay
          regularPayPanel = new JPanel();
          regularPayPanel.setBackground(Color.GREEN);
          regularPayPanel.setLayout(new FlowLayout());
          regularPayPanel.setMaximumSize(new Dimension(600, 60));
          regularPayLabel = new JLabel("Regular Pay: ");
          regularPayLabelFinal = new JLabel("");
          //overtimePanel
          overtimePayPanel = new JPanel();
          overtimePayPanel.setBackground(Color.GREEN);
          overtimePayPanel.setLayout(new FlowLayout());
          overtimePayPanel.setMaximumSize(new Dimension(600, 60));
          overtimePayLabel = new JLabel("Overtime Pay: ");
          overtimePayLabelFinal = new JLabel("");
          //grossPay
          grossPayPanel = new JPanel();
          grossPayPanel.setBackground(Color.GREEN);
          grossPayPanel.setLayout(new FlowLayout());
          grossPayPanel.setMaximumSize(new Dimension(600, 60));
          grossPayLabel = new JLabel("Gross Pay: ");
          grossPayLabelFinal = new JLabel("");
     
          //put panels together
          //empNamePanel2
          empNameLabel2.setForeground(Color.BLACK);
          empNameLabelFinal.setForeground(Color.BLACK);
          empNamePanel2.add(empNameLabel2);
          empNamePanel2.add(empNameLabelFinal);
          //regularPayPanel
          regularPayLabel.setForeground(Color.BLACK);
          regularPayLabelFinal.setForeground(Color.BLACK);
          regularPayPanel.add(regularPayLabel);
          regularPayPanel.add(regularPayLabelFinal);
          //overtimePayPanel
          overtimePayLabel.setForeground(Color.BLACK);
          overtimePayLabelFinal.setForeground(Color.BLACK);
          overtimePayPanel.add(overtimePayLabel);
          overtimePayPanel.add(overtimePayLabelFinal);
          //grossPayPanel
          grossPayLabel.setForeground(Color.BLACK);
          grossPayLabelFinal.setForeground(Color.BLACK);
          grossPayPanel.add(grossPayLabel);
          grossPayPanel.add(grossPayLabelFinal);

          //put panel3 together
          panel3.add(empNamePanel2);
          panel3.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel3.add(regularPayPanel);
          panel3.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel3.add(overtimePayPanel);
          panel3.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel3.add(grossPayPanel);
          add(panel3); 

          //panel 4 settings
          panel4 = new JPanel();
          panel4.setLayout(new FlowLayout());
          panel1.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel4.setBackground(Color.PINK);

          //init and add panel 4 buttons
          clearButton = new JButton("Clear");
          clearButton.setBackground(Color.WHITE);
          panel4.add(clearButton);
          computeButton = new JButton("Compute");
          computeButton.setBackground(Color.CYAN);
          panel4.add(computeButton);
          quitButton = new JButton("Quit");
          quitButton.setBackground(Color.WHITE);
          panel4.add(quitButton);
          add(panel4);
     
          //add button handlers
          buttonhandler myhandler = new buttonhandler();
          clearButton.addActionListener(myhandler);
          computeButton.addActionListener(myhandler);
          quitButton.addActionListener(myhandler);
          setLocationRelativeTo(null);  
     }


     private class buttonhandler implements ActionListener
     {
          public void actionPerformed(ActionEvent event)
          {
               if(event.getSource() == clearButton)
               {
                    //clear all text Fields
                    empNameTextField.setText("");
                    hoursWorkedTextField.setText("");
                    payRateTextField.setText("");
                    
                    //clear output Labels
                    empNameLabelFinal.setText("");
                    regularPayLabelFinal.setText("");
                    overtimePayLabelFinal.setText("");
                    grossPayLabelFinal.setText("");
               }
               else if(event.getSource() == computeButton)
               {
                    //get the name to use
                    str1 = empNameTextField.getText();
                    leng = str1.length();
                    if(leng == 0)
                    {
                         empNameLabelFinal.setText("No name given");
                    }
                    else
                    {
                         empNameLabelFinal.setText(empNameTextField.getText());
                    }

                    //get hours worked from textfield
                    str1 = hoursWorkedTextField.getText();
                    leng = str1.length();
                    if(leng == 0)
                    {
                         hours = 0; 
                    }
                    else
                    {
                         hours = Float.parseFloat(str1);
                    }

                    //get payrate worked from textfield
                    str1 = payRateTextField.getText();
                    leng = str1.length();
                    if(leng == 0)
                    {
                         payrate = 0; 
                    }
                    else
                    {
                         payrate = Float.parseFloat(str1);
                    }
                    
                    //calculate
                    overtimePay = Assignment1Functions.getOvertimePay(hours,payrate);
                    regularPay = Assignment1Functions.getRegularPay(hours,payrate);
                    grossPay = Assignment1Functions.getGrossPay(regularPay,overtimePay);

                    //convert back into text for display
                    DecimalFormat df = new DecimalFormat("0.00##");
                    overtimePayLabelFinal.setText(df.format(overtimePay));
                    overtimePayLabelFinal.setForeground(Color.BLACK);
                    regularPayLabelFinal.setText(df.format(regularPay));
                    regularPayLabelFinal.setForeground(Color.BLACK);
                    grossPayLabelFinal.setText(df.format(grossPay));
                    grossPayLabelFinal.setForeground(Color.BLACK);
               }
               else if(event.getSource() == quitButton)
               {
                    //quiting the program
                         System.out.print("Quitting.\n");
                         quitButton.setEnabled(false);
                         clearButton.setEnabled(false);
                         computeButton.setEnabled(false);
                         System.exit(0);
               }
               else
               {
                    System.out.println("Unknown error");
               }
          }
     }
}