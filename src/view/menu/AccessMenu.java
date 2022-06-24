package view.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MenuController;
import model.account.AccountManager;
import start.Warning;
import view.Utilities;
import view.gui.MenuManager;
import view.menu.access.Access;
import view.menu.access.AccessPanel;

/**
 * //DA SISTEMARE I MAGIC NUMBERS.
 */
public class AccessMenu extends JPanel implements Access, Menu {

    private static final long serialVersionUID = 1L;
    private final MenuController viewController;
    
    /**
     * //DA SISTEMARE I MAGIC NUMBERS.
     */
    public AccessMenu(final MenuManager frame, final MenuController menuController) {
        this.viewController = menuController;
        this.setLayout(new BorderLayout());
        
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
                e -> cl.show(west, AccessType.REGISTER.toString()), menuController);
        final AccessPanel register = new AccessPanel(this, dimAccessPanel, AccessType.REGISTER, 
                e -> cl.show(west, AccessType.LOGIN.toString()), menuController);
        west.add(login, AccessType.LOGIN.toString());
        west.add(register, AccessType.REGISTER.toString());
        cl.show(west, AccessType.LOGIN.toString());
        this.add(west, BorderLayout.WEST);
        this.setFocusable(true);
    }
    
    @Override
    public void successfullyAccessed() {
        viewController.setMainMenu();
        new Warning();
    }
    
    @Override
    public JPanel getMenu() {
        return this;
    }

}
