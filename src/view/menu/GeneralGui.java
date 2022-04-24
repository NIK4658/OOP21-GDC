package view.menu;

import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


import account.AccountManager;
import view.gui.MenuManager;

public class GeneralGui extends JFrame {
	
	
	
	  GeneralGui(){
	        setTitle("JPANEL CREATION");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(null);
	        //setting the bounds for the JFrame
	        setBounds(100,100,645,470);
	        //Border br = BorderFactory.createLineBorder(Color.BLACK);
	        Container c=getContentPane();
	        //Creating a JPanel for the JFrame
	        JPanel panel=new JPanel();
	        JPanel panel2=new JPanel();
	        JPanel panel3=new JPanel();
	        JPanel panel4=new JPanel();
	        //setting the panel layout as null
	        
	        
	        /*panel.setLayout(null);
	        panel2.setLayout(null);
	        panel3.setLayout(null);
	        panel4.setLayout(null);
	        //adding a label element to the panel
	        /*JLabel label=new JLabel("Panel 1");
	        JLabel label2=new JLabel("Panel 2");
	        JLabel label3=new JLabel("Panel 3");
	        JLabel label4=new JLabel("Panel 4");
	        label.setBounds(120,50,200,50);
	        label2.setBounds(120,50,200,50);
	        label3.setBounds(120,50,200,50);
	        label4.setBounds(120,50,200,50);
	        panel.add(label);
	        panel2.add(label2);
	        panel3.add(label3);
	        panel4.add(label4);*/
	        // changing the background color of the panel to yellow
	        //Panel 1
	        //panel.setBackground(Color.yellow);
	        panel.setBounds(10,10,70,50);
	        //Panel 2
	        //panel2.setBackground(Color.red);
	        panel2.setBounds(570,10,70,50);
	        //Panel 3
	        panel3.setBackground(Color.green);
	        panel3.setBounds(10,220,300,200);
	        //Panel 4
	        panel4.setBackground(Color.cyan);
	        panel4.setBounds(320,220,300,200);
	        


	        final JButton bo = new JButton("MENU");
	        panel.add(bo);
	        
	        final JButton bo1 = new JButton("?");
	        panel2.add(bo1);
	        
	        // Panel border
	        //panel.setBorder(br);
	        //panel2.setBorder(br);
	       // panel3.setBorder(br);
	       // panel4.setBorder(br);
	        
	        //adding the panel to the Container of the JFrame
	        c.add(panel,BorderLayout.EAST);
	        c.add(panel2,BorderLayout.WEST);
	        c.add(panel3);
	        c.add(panel4);
	       
	        setVisible(true);
	    }
	    public static void main(String[] args) {
	        new GeneralGui();
	    }
	}
		
	



		
		

	    
	   
	