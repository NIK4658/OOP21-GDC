package view.access;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import start.MainGui;
import view.ImageModifier;
import view.access.JaccessPanel.AccessType;

/**
 * //DA SISTEMARE I MAGIC NUMBERS.
 */
public class AccessMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * //DA SISTEMARE I MAGIC NUMBERS.
     */
    public AccessMenu(final MainGui frame) {
        
        this.setLayout(new BorderLayout());
        
        // Zona di destra
        final Dimension dimImg = new Dimension(frame.getWidth() * 2 / 3, frame.getHeight());
        final Image imgi = new ImageModifier().scaleFullScreen(
                new ImageIcon("res/img/backgrounds/HQcasinoCroppedWithTitle.gif").getImage(), dimImg);
        this.add(new JLabel(new ImageIcon(imgi)), BorderLayout.EAST);
        
        // Zona di sinistra
        final CardLayout cl = new CardLayout();
        final JPanel west = new JPanel(cl);
        final JaccessPanel login = new JaccessPanel(AccessType.LOGIN, frame.getWidth(), frame.getHeight());
        final JaccessPanel register = new JaccessPanel(AccessType.REGISTER, frame.getWidth(), frame.getHeight());
        login.setActionListenerRegisterButton(e -> {
            cl.show(west, AccessType.REGISTER.toString());
        });
        register.setActionListenerLoginButton(e -> {
            cl.show(west, AccessType.LOGIN.toString());
        });
        west.add(login, AccessType.LOGIN.toString());
        west.add(register, AccessType.REGISTER.toString());
        cl.show(west, AccessType.LOGIN.toString());
        
        this.add(west, BorderLayout.WEST);
        frame.updateContentPanel(this);
    }
}

















