//Brian Montgomery
//CPSC223J Test 1

//UI File

//Code largely adapted from assignment 1

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

public class PopTest1Frame extends JFrame
{
     //panels
     private JPanel panel1;
     private JPanel panel2;
     private JPanel panel3;
     private JPanel panel4;

     //panel 1 vars
     private JPanel titlePanel1;
     private JLabel titleLabel1;
     private JPanel titlePanel2;
     private JLabel titleLabel2;
     private JPanel titlePanel3;
     private JLabel titleLabel3;

     //panel 2 vars
     private JPanel side1Panel;
     private JLabel side1Label;
     private JTextField side1TextField;
     private JPanel side2Panel;
     private JLabel side2Label;
     private JTextField side2TextField;

     //panel 3 vars
     private JPanel hypotenusePanel;
     private JLabel hypotenuseLabel;
     private JLabel hypotenuseLabelFinal;
     private JPanel areaPanel;
     private JLabel areaLabel;
     private JLabel areaLabelFinal;

     //panel 4 vars
     private JButton clearButton;
     private JButton computeButton;
     private JButton quitButton;

     //calculation vars
     private String str1;
     private double side1;
     private double side2;
     private double hypotenuse;
     private double area;
     private int leng;

     //constructor for PopTest1Frame
     public PopTest1Frame()
     {
          //initialize frame
          super("Pop Test 1 Frame");
          setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
          setTitle("Program 1");
     
          //init panel1
          panel1 = new JPanel();
          panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
          panel1.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel1.setBackground(Color.YELLOW);

          //init panel1's objects
          //title Panel 1
          titlePanel1 = new JPanel();
          titlePanel1.setLayout(new FlowLayout());
          titlePanel1.setMaximumSize(new Dimension(600, 60));
          titlePanel1.setBackground(Color.YELLOW);
          titleLabel1 = new JLabel("Welcome to Triangle Computations");
          titleLabel1.setMaximumSize(new Dimension(300, 20));
          //title Panel 2
          titlePanel2 = new JPanel();
          titlePanel2.setLayout(new FlowLayout());
          titlePanel2.setMaximumSize(new Dimension(600, 60));
          titlePanel2.setBackground(Color.YELLOW);
          titleLabel2 = new JLabel("Programmed By Brian Montgomery");
          titleLabel2.setMaximumSize(new Dimension(300, 20));
          //title Panel 3
          titlePanel3 = new JPanel();
          titlePanel3.setLayout(new FlowLayout());
          titlePanel3.setMaximumSize(new Dimension(600, 60));
          titlePanel3.setBackground(Color.YELLOW);
          titleLabel3 = new JLabel("All Triangles are Right Triangles");
          titleLabel3.setMaximumSize(new Dimension(300, 20));

          //set panel1's objects
          titleLabel1.setForeground(Color.BLUE);
          titlePanel1.add(titleLabel1);
          titleLabel2.setForeground(Color.BLUE);
          titlePanel2.add(titleLabel2);
          titleLabel3.setForeground(Color.BLUE);
          titlePanel3.add(titleLabel3);

          //put panel1 together
          panel1.add(titlePanel1);
          panel1.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel1.add(titlePanel2);
          panel1.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel1.add(titlePanel3);
          add(panel1);  
     
          //init panel2
          panel2 = new JPanel();
          panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
          panel2.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel2.setBackground(Color.BLACK);

          //init panel2's objects
          //side2Panel
          side1Panel = new JPanel();
          side1Panel.setBackground(Color.BLACK);
          side1Panel.setLayout(new FlowLayout());
          side1Panel.setMaximumSize(new Dimension(600, 60));
          side1Label = new JLabel("Input Side 1");
          side1TextField = new JTextField(40);
          side1TextField.setMaximumSize(new Dimension(300, 20));
          //side2Panel
          side2Panel = new JPanel();
          side2Panel.setBackground(Color.BLACK);
          side2Panel.setLayout(new FlowLayout());
          side2Panel.setMaximumSize(new Dimension(600, 60));
          side2Label = new JLabel("Input Side 2");
          side2TextField = new JTextField(40);
          side2TextField.setMaximumSize(new Dimension(300, 20));

          //put panels together
          //side1Panel
          side1Label.setForeground(Color.WHITE);
          side1Panel.add(side1Label);
          side1Panel.add(side1TextField);
          //side2Panel
          side2Label.setForeground(Color.WHITE);
          side2Panel.add(side2Label);
          side2Panel.add(side2TextField);

          //put panel 2 together
          panel2.add(side1Panel);
          panel2.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel2.add(side2Panel);
          add(panel2);  

          //init panel3
          panel3 = new JPanel();
          panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
          panel3.add(new Box.Filler(new Dimension(600, 0), new Dimension(600, 0), new Dimension(600, 0)));
          panel3.setBackground(Color.GREEN);

          //init panel3's objects
          //hypotenuse
          hypotenusePanel = new JPanel();
          hypotenusePanel.setBackground(Color.GREEN);
          hypotenusePanel.setLayout(new FlowLayout());
          hypotenusePanel.setMaximumSize(new Dimension(600, 60));
          hypotenuseLabel = new JLabel("Hypotenuse: ");
          hypotenuseLabelFinal = new JLabel("");
          //area
          areaPanel = new JPanel();
          areaPanel.setBackground(Color.GREEN);
          areaPanel.setLayout(new FlowLayout());
          areaPanel.setMaximumSize(new Dimension(600, 60));
          areaLabel = new JLabel("Area: ");
          areaLabelFinal = new JLabel("");
     
          //put panels together
          //hypotenusePanel
          hypotenuseLabel.setForeground(Color.BLACK);
          hypotenuseLabelFinal.setForeground(Color.BLACK);
          hypotenusePanel.add(hypotenuseLabel);
          hypotenusePanel.add(hypotenuseLabelFinal);
          //areaPanel
          areaLabel.setForeground(Color.BLACK);
          areaLabelFinal.setForeground(Color.BLACK);
          areaPanel.add(areaLabel);
          areaPanel.add(areaLabelFinal);

          //put panel3 together
          panel3.add(hypotenusePanel);
          panel3.add(new Box.Filler(new Dimension(600, 7), new Dimension(600, 7), new Dimension(600, 7)));
          panel3.add(areaPanel);
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
                    side1TextField.setText("");
                    side2TextField.setText("");
                    
                    //clear output Labels
                    hypotenuseLabelFinal.setText("");
                    areaLabelFinal.setText("");
               }
               else if(event.getSource() == computeButton)
               {
                    //get side1 from textfield
                    str1 = side1TextField.getText();
                    leng = str1.length();
                    if(leng == 0)
                    {
                         side1 = 0; 
                    }
                    else
                    {
                         side1 = Double.parseDouble(str1);
                    }

                    //get side2 from textfield
                    str1 = side2TextField.getText();
                    leng = str1.length();
                    if(leng == 0)
                    {
                         side2 = 0; 
                    }
                    else
                    {
                         side2 = Double.parseDouble(str1);
                    }
                    if(side2 <= 0 || side1 <= 0){
                         hypotenuseLabelFinal.setText("Error - Both sides must be positive numbers");
                         areaLabelFinal.setText("Error - Both sides must be positive numbers");
                         return;
                    }
                    
                    //calculate
                    hypotenuse = PopTest1Functions.compute_hypotenuse(side1,side2);
                    area = PopTest1Functions.compute_area(side1,side2);

                    //convert back into text for display
                    DecimalFormat df = new DecimalFormat("0.00##");
                    hypotenuseLabelFinal.setText(df.format(hypotenuse));
                    hypotenuseLabelFinal.setForeground(Color.BLACK);
                    areaLabelFinal.setText(df.format(area));
                    areaLabelFinal.setForeground(Color.BLACK);
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