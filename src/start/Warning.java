package start;
import javax.swing.*;
import java.awt.*;

public class Warning {
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("WARNING");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        Color colore = Color.RED;
        Color colore2 = Color.WHITE;
        
        
        
         
        JTextField t1;  
        
        t1=new JTextField("IL GIOCO CREA DIPENDENZA STAI ATTENTO");
         
        
        t1.setBackground(colore);
        t1.setSelectedTextColor(colore2);
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setEditable(false);
        t1.setBounds(30,30, 300,200);  
          
         
        frame.add(t1);   
          
          
        
        

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        //JLabel label = new JLabel("Enter Text");
        JCheckBox tf = new JCheckBox("Sono consapevole"); // accepts upto 10 characters
        JButton send = new JButton("Avanti");
        //JButton reset = new JButton("Reset");
        //panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        //panel.add(reset);

        // Text Area at the Center
        

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, t1);
        
        
        frame.setVisible(true);
    }
}
	


