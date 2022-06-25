package view.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.account.AccountManager;
import view.Utilities;
import view.gui.MenuManager;
import view.gui.Warning;
import view.menu.access.Access;
import view.menu.access.AccessPanel;

/**
 * Starting panel of the program.
 */
public class AccessMenu extends JPanel implements Access, Menu {

    private static final long serialVersionUID = 1L;
    private final MenuManager frame;
    private final AccountManager account;
    
    /**
     * Main Costructor.
     * 
     * @param frame Frame of the window.
     * @param account AccountManager useful to manage user data.
     */
    public AccessMenu(final MenuManager frame, final AccountManager account) {
        this.setLayout(new BorderLayout());
        this.frame = frame;
        this.account = account;
        // Zona di destra
        final Dimension dimImg = new Dimension(frame.getWidthMenu() * 2 / 3, frame.getHeightMenu());
        final Image img = Utilities.getImage("img/backgrounds/HQcasinoCroppedWithTitle.gif");
        final Image imgScaled = img.getScaledInstance(dimImg.width, dimImg.height, Image.SCALE_DEFAULT);
        this.add(new JLabel(new ImageIcon(imgScaled)), BorderLayout.EAST);
        
        
        // Zona di sinistra
        final CardLayout cl = new CardLayout();
        final JPanel west = new JPanel(cl);
        final Dimension dimAccessPanel = new Dimension(frame.getWidthMenu() * 1 / 3, frame.getHeightMenu());
        final AccessPanel login = new AccessPanel(this, dimAccessPanel, AccessType.LOGIN,
                e -> cl.show(west, AccessType.REGISTER.toString()), account);
        final AccessPanel register = new AccessPanel(this, dimAccessPanel, AccessType.REGISTER, 
                e -> cl.show(west, AccessType.LOGIN.toString()), account);
        west.add(login, AccessType.LOGIN.toString());
        west.add(register, AccessType.REGISTER.toString());
        cl.show(west, AccessType.LOGIN.toString());
        this.add(west, BorderLayout.WEST);
        this.setFocusable(true);
    }
    
    @Override
    public void successfullyAccessed() {
        frame.setMainMenu(this.account);
        new Warning();
    }
    
    @Override
    public JPanel getMenu() {
        return this;
    }

}
