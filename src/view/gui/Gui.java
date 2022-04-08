package view.gui;

import account.AccountManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.access.AccessMenu;
import view.games.GamesMenu;

public class Gui extends JFrame implements MainGui {
    
//    private static final int SCALE = 2 / 3;

    public Gui() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAccessMenu();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public void updateMenu(final JPanel panel) {
        System.out.println("updateMenu1: " + this.getSize());
        this.setContentPane(panel);
        this.pack();
        this.revalidate();
        System.out.println("updateMenu2: " + this.getSize());
    }
    
    @Override
    public void setAccessMenu() {
        new AccessMenu(this);
    }
    
    @Override
    public void setMainMenu(final AccountManager account) {
        System.out.println("setMainMenu: " + this.getSize());
        new GamesMenu(this, account);
    }

    @Override
    public void setAccountMenu(final AccountManager account) {
        // TODO Auto-generated method stub
    }
}


































