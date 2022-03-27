package start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;


	

public class AccountMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Ripetizione da sistemare poi */
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int dimX = (int) screenSize.getWidth() / 2;
	private final int dimY = (int) screenSize.getHeight() / 2;
	private int i = 0;
	
	public AccountMenu() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		
		JPanel panel2 = new JPanel(new GridBagLayout());
		panel2.setBackground(new Color(68, 87, 96));
		panel2.setPreferredSize(new Dimension(dimX, dimY));
		JButton btJDialog = new JButton("JDialog");
		panel2.add(btJDialog, setDimensionObj(0, 0, 40));
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(68, 87, 96));
		panel.setPreferredSize(new Dimension(dimX / 2, dimY));
		
		JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
//		title.setPreferredSize(new Dimension(dimX / 4, dimY / 20));
		title.setFont(new Font("Arial", Font.BOLD, dimX / 20));
		panel.add(title, setDimensionObj(0, i++, 40));
		
		JButton ricarica = new JButton("RICARICA");
		JButton preleva = new JButton("PRELEVA");
		JButton dettaglioSaldo = new JButton("DETTAGLIO SALDO");
		JButton cambiaPassword = new JButton("CAMBIA PASSWORD");
		JButton eliminaAccount = new JButton("ELIMINA ACCOUNT");
		List<JButton> list = new LinkedList<>();
		list.add(ricarica);
		list.add(preleva);
		list.add(dettaglioSaldo);
		list.add(cambiaPassword);
		list.add(eliminaAccount);
		for (JButton jb : list) {
//		jb.setOpaque(false);
//		jb.setContentAreaFilled(false);
//		jb.setBorderPainted(false);
//		jb.setForeground(Color.WHITE);
		jb.setPreferredSize(new Dimension(dimX / 4 , dimY / 20));
		jb.setFont(new Font("Arial", Font.PLAIN, dimX / 50));
		panel.add(jb, setDimensionObj(0, i++, 5));
		}
		
		ricarica.addActionListener(e -> {
			System.out.println("Ciao");
		});
		
		preleva.addActionListener(e -> {
			System.out.println("Ciao");
		});
		
		dettaglioSaldo.addActionListener(e -> {
			System.out.println("Ciao");
		});
		
		cambiaPassword.addActionListener(e -> {
			System.out.println("Ciao");
		});
		
		eliminaAccount.addActionListener(e -> {
			System.out.println("Ciao");
		});
		
		btJDialog.addActionListener(e -> {
			System.out.println("va");
			JDialog dialog = new JDialog(this, true);
			dialog.setContentPane(panel);
			dialog.pack();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		});
		
		add(panel2, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null); //setta al centro dello schermo --> al centro del menu principale
		setVisible(true);
	}
	
	/* Ripetizione da sistemare poi */
	private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int space) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, space, 0); // terzo parametro definisce la distanza verticale (verso il basso) tra i vari oggetti della gui
		c.gridx = gridx;
		c.gridy = gridy;
		return c;
	}
	
	/* Testing */
	public static void main(String[] args){
		new AccountMenu();
	}
}
