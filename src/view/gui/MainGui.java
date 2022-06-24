package view.gui;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JFrame;

import controller.MenuController;
import model.account.AccountManager;
import view.Utilities;
import view.menu.AccessMenu;
import view.menu.AccountMenu;
import view.menu.GeneralGui;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.games.Game.Games;
import view.menu.games.GameImpl;


//forse meglio usare un unico metodo setMenu(Menu menu, AccountManager account);
//da settare this.frame.setResizable(false) appena aggiunto torna indietro nei giochi
public class MainGui implements MenuManager {

    private final MenuController menuController;
    private final JFrame frame;
    private final int widthMenu;
    private final int heightMenu;
    private final Dimension sizeMenu;


    public MainGui(final MenuController menuController) {
        this.menuController = menuController;
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
        this.frame.setVisible(true);
    }

    private void updateMenu(final Menu menu) {
        this.frame.setContentPane(menu.getMenu());
        this.frame.pack();
        this.frame.revalidate();
    }

    @Override
    public void setAccessMenu() {
        this.updateMenu(new AccessMenu(this, this.menuController));
    }

    @Override
    public void setMainMenu() {
        this.updateMenu(new MainMenu(this, this.menuController));
    }

    @Override
    public void setAccountMenu() {
        this.updateMenu(new AccountMenu(this, this.menuController));
    }

    //CAMBIARE DA ACCOUNT MANAGER A BALANCE MANAGER (SEMPRE ADVANCED)
    @Override
    public void setRouletteMenu(final AccountManager account, final Games game, final MenuController menuController) {
        this.updateMenu(new GeneralGui(this, account, game, menuController));
    }

    @Override
    public void setBlackjackMenu(final AccountManager account, final MenuController menuController) {
        final GeneralGui g = new GeneralGui(this, account, Games.BLACKJACK, menuController);
        this.updateMenu(new GameImpl(this, g, g.getGame()));
    }

    //CAMBIARE DA ACCOUNT MANAGER A BALANCE MANAGER (SEMPRE ADVANCED)
    @Override
    public void setBaccaratMenu(final AccountManager account, final MenuController menuController) {
        final GeneralGui g = new GeneralGui(this, account, Games.BACCARAT, menuController);
        this.updateMenu(new GameImpl(this, g, g.getGame()));
        
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
