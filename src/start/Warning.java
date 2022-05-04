package start;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Warning extends JDialog {
    public static void main(String args[]) {
        
        //Definition of center and south JPanel
    	
    	JPanel center = new JPanel ();
    	JPanel south = new JPanel ();
        center.setBorder ( new TitledBorder ( new EtchedBorder (), "WARNING!!" ) );

        // Definition of a Text area with terms and condiction

        JTextArea textArea = new JTextArea ( 15,30 );
        textArea.setBackground(Color.RED);
        textArea.setText("1.1 William Hill RacingTV is only available to William Hill account holders, "
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
        
        // set textArea non-editable
        textArea.setEditable ( false ); 
        
        //Putting the Text area on a scroll panel
        
        JScrollPane scrollText = new JScrollPane ( textArea );
        
        //Setting scroll option only vertical
        
        scrollText.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scrollText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //Definition of a ceckbox and button for confirm terms and conditions
        
        final JCheckBox ceckbox = new JCheckBox("Sono consapevole"); // accepts upto 10 characters
        final JButton accept = new JButton("Avanti");
        accept.setEnabled(false);
        
        //Adding action listener for the checkbox the button "avanti" is unavaiable 
        //the button "avanti" is not enabled until the checkbox is selected

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
              boolean selected = abstractButton.getModel().isSelected();
              
              if(selected==true) 
              {
            	  accept.setEnabled(true);
            	  
              }
              
              else 
              {
                  
                  accept.setEnabled(false);
                  
              }
            
            }
            
        };
        
        //Adding the components to the panels
        
        center.add ( scrollText );
        south.add ( ceckbox );
        south.add ( accept);
        
        // Setting Frame
        ceckbox.addActionListener(actionListener);
        JFrame frame = new JFrame ("WARNING");
        frame.add ( center ,BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);
        frame.pack ();
        frame.setLocationRelativeTo ( null );
        frame.setVisible ( true );
    }
}
    	

        
        
 