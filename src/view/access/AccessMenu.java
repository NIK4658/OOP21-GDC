package view.access;

import javax.swing.*;

import view.ImageModifier;
import view.access.AccessPanel.AccessType;

import java.awt.*;

//DA SISTEMARE I MAGIC NUMBERS
public class AccessMenu extends JFrame {

	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int dimX = (int) screenSize.getWidth() / 2;
	private final int dimY = (int) screenSize.getHeight() / 2;

	private static final long serialVersionUID = 1L;

	public AccessMenu() {

		// Default
		setDefaultCloseOperation(EXIT_ON_CLOSE); 																// chiude processo finestra
		setResizable(false); 																					// non può essere allargata
		// GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; 	//definisce schermo principale
		// setUndecorated(true); 																				//toglie la barra in alto
		// device.setFullScreenWindow(this); 																	//set full screen

		// Zona di destra
		JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		Image imgi = new ImageModifier().scaleFullScreen(
				new ImageIcon("res/img/backgrounds/HQcasinoCroppedWithTitle.gif").getImage(),
				new Dimension(2 * dimX / 3, dimY));																
		east.add(new JLabel(new ImageIcon(imgi), SwingConstants.CENTER));
		east.setPreferredSize(new Dimension(2 * dimX / 3, dimY));												

		// Zona di sinistra
		//JPanel contenitore
		CardLayout cl = new CardLayout();
		JPanel west = new JPanel(cl);
		
		JAccessPanel login = new JAccessPanel(AccessType.LOGIN, dimX, dimY);
		JAccessPanel register = new JAccessPanel(AccessType.REGISTER, dimX, dimY);
		login.setActionListenerRegisterButton(e -> {
			cl.show(west, AccessType.REGISTER.toString());
		});
		register.setActionListenerLoginButton(e -> {
			cl.show(west, AccessType.LOGIN.toString());
		});
		
		//Aggiungo entrambi i tipi di JPanel al JPanel contenitore di sinista
		west.add(login, AccessType.LOGIN.toString());
		west.add(register, AccessType.REGISTER.toString());

		//Rende visibile il JPanel di Login
		cl.show(west, AccessType.LOGIN.toString());

		// Add JPanel to JFrame
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);

		pack();								//setta la dimensione della finestra automaticamente
		setLocationRelativeTo(null);		//setta al centro la finestra automaticamente
		setVisible(true);
	}

}
