package view.gui;

import account.AdvancedAccountManager;
import account.AdvancedAccountManagerImpl;
import account.AdvancedBalanceManagerImpl;
import account.SimpleAccountManager;
import account.SimpleAccountManagerImpl;
import blackjack.Gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.menu.AccessMenu;
import view.menu.AccountMenu;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.GameMenu;

//forse meglio usare un unico metodo setMenu(Menu menu, AccountManager account);
//da settare this.frame.setResizable(false) appena aggiunto torna indietro nei giochi
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
        //this.setGameMenu(new AdvancedAccountManagerImpl());
        //this.setAccountMenu(new AdvancedAccountManagerImpl());
        
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
    
    private void updateMenu(final Menu menu) {
        this.frame.setContentPane(menu.getMenu());
        this.frame.pack();
        this.frame.revalidate();
    }
    
    @Override
    public void setAccessMenu() {
        this.updateMenu(new AccessMenu(this));
    }
    
    @Override
    public void setMainMenu(final AdvancedAccountManager account) {
        this.updateMenu(new MainMenu(this, account));
    }

    @Override
    public void setAccountMenu(final AdvancedAccountManager account) {
        this.updateMenu(new AccountMenu(this, account));
    }
    
    @Override
    public void setGameMenu(final AdvancedAccountManager account) {
        this.frame.setResizable(true);
        this.updateMenu(new GameMenu(this, account));
    }
    
    @Override
    public void setBlackjackMenu(final AdvancedAccountManager account) {
        System.out.println("setBlackjackMenu: " + this.getSizeMenu());
        new Gui(new Dimension(1280, 720), new AdvancedBalanceManagerImpl(account));
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

