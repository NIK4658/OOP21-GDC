package ex;

import ex.ExAccessPanel.AccessType;
import ex.ExJaccessPanel;
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

import view.Resizer;

/**
 * //DA SISTEMARE I MAGIC NUMBERS.
 */
public class ExAccessMenu extends JFrame {

    private final Dimension dim = new Resizer().resize(2);
    //private final Pair<Integer, Integer> dim = new Pair<>(3440/2,1440/2); //test 21:9

    private static final long serialVersionUID = 1L;

    /**
     * //DA SISTEMARE I MAGIC NUMBERS.
     */
    public ExAccessMenu() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        // Zona di destra
        final JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        final Image imgi = new ImageModifier().scaleFullScreen(
                new ImageIcon("res/img/backgrounds/HQcasinoCroppedWithTitle.gif").getImage(),
                new Dimension((int) (this.dim.getWidth() * 2 / 3), (int) this.dim.getHeight()));
        east.add(new JLabel(new ImageIcon(imgi), SwingConstants.CENTER));
        east.setPreferredSize(new Dimension((int) (this.dim.getWidth() * 2 / 3), (int) this.dim.getHeight()));
        
        // Zona di sinistra
        final CardLayout cl = new CardLayout();
        final JPanel west = new JPanel(cl);
        final ExJaccessPanel login = new ExJaccessPanel(AccessType.LOGIN, 
                (int) this.dim.getWidth(), (int) this.dim.getHeight());
        final ExJaccessPanel register = new ExJaccessPanel(AccessType.REGISTER,
                (int) dim.getWidth(), (int) dim.getHeight());
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

/* APPUNTI */
// GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().
// getScreenDevices()[0];    //definisce schermo principale
// setUndecorated(true);                //toglie la barra in alto
// device.setFullScreenWindow(this);    //set full screen