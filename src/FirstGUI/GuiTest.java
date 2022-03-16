package FirstGUI;

import Account.AccountManagerImpl;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GuiTest extends JFrame {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int DIMX = (int) screenSize.getWidth() / 2;
	private static final int DIMY = (int) screenSize.getHeight() / 2;

	private static final long serialVersionUID = 1L;

	public GuiTest() {

		// Default
		setDefaultCloseOperation(EXIT_ON_CLOSE); 															// chiude processo finestra
		setResizable(false); 																				// non può essere allargata
		//GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; 	// definisce schermo principale
		setLocation((DIMX / 2) - (getSize().width / 2), (DIMY / 2) - (getSize().height / 2)); 				// spawn al centro
		//setUndecorated(true);																				//toglie la barra in alto
		//device.setFullScreenWindow(this); 																//set full screen
		
		// Zona di destra
		JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		ImageModifier imgMod = new ImageModifier();
		Image imgi = imgMod.scaleFullScreen((new ImageIcon("res/img/backgrounds/HQcasinoCroppedWithTitle.gif").getImage()),new Dimension(2 * DIMX / 3, DIMY));
		east.add(new JLabel(new ImageIcon(imgi), SwingConstants.CENTER));
		east.setPreferredSize(new Dimension(2 * DIMX / 3, DIMY));

		// Zona di sinistra
		JPanel west = new JPanel();
		west.setBackground(new Color(68, 87, 96)); 						// blu bello
		// west.setBackground(new Color(44, 107, 14)); 					//verde tavolo
		west.setLayout(new GridBagLayout());
		west.setPreferredSize(new Dimension(DIMX / 3, DIMY));

		// Componenti JPanel west
		JLabel title = new JLabel("LOGIN", SwingConstants.CENTER); 						// Titolo Login
		JTextField usr = new JTextField("Username"); 									// Username
		JTextField psw = new JTextField("Password"); 									// Password
		JTextField eta = new JTextField("Età"); 										// Età
		JButton loginbtn = new JButton("Login"); 										// Tasto Login
		JButton registerbtn = new JButton("Registrati"); 								// Tasto Registrati

		ArrayList<JComponent> list = new ArrayList<>();

		list.add(usr);
		list.add(psw);
		list.add(eta);
		list.add(loginbtn);
		list.add(registerbtn);

		//Modifiche per titolo
		title.setForeground(Color.WHITE);
		title.setPreferredSize(new Dimension(DIMX / 3 - (DIMX / 3 / 2), DIMY / 10));
		title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, DIMX / 20));
		west.add(title, setDimensionObj(0, 0, 60));

		//Modifiche generali
		int i = 1;
		for (JComponent jc : list) {
			jc.setPreferredSize(new Dimension(DIMX / 3 - (DIMX / 3 / 2), DIMY / 20));
			jc.setFont(new Font("Arial", Font.PLAIN, DIMX / 50));
			west.add(jc, setDimensionObj(0, i, 5));
			i++;
		}

		// Add JPanel to JFrame
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);

		// Script temporaneo per creare/loggare account 
		
		// Login
		loginbtn.addActionListener(e -> {
			new AccountManagerImpl().Logger(usr.getText(), psw.getText());	
		});
		// Register
		registerbtn.addActionListener(e -> {
			new AccountManagerImpl().Register(usr.getText(), psw.getText(), eta.getText());
		});

		setSize(DIMX, DIMY);
		setVisible(true);
	}

	private GridBagConstraints setDimensionObj(int gridx, int gridy, int space) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, space, 0); 							// terzo parametro definisce la distanza verticale (verso il basso) tra i vari oggetti della gui
		c.gridx = gridx;
		c.gridy = gridy;
		return c;
	}
}
