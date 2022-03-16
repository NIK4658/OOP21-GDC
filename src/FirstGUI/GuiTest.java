package FirstGUI;

import javax.swing.*;
import Account.AccountManagerImpl;
import java.awt.*;
import java.util.ArrayList;

public class GuiTest extends JFrame {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int DIMX = (int) screenSize.getWidth() / 2;
	private static final int DIMY = (int) screenSize.getHeight() / 2;

	private static final long serialVersionUID = 1L;

	public GuiTest() {

		// Default
		setDefaultCloseOperation(EXIT_ON_CLOSE); 																// chiude processo finestra
		setResizable(false); 																					// non può essere allargata
		// GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; 	//definisce schermo principale
		setLocation((DIMX / 2) - (getSize().width / 2), (DIMY / 2) - (getSize().height / 2)); 					// spawn al centro
		// setUndecorated(true); 																				//toglie la barra in alto
		// device.setFullScreenWindow(this); 																	//set full screen

		// Zona di destra
		JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		ImageModifier imgMod = new ImageModifier();
		Image imgi = imgMod.scaleFullScreen(
				(new ImageIcon("res/img/backgrounds/HQcasinoCroppedWithTitle.gif").getImage()),
				new Dimension(2 * DIMX / 3, DIMY));
		east.add(new JLabel(new ImageIcon(imgi), SwingConstants.CENTER));
		east.setPreferredSize(new Dimension(2 * DIMX / 3, DIMY));

		// Zona di sinistra
		JPanel login = new JPanel();
		JPanel register = new JPanel();

		register.setBackground(new Color(68, 87, 96));					 	// blu bello
		//register.setBackground(new Color(44, 107, 14)); 					//verde tavolo
		register.setLayout(new GridBagLayout());
		register.setPreferredSize(new Dimension(DIMX / 3, DIMY));

		login.setBackground(new Color(68, 87, 96)); 						// blu bello
		//login.setBackground(new Color(44, 107, 14)); 						//verde tavolo
		login.setLayout(new GridBagLayout());
		login.setPreferredSize(new Dimension(DIMX / 3, DIMY));

		// Componenti JPanel west (login)
		JLabel title = new JLabel("LOGIN", SwingConstants.CENTER); 			// Titolo Login
		JTextField usr = new JTextField("Username"); 						// Username
		JTextField psw = new JTextField("Password"); 						// Password
		JButton loginbtn = new JButton("Login"); 							// Tasto Login
		JButton registerbtn = new JButton("Registrati"); 					// Tasto Registrati

		ArrayList<JComponent> list = new ArrayList<>();

		list.add(usr);
		list.add(psw);
		list.add(loginbtn);
		list.add(registerbtn);

		// Modifiche solo per titolo
		title.setForeground(Color.WHITE);
		title.setPreferredSize(new Dimension(DIMX / 3 - (DIMX / 3 / 2), DIMY / 10));
		title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, DIMX / 20));
		login.add(title, setDimensionObj(0, 0, 60));

		// Modifiche generali
		int i = 1;
		for (JComponent jc : list) {
			jc.setPreferredSize(new Dimension(DIMX / 3 - (DIMX / 3 / 2), DIMY / 20));
			jc.setFont(new Font("Arial", Font.PLAIN, DIMX / 50));
			login.add(jc, setDimensionObj(0, i, 5));
			i++;
		}

		// Componenti JPanel west (Register)
		JLabel title2 = new JLabel("REGISTRATI", SwingConstants.CENTER); 		// Titolo Registrati
		JTextField usr2 = new JTextField("Username"); 							// Username
		JTextField psw2 = new JTextField("Password"); 							// Password
		JTextField eta = new JTextField("Età"); 								// Età
		JButton loginbtn2 = new JButton("Login"); 								// Tasto Login
		JButton registerbtn2 = new JButton("Registrati"); 						// Tasto Registrati

		ArrayList<JComponent> list2 = new ArrayList<>();

		list2.add(usr2);
		list2.add(psw2);
		list2.add(eta);
		list2.add(loginbtn2);
		list2.add(registerbtn2);

		// Modifiche per titolo registrati
		title2.setForeground(Color.WHITE);
		title2.setPreferredSize(new Dimension(DIMX / 2 - (DIMX / 3 / 2), DIMY / 10));
		title2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, DIMX / 25));
		register.add(title2, setDimensionObj(0, 0, 60));

		// Modifiche generali
		i = 1;
		for (JComponent jc : list2) {
			jc.setPreferredSize(new Dimension(DIMX / 3 - (DIMX / 3 / 2), DIMY / 20));
			jc.setFont(new Font("Arial", Font.PLAIN, DIMX / 50));
			register.add(jc, setDimensionObj(0, i, 5));
			i++;
		}
		
		//JPanel contenitore
		JPanel west = new JPanel();
		CardLayout cl = new CardLayout();
		west.setLayout(cl);
		
		//Aggiungo entrambi i tipi di JPanel al JPanel contenitore di sinista
		west.add(login, "1");
		west.add(register, "2");

		//Rende visibile il JPanel di Login
		cl.show(west, "1");

		// Add JPanel to JFrame
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
	
		// Script temporaneo per creare/loggare account
		
		// Login
		loginbtn.addActionListener(e -> {
			new AccountManagerImpl().Logger(usr.getText(), psw.getText());
		});
		
		// Register
		registerbtn2.addActionListener(e -> {
			new AccountManagerImpl().Register(usr.getText(), psw.getText(), eta.getText());
		});
		
		// Register (switch layout)
		registerbtn.addActionListener(e -> {
			cl.show(west, "2");
		});

		// Login (switch layout)
		loginbtn2.addActionListener(e -> {
			cl.show(west, "1");
		});

		pack();
		setSize(DIMX, DIMY);
		setVisible(true);
	}

	private GridBagConstraints setDimensionObj(int gridx, int gridy, int space) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, space, 0); // terzo parametro definisce la distanza verticale (verso il basso) tra i vari oggetti della gui
		c.gridx = gridx;
		c.gridy = gridy;
		return c;
	}
}
