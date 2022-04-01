package view.access;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utility.Pair;
import view.ImageModifier;
import view.Resizer;
import view.access.AccessPanel.AccessType;


//DA SISTEMARE I MAGIC NUMBERS
public class AccessMenu extends JFrame {

	private final Pair<Integer, Integer> dim = new Resizer().Resize(2);
	//private final Pair<Integer, Integer> dim = new Pair<>(3440/2,1440/2); //test 21:9
	
    private static final long serialVersionUID = 1L;

    
    public AccessMenu() {
        // Default
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
	// GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; 	//definisce schermo principale
        // setUndecorated(true);                //toglie la barra in alto
        // device.setFullScreenWindow(this);    //set full screen
        
        // Zona di destra
        final JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        final Image imgi = new ImageModifier().scaleFullScreen(
                new ImageIcon("res/img/backgrounds/HQcasinoCroppedWithTitle.gif").getImage(),
                new Dimension(2 * dim.get1() / 3, dim.get2()));
        east.add(new JLabel(new ImageIcon(imgi), SwingConstants.CENTER));
        east.setPreferredSize(new Dimension(2 * dim.get1() / 3, dim.get2()));
        
        // Zona di sinistra
        //JPanel contenitore
        final CardLayout cl = new CardLayout();
        final JPanel west = new JPanel(cl);
        final JAccessPanel login = new JAccessPanel(AccessType.LOGIN, dim.get1(), dim.get2());
        final JAccessPanel register = new JAccessPanel(AccessType.REGISTER, dim.get1(), dim.get2());
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
        
        pack();                                 //setta la dimensione della finestra automaticamente
        setLocationRelativeTo(null);            //setta al centro la finestra automaticamente
        setVisible(true);
    }
}
