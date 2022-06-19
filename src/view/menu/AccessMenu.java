package view.menu;

import account.AdvancedAccountManager;
import account.SimpleAccountManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.gui.MenuManager;
import view.menu.access.Access;
import view.menu.access.AccessPanel;
import view.ImageLoader;

/**
 * //DA SISTEMARE I MAGIC NUMBERS.
 */
public class AccessMenu extends JPanel implements Access, Menu {

    private static final long serialVersionUID = 1L;
    private final MenuManager frame;
    
    /**
     * //DA SISTEMARE I MAGIC NUMBERS.
     */
    public AccessMenu(final MenuManager frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        
        // Zona di destra
        final Dimension dimImg = new Dimension(frame.getWidthMenu() * 2 / 3, frame.getHeightMenu());
        final Image img = ImageLoader.getImage("res/img/backgrounds/HQcasinoCroppedWithTitle.gif");
        final Image imgScaled = img.getScaledInstance(dimImg.width, dimImg.height, Image.SCALE_DEFAULT);
        this.add(new JLabel(new ImageIcon(imgScaled)), BorderLayout.EAST);
        
        
        // Zona di sinistra
        final CardLayout cl = new CardLayout();
        final JPanel west = new JPanel(cl);
        final Dimension dimAccessPanel = new Dimension(frame.getWidthMenu() * 1 / 3, frame.getHeightMenu());
        final AccessPanel login = new AccessPanel(this, dimAccessPanel, AccessType.LOGIN,
                e -> cl.show(west, AccessType.REGISTER.toString()));
        final AccessPanel register = new AccessPanel(this, dimAccessPanel, AccessType.REGISTER, 
                e -> cl.show(west, AccessType.LOGIN.toString()));
        west.add(login, AccessType.LOGIN.toString());
        west.add(register, AccessType.REGISTER.toString());
        cl.show(west, AccessType.LOGIN.toString());
        this.add(west, BorderLayout.WEST);
        this.setFocusable(true);
    }
    
    @Override
    public void successfullyAccessed(final AdvancedAccountManager account) {
        frame.setMainMenu(account);
    }
    
    @Override
    public JPanel getMenu() {
        return this;
    }

}
