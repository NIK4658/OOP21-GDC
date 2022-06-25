package view.gui;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JFrame;
import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import view.Utilities;
import view.menu.AccessMenu;
import view.menu.AccountMenu;
import view.menu.GeneralGui;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.games.Game.Games;
import view.menu.games.MergedGame;

<<<<<<< Updated upstream


/**
 * 
 * Main menu gui 
 *
=======
/**
 * The main gui that manages the menu.
>>>>>>> Stashed changes
 */
public class MainGui implements MenuManager {

    private final JFrame frame;
    private final int widthMenu;
    private final int heightMenu;
    private final Dimension sizeMenu;

<<<<<<< Updated upstream
/**
 * Constructor
 */
=======
    /**
     * Create the main gui that manages the menu.
     */
>>>>>>> Stashed changes
    public MainGui() {
        this.frame = new JFrame();
        this.widthMenu =  (Utilities.resize(1.5f)).width;
        this.heightMenu = (Utilities.resize(1.5f)).height;
        this.sizeMenu = new Dimension(this.widthMenu, this.heightMenu);
        this.frame.setSize(this.sizeMenu);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("GIOCHI DEL COLOSSO");
        this.frame.setIconImage(Utilities.getImage("img/logo/gdclogo3.png"));
        this.frame.setLocationRelativeTo(null);
        this.setAccessMenu(new AdvancedAccountManagerImpl());
        this.frame.setVisible(true);
    }

    @Override
    public void setAccessMenu(final AccountManager account) {
        this.updateMenu(new AccessMenu(this, account));
    }

    @Override
    public void setMainMenu(final AccountManager account) {
        this.updateMenu(new MainMenu(this, account));
    }

    @Override
    public void setAccountMenu(final AccountManager account) {
        this.updateMenu(new AccountMenu(this, account));
    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Override
    public void setRouletteMenu(final AccountManager account, final Games game) {
        this.updateMenu(new GeneralGui(this, account,  game));
    }

    @Override
    public void setBlackjackMenu(final AccountManager account) {
        final GeneralGui g = new GeneralGui(this, account, Games.BLACKJACK);
        this.updateMenu(new MergedGame(this, g, g.getGame()));
    }

    @Override
    public void setBaccaratMenu(final AccountManager account) {
        final GeneralGui g = new GeneralGui(this, account, Games.BACCARAT);
        this.updateMenu(new MergedGame(this, g, g.getGame()));
        
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
    

    private void updateMenu(final Menu menu) {
        this.frame.setContentPane(menu.getMenu());
        this.frame.pack();
        this.frame.revalidate();
    }

}
