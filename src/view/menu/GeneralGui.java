package view.menu;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
import java.awt.Insets;

import account.SimpleAccountManager;
import blackjack.BackgroundPanel;
import view.gui.MenuManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class GeneralGui extends JPanel {
	
	private final int x = 1280;
	private final int y = 720;
	
	
	
	  GeneralGui(){
	       
	        setLayout(new BorderLayout());
	        setPreferredSize(new Dimension(x,y));
	        
	        JPanel north = new JPanel();
	        JPanel south = new JPanel();
	        JPanel center = new JPanel();
	        
	        
	        south.setLayout(new GridBagLayout());
	        south.setPreferredSize(new Dimension(100,100));
	        //south.setBackground(Color.RED);
	        
	        north.setLayout(new GridBagLayout());
	        north.setPreferredSize(new Dimension(100,100));
	        //north.setBackground(Color.RED);
	        
	        final JLabel labelvincita = new JLabel("HAI VINTO...");
	        labelvincita.setForeground(Color.BLACK);
	        labelvincita.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        north.add(labelvincita);
	        
	        
	       
	        
	        
	        
	        final JLabel vincita = new JLabel("VINCITA");
	        vincita.setForeground(Color.BLACK);
	        vincita.setBounds(350, 20, 150, 150);
	        vincita.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        south.add(vincita, setDimensionObj(0,0,0,(int)(x/3),150));
	        
	        final JLabel saldo = new JLabel("SALDO");
	        saldo.setForeground(Color.BLACK);
	        saldo.setBounds(350, 20, 150, 150);
	        saldo.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        south.add(saldo, setDimensionObj(0,0,0,(int)(x/2),20));
	        
	        final JLabel puntata = new JLabel("PUNTATA");
	        puntata.setForeground(Color.BLACK);
	        puntata.setBounds(350, 20, 150, 150);
	        puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
	        south.add(puntata, setDimensionObj(0,0,0,(int)(x/3),0));
	        
	        final JLabel vsaldo = new JLabel("1");
            puntata.setForeground(Color.BLACK);
            puntata.setBounds(350, 20, 150, 150);
            puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
            south.add(vsaldo, setDimensionObj(0,1,0,(int)(x/3),150));
            
            final JLabel vpuntata = new JLabel("2");
            puntata.setForeground(Color.BLACK);
            puntata.setBounds(350, 20, 150, 150);
            puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
            south.add(vpuntata, setDimensionObj(0,1,0,(int)(x/2),20));
            
            final JLabel vvincita = new JLabel("3");
            puntata.setForeground(Color.BLACK);
            puntata.setBounds(350, 20, 150, 150);
            puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
            south.add(vvincita, setDimensionObj(0,1,0,(int)(x/3),0));
            
            
            
	        
	        
	      
	        


	        final JButton bo = new JButton("MENU");
	        //bo.setPreferredSize(new Dimension(100,50));
	        north.add(bo, setDimensionObj(0,0,0,(int)(x/1.3),0));
	        
	        final JButton bo1 = new JButton("?");
	        //bo1.setPreferredSize(new Dimension(100,50));
	        north.add(bo1, setDimensionObj(1,0,0,0,0));
	        
	        
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
	        
	       
	     
	     
	     final JButton reset = new JButton(" ");
	     final JButton confirm = new JButton("  ");
	     
	     
	     
	     final JButton fish1 = new JButton(" ");
	     final JButton fish2 = new JButton(" ");
	     final JButton fish3 = new JButton(" ");
	     final JButton fish4 = new JButton(" ");
	     final JButton fish5 = new JButton(" ");
	     final JButton fish6 = new JButton(" ");
	     
	     
	     final List<JButton> list = new ArrayList<>();
	     	list.add(reset);
	     	list.add(confirm);
	        list.add(fish1);
	        list.add(fish2);
	        list.add(fish3);
	        list.add(fish4);
	        list.add(fish5);
	        
	        
	        


	        for (final JButton jb : list) { 
	        	jb.setOpaque(false);
		        jb.setFocusPainted(false);
		        jb.setBorderPainted(false);
		        jb.setContentAreaFilled(false);
		        jb.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	            
	        }
	        
	        final Image img = new ImageIcon("res/img/fiches/numbers/1.png").getImage();
	        reset.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	        
	        final Image img1 = new ImageIcon("res/img/fiches/numbers/1.png").getImage();
	        confirm.setIcon(new ImageIcon(img1.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	        
	        final Image img2 = new ImageIcon("res/img/fiches/numbers/1.png").getImage();
	        fish1.setIcon(new ImageIcon(img2.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	        
	        final Image img3 = new ImageIcon("res/img/fiches/numbers/5.png").getImage();
	        fish2.setIcon(new ImageIcon(img3.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	        
	        final Image img4 = new ImageIcon("res/img/fiches/numbers/25.png").getImage();
	        fish3.setIcon(new ImageIcon(img4.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	        
	        final Image img5 = new ImageIcon("res/img/fiches/numbers/100.png").getImage();
	        fish4.setIcon(new ImageIcon(img5.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	        
	        final Image img6 = new ImageIcon("res/img/fiches/numbers/500.png").getImage();
	        fish5.setIcon(new ImageIcon(img6.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
	     
	        
	        
	        for(final JButton jb: list) {
	        	south.add(jb);
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	     
	       
	   
	        
	      
	        
	        
	        
	     
	        
	        //adding the panel to the Container of the JFrame
	        add(north,BorderLayout.NORTH);
	        add(center,BorderLayout.CENTER);
	        add(south,BorderLayout.SOUTH);
	        
	       
	        setVisible(true);
	    }
	    public static void main(String[] args) {
	    	
	    	JFrame frame = new JFrame();
	    	frame.setTitle("Gui giochi");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setPreferredSize(new Dimension(1280,720));
	        frame.add(new GeneralGui());
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        
	        
	    	
	        
	    }
	    
	    private GridBagConstraints setDimensionObj(final int gridx, final int gridy,    
	    		final int spacedown, final int spaceright, final int spaceleft) {       
	    	final GridBagConstraints c = new GridBagConstraints();        
	    	c.anchor = GridBagConstraints.PAGE_END;        
	    	c.insets = new Insets(0, spaceleft, spacedown, spaceright); 
	    	// terzo parametro definisce la distanza verticale     
	    	//(verso il basso) tra i vari oggetti della gui      
	    	c.gridx = gridx;       
	    	c.gridy = gridy;       
	    	return c;    
	    	}
	    }
	
		
	



		
		

	    
	   
	