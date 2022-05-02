package view.menu;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;

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

import javax.imageio.ImageIO;
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
	        
	        panel4.setBounds(300,400,300,400);
	        
	        //JLabel label3=new JLabel("SALDO | PUNTATA | VINCITA");
	        //label3.setFont(new Font("Verdana", Font.PLAIN, 15));
	        
	        final JLabel saldo = new JLabel("SALDO");
	        saldo.setForeground(Color.BLACK);
	        saldo.setBounds(350, 20, 150, 150);
	        saldo.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        panel3.add(saldo, 0);
	        
	        final JLabel puntata = new JLabel("PUNTATA");
	        puntata.setForeground(Color.BLACK);
	        puntata.setBounds(350, 20, 150, 150);
	        puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        panel3.add(puntata, 0);
	        
	        final JLabel vincita = new JLabel("VINCITA");
	        vincita.setForeground(Color.BLACK);
	        vincita.setBounds(350, 20, 150, 150);
	        vincita.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        panel3.add(vincita, 0);
	        
	        
	        
	        
	        


	        final JButton bo = new JButton("MENU");
	        panel.add(bo);
	        
	        final JButton bo1 = new JButton("?");
	        panel2.add(bo1);
	        
	        
	        JButton imageButton = new JButton();

	        ImageIcon image = new ImageIcon("yourImage.png"); 
	        		//JButton jButton1 = new JButton(new ImageIcon(getScaledImage(icon.getImage(), 32, 32)));
	        		
	        		
	        		//jButton1.setIcon(new ImageIcon(ImageIO.read(new File("path/to/image.png"))));



	        		/**
	        		 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	        		 * @param srcImg - source image to scale
	        		 * @param w - desired width
	        		 * @param h - desired height
	        		 * @return - the new resized image
	        		 */
	        		/*private Image getScaledImage(Image srcImg, int w, int h){
	        		    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	        		    Graphics2D g2 = resizedImg.createGraphics();
	        		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        		    g2.drawImage(srcImg, 0, 0, w, h, null);
	        		    g2.dispose();
	        		    return resizedImg;
	        		}*/
	        
	       
	     
	     
	     final JButton bo2 = new JButton("ANNULLA");
	        final JButton button = new JButton("1");
	        button.setOpaque(false);
	        button.setFocusPainted(false);
	        button.setBorderPainted(false);
	        button.setContentAreaFilled(false);
	        button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	        try {
	            Image img = ImageIO.read(getClass().getResource("res/img/backgrounds/1.png"));
	            button.setIcon(new ImageIcon(img));
	          } catch (Exception ex) {
	            System.out.println(ex);
	          }
	        final JButton bo4 = new JButton("5");
	        final JButton bo5 = new JButton("10");
	        final JButton bo6 = new JButton("25");
	        final JButton bo7 = new JButton("120");
	        panel4.add(imageButton);
	        panel4.add(bo2);
	        panel4.add(button);
	        panel4.add(bo4);
	        panel4.add(bo5);
	        panel4.add(bo6);
	        panel4.add(bo7);
	        
	        final Image img1 = new ImageIcon("res/img/fiches/empty/1.png").getImage();
	        
	        //JButton imageButton = new JButton(new ImageIcon(img1));
	        
	        //Image img = icon.getImage() ;  
	        //Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
	        //icon = new ImageIcon( newimg );
	        
	        imageButton.setBorderPainted(false);
	        imageButton.setContentAreaFilled(false);
	        imageButton.setFocusPainted(false);
	        imageButton.setOpaque(false);
	        imageButton.setPreferredSize(new Dimension(40, 40));
	        
	       // panel4.add(bo2);
	        
	        
	        // Panel border
	        //panel.setBorder(br);
	        //panel2.setBorder(br);
	       // panel3.setBorder(br);
	       // panel4.setBorder(br);
	        
	        //adding the panel to the Container of the JFrame
	        c.add(panel,BorderLayout.EAST);
	        c.add(panel2,BorderLayout.WEST);
	        c.add(panel3,BorderLayout.SOUTH);
	        c.add(panel4,BorderLayout.SOUTH);
	       
	        setVisible(true);
	    }
	    public static void main(String[] args) {
	        new GeneralGui();
	    }
	}
		
	



		
		

	    
	   
	