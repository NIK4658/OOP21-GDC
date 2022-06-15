package start;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    	
        final JPanel center = new JPanel();
        final JPanel south = new JPanel();
        center.setBorder(new TitledBorder(new EtchedBorder(), "WARNING!!"));

        // Definition of a Text area with terms and condiction

        JTextArea textArea = new JTextArea(15, 30);
        textArea.setBackground(Color.RED);
        
        //lettura da file

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("WarningTest.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                textArea.append(str);
            }
        } catch (IOException e) {
        } finally {
            try { 
                in.close(); 
            } catch (Exception ex) { }
        }
        
        // set textArea non-editable
        textArea.setEditable(false); 
        
        //Putting the Text area on a scroll panel
        
        JScrollPane scrollText = new JScrollPane(textArea);
        
        //Setting scroll option only vertical
        
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //Definition of a ceckbox and button for confirm terms and conditions
        
        final JCheckBox ceckbox = new JCheckBox("Sono consapevole"); // accepts upto 10 characters
        final JButton accept = new JButton("Avanti");
        accept.setEnabled(false);
        
        //Adding action listener for the checkbox the button "avanti" is unavaiable 
        //the button "avanti" is not enabled until the checkbox is selected

        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
              
                if (selected == true) {
                    accept.setEnabled(true); 
                } else {
                    accept.setEnabled(false);
                  
                }
            
            }
            
        };
        
        //Adding the components to the panels
        
        center.add(scrollText);
        south.add(ceckbox);
        south.add(accept);
        
        // Setting Frame
        ceckbox.addActionListener(actionListener);
        JFrame frame = new JFrame("WARNING");
        frame.add(center, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
    	

        
        
 