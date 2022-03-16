package NotifyGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotifyGUI extends JDialog implements ActionListener{

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int DIMX = (int) screenSize.getWidth() / 5;	//larghezza
	private static final int DIMY = (int) screenSize.getHeight() / 8; 	//altezza
	private static final long serialVersionUID = 1L;

	public NotifyGUI(String text) {
		this.setTitle("Attenzione"); 																		//Titolo finestra
		this.setModal(true);																				//Disabilita altre finestre fino a che non viene chiusa questa											
		setResizable(false); 																				//non può essere allargata
		//GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; 	// definisce schermo principale
		
		setAlwaysOnTop(true);
		setSize(DIMX, DIMY);
		setLocation((1920/2) - (getSize().width/2), (1080/2) - (getSize().height/2));
		JPanel content = new JPanel(new GridBagLayout());
		JLabel txt = new JLabel(text);
		JButton btn = new JButton("OK");
		txt.setFont(new Font("Arial", Font.PLAIN, DIMX / 30));
		btn.setFont(new Font("Arial", Font.BOLD, DIMX / 30));
		content.add(txt, setDimensionObj(0, 0, 10));
		content.add(btn, setDimensionObj(0, 1, 5));	
		add(content);
		btn.addActionListener(this);
		//pack(); 		// !?!?	
		setVisible(true);
	}
	
	private GridBagConstraints setDimensionObj(int gridx, int gridy, int space) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10, 0, space, 0); 							// terzo parametro definisce la distanza verticale tra i vari oggetti della gui
		c.gridx = gridx;
		c.gridy = gridy;
		return c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
