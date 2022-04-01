package start;

import javax.swing.*;

import view.ImageModifier;

import java.awt.*;
import java.util.ArrayList;

public class MasterGUI extends JFrame {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int DIMX = (int) screenSize.getWidth() /2;
	private static final int DIMY = (int) screenSize.getHeight() /2;

	private static final long serialVersionUID = 1L;

	public MasterGUI() {

		// Default
		setDefaultCloseOperation(EXIT_ON_CLOSE); 																// chiude processo finestra
		setResizable(false); 																					// non può essere allargata
		//GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; 	//definisce schermo principale
		setLocation((DIMX / 2) - (getSize().width / 2), (DIMY / 2) - (getSize().height / 2)); 					// spawn al centro
		//setUndecorated(true); 																				//toglie la barra in alto
		//device.setFullScreenWindow(this); 																	//set full screen

		// Zona di destra
		JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		
		
		
		JPanel south = new JPanel(new GridBagLayout());
		JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		ImageModifier imgMod = new ImageModifier();
		Image imgi = imgMod.scaleFullScreen(
				(new ImageIcon("res/img/backgrounds/tavolo.jpg").getImage()),
				new Dimension(DIMX, DIMY));
		JLabel bg = new JLabel();
		bg.setIcon(new ImageIcon(imgi));

		// Zona di sinistra

		//setContentPane(new JLabel(new ImageIcon(imgi)));
		
		north.setBackground(new Color(44, 107, 14));	
		south.setBackground(new Color(44, 107, 14));// blu bello
		
		//west.setBackground(new Color(44, 107, 14)); 					//verde tavolo
		north.setLayout(new GridBagLayout());
		north.setPreferredSize(new Dimension(DIMX, DIMY/4));
		south.setPreferredSize(new Dimension(DIMX, 3*DIMY/4));

		// Componenti JPanel west (login)
		JLabel title = new JLabel("GIOCHI DEL COLOSSO", SwingConstants.CENTER); 			// Titolo Login

		
		JButton roulette = new JButton(); 							// Tasto Login
		JButton bj =new JButton(); 											// Tasto Registrati
		JButton bacarat =new JButton();
		
		
		imgi = imgMod.scaleFullScreen(
				(new ImageIcon("res/img/buttons/roulette.jpeg").getImage()),
				new Dimension(DIMX/5 , DIMY / 3));
		roulette.setIcon(new ImageIcon((imgi)));
		
		imgi = imgMod.scaleFullScreen(
				(new ImageIcon("res/img/buttons/blackjack.PNG").getImage()),
				new Dimension(DIMX/5 , DIMY / 3));
		bj.setIcon(new ImageIcon((imgi)));
		
		imgi = imgMod.scaleFullScreen(
				(new ImageIcon("res/img/buttons/baccarat.jpg").getImage()),
				new Dimension(DIMX/5 , DIMY / 3));
		bacarat.setIcon(new ImageIcon((imgi)));
		
		
		//roulette.setBorder(BorderFactory.createEmptyBorder());
		//roulette.setBorder(null);
		//roulette.setBorderPainted(false);
		
		

		ArrayList<JComponent> list = new ArrayList<>();

		list.add(roulette);
		list.add(bj);
		list.add(bacarat);

		// Modifiche solo per titolo
		title.setForeground(Color.WHITE);
		title.setPreferredSize(new Dimension(DIMX - (DIMX / 3 / 2), DIMY / 10));
		title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, DIMX / 20));
		north.add(title, setDimensionObj(0, 0, 0,0,0));

		// Modifiche generali
		int i = 0;
		for (JComponent jc : list) {
			jc.setPreferredSize(new Dimension(DIMX/5 , DIMY / 3));
			jc.setFont(new Font("Arial", Font.PLAIN, DIMX / 50));
			south.add(jc, setDimensionObj(i, 0, 0,50,50));
			i++;
		}

		
		//south.add(new JLabel("ciao "), setDimensionObj(0, 1, 0) );
		// Add JPanel to JFrame
		
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
	

		
		setSize(DIMX, DIMY);
		setVisible(true);
	}

	private GridBagConstraints setDimensionObj(int gridx, int gridy, int spacedown, int spaceleft, int spaceright) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, spaceleft, spacedown, spaceright); // terzo parametro definisce la distanza verticale (verso il basso) tra i vari oggetti della gui
		c.gridx = gridx;
		c.gridy = gridy;
		return c;
	}
}
