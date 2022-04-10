package view.gui;

import account.AccountManager;
import account.AccountManagerImpl;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.menu.AccessMenu;
import view.menu.AccountMenu;
import view.menu.GamesMenu;

public class MainGui implements MenuManager {
    
//    private static final int SCALE = 2 / 3;
    private final JFrame frame;
    private final int widthMenu;
    private final int heightMenu;
    private final Dimension sizeMenu;

    //vedere se meglio aggiungere campo private final AccountManager account;
    public MainGui() {
        this.frame = new JFrame();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthMenu = screenSize.width * 2 / 3;
        this.heightMenu = screenSize.height * 2 / 3;
        this.sizeMenu = new Dimension(this.widthMenu, this.heightMenu);
        this.frame.setSize(this.sizeMenu);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setAccessMenu();
//        this.setAccountMenu(new AccountManagerImpl());
        
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
    
    private void updateMenu(final JPanel panel) {
        System.out.println("updateMenu1: " + this.getSizeMenu());
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.revalidate();
        System.out.println("updateMenu2: " + this.getSizeMenu());
    }
    
    @Override
    public void setAccessMenu() {
        this.updateMenu(new AccessMenu(this).getMenu());
    }
    
    @Override
    public void setGamesMenu(final AccountManager account) {
        System.out.println("setGamesMenu: " + this.getSizeMenu());
        this.updateMenu(new GamesMenu(this, account).getMenu());
    }

    @Override
    public void setAccountMenu(final AccountManager account) {
        System.out.println("setAccountMenu: " + this.getSizeMenu());
        this.updateMenu(new AccountMenu(this, account).getMenu());
    }

    @Override
    public Frame getFrame() {
        return this.frame;
    }

    @Override
    public int getWidthMenu() {
        return this.widthMenu;
    }

    @Override
    public int getHeightMenu() {
        return this.heightMenu;
    }
    
    @Override
    public Dimension getSizeMenu() {
        return this.sizeMenu;
    }

}

