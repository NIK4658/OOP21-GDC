package view.menu;

import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Image;

import account.SimpleAccountManager;
import view.gui.MenuManager;

public class GeneralGui extends JFrame {
	
	
	
	  GeneralGui(){
	        setTitle("Gui giochi");
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
	        
	        
	       
	        // changing the background color of the panel to yellow
	        //Panel 1
	        //panel.setBackground(Color.yellow);
	        panel.setBounds(10,10,70,50);
	        //Panel 2
	        
	        panel2.setBounds(570,10,70,50);
	        //Panel 3
	        
	        panel3.setBounds(10,400,300,200);
	        //Panel 4
	        
	        panel4.setBounds(300,400,300,200);
	        
	        JLabel label3=new JLabel("SALDO | PUNTATA | VINCITA");
	        label3.setFont(new Font("Verdana", Font.PLAIN, 15));
	        
	        label3.setBounds(10,50,200,50);
	        
	        panel3.add(label3);
	        


	        final JButton bo = new JButton("MENU");
	        panel.add(bo);
	        
	        final JButton bo1 = new JButton("?");
	        panel2.add(bo1);
	        
	        final JButton bo2 = new JButton("ANNULLA");
	        /*final JButton bo3 = new JButton("?");
	        final JButton bo4 = new JButton("?");
	        final JButton bo5 = new JButton("?");
	        final JButton bo6 = new JButton("?");
	        final JButton bo7 = new JButton("?");
	        //panel4.add(bo2);
	        //panel4.add(bo3);
	        //panel4.add(bo4);
	        //panel4.add(bo5);
	        //panel4.add(bo6);
	        //panel4.add(bo7);*/
	        
	        final Image img1 = new ImageIcon("res/img/fiches/empty/1.png").getImage();
	        
	        JButton imageButton = new JButton(new ImageIcon(img1));
	        
	        //Image img = icon.getImage() ;  
	        //Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
	        //icon = new ImageIcon( newimg );
	        
	        imageButton.setBorderPainted(false);
	        imageButton.setContentAreaFilled(false);
	        imageButton.setFocusPainted(false);
	        imageButton.setOpaque(false);
	        imageButton.setPreferredSize(new Dimension(40, 40));
	        
	        panel4.add(bo2);
	        
	        
	        // Panel border
	        //panel.setBorder(br);
	        //panel2.setBorder(br);
	       // panel3.setBorder(br);
	       // panel4.setBorder(br);
	        
	        //adding the panel to the Container of the JFrame
	        c.add(panel,BorderLayout.EAST);
	        c.add(panel2,BorderLayout.WEST);
	        c.add(panel3,BorderLayout.SOUTH);
	        c.add(panel4);
	       
	        setVisible(true);
	    }
	    public static void main(String[] args) {
	        new GeneralGui();
	    }
	}
		
	



		
		

	    
	   
	