package start;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Warning {
    public static void main(String args[]) {
        
    	
    	JPanel middlePanel = new JPanel ();
    	JPanel south = new JPanel ();
        middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "WARNING!!" ) );

        // create the middle panel components

        JTextArea display = new JTextArea ( 15,30 );
        display.setBackground(Color.RED);
        display.setText("1.1 William Hill RacingTV is only available to William Hill account holders, "
        		+ "subject to the provisions of the remaining terms and conditions below.\n"
        		+ "1.2 Race viewing is available to UK and Ireland residents only and may not \n"
        		+ "be available to customers using foreign configured IP addresses. Please contact "
        		+ "your Internet Service Provider for more information.\n"
        		+ "1.3 William Hill RacingTV is available on a race by race basis based  \n"
        		+ "on you placing at least a £1.00 single bet or a £0.50 each-way single "
        		+ "bet (total stake £1.00), or at least a £2.00 double bet or a £1.00 each-way "
        		+ "double bet \n"
        		+ " (total stake £2.00), or at least a £3.00 treble bet or a £1.50 each-way \n"
        		+ "treble bet (total stake £3.00) \n"
        		+ "on the race(s) you wish to view. For multiple bet types the equivalent\n"
        		+ " stake of £1.00 per event \n"
        		+ "must be spent to view each additional race.\n"
        		+ "1.4 Antepost, totePool and special bets do not apply.\n"
        		+ "1.5 However, William Hill reserve the right to alter these conditions \n"
        		+ "for access or introduce a fee, subscription or other charge for access \n"
        		+ "to William Hill RacingTV in \n"
        		+ "the future. You will be made aware of the introduction of any charge or \n"
        		+ "condition before it is introduced.\n"
        		+ "1.6 William Hill may refuse access to William Hill RacingTV to any \n "
        		+ "customer \n"
        		+ "at its absolute discretion.\n"
        		+ "1.7 By accessing William Hill RacingTV you accept these terms and conditions.\n"
        		+ "");
        display.setEditable ( false ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        final JCheckBox tf = new JCheckBox("Sono consapevole"); // accepts upto 10 characters
        final JButton send = new JButton("Avanti");
        
        send.setEnabled(false);

        //Add Textarea in to middle panel
        middlePanel.add ( scroll );
        south.add ( tf );
        south.add ( send);
        
        
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
              boolean selected = abstractButton.getModel().isSelected();
              if(selected==true) {
            	  send.setEnabled(true);
            	  
              }
              else;
            
            }};
        
       /* send.setEnabled(false);
        if (( tf.isSelected()==true)) {
            send.setEnabled(true);
       } else {
            // Your code if the box is not checked
       }*/
        
        
       // frame.getContentPane().add(BorderLayout.SOUTH, );

        // My code
        tf.addActionListener(actionListener);
        JFrame frame = new JFrame ("WARNING");
        frame.add ( middlePanel ,BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);
        frame.pack ();
        frame.setLocationRelativeTo ( null );
        frame.setVisible ( true );
    }
}
    	
    	

       
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//Creating the Frame
        /*final JFrame frame = new JFrame("WARNING");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        Color colore = Color.RED;
        Color colore2 = Color.WHITE;*/
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*final JTextField t1 = new JTextField("IL GIOCO CREA DIPENDENZA STAI ATTENTO");
         
        
        t1.setBackground(colore);
        t1.setSelectedTextColor(colore2);
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setEditable(false);
        t1.setBounds(30, 30, 300, 200);  
          
         
        frame.add(t1);   
        
        JPanel middlePanel = new JPanel ();
        middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "ATTENZIONE!!" ) );

        // create the middle panel components

        JTextArea display = new JTextArea ( 16,58);
        display.setBackground(colore);
        display.setSelectedTextColor(colore2);
        display.setBounds(30, 30, 300, 200);
        display.setEditable ( false ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        //Add Textarea in to middle panel
        middlePanel.add ( scroll );
        
        
        final JPanel panel = new JPanel(); // the panel is not visible in output
        //JLabel label = new JLabel("Enter Text");
        final JCheckBox tf = new JCheckBox("Sono consapevole"); // accepts upto 10 characters
        final JButton send = new JButton("Avanti");
        //JButton reset = new JButton("Reset");
        //panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);

        // My code
        JFrame frame1 = new JFrame ("WARNING");
        frame1.add ( middlePanel );
        frame1.add(panel);
        frame1.pack ();
        frame1.setLocationRelativeTo ( null );
        
        
        
        
        
          
          
    
       
        //panel.add(reset);

        
        

        //Adding Components to the frame.
        //frame1.getContentPane().add(BorderLayout.SOUTH, panel);
        //frame1.getContentPane().add(BorderLayout.CENTER, middlePanel);
        
        frame1.setVisible ( true );*/
        
        
        
 