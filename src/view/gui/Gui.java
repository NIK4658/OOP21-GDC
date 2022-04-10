package view.gui;

import account.AccountManager;
import account.AccountManagerImpl;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.access.AccessMenu;
import view.account.AccountMenu;
import view.games.GamesMenu;

public class Gui implements MainGui {
    
//    private static final int SCALE = 2 / 3;
    private final JFrame frame;

    public Gui() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame = new JFrame();
        this.frame.setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setAccountMenu(new AccountManagerImpl());
        
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
    
    @Override
    public void updateMenu(final JPanel panel) {
        System.out.println("updateMenu1: " + this.getSize());
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.revalidate();
        System.out.println("updateMenu2: " + this.getSize());
    }
    
    @Override
    public void setAccessMenu() {
        new AccessMenu(this);
    }
    
    @Override
    public void setGamesMenu(final AccountManager account) {
        System.out.println("setGamesMenu: " + this.getSize());
        new GamesMenu(this, account);
    }

    @Override
    public void setAccountMenu(final AccountManager account) {
        System.out.println("setAccountMenu: " + this.getSize());
        new AccountMenu(this, account);
    }

    @Override
    public Frame getFrame() {
        return this.frame;
    }

    @Override
    public Dimension getSize() {
        return this.frame.getSize();
    }

    @Override
    public int getHeight() {
        return this.frame.getHeight();
    }

    @Override
    public int getWidth() {
        return this.frame.getWidth();
    }
}

