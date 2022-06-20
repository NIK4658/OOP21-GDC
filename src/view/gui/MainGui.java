package view.gui;

import account.AccountManager;
import baccarat.BaccaratGui;
import account.AdvancedBalanceManagerImpl;
import blackjack.BlackJackGui;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import view.Resizer;
import view.menu.AccessMenu;
import view.menu.AccountMenu;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.games.Game;
import view.menu.games.Game.Games;
import view.menu.games.GameImpl;
import view.menu.games.roulette.RouletteGame;
import view.menu.games.roulette.RouletteGame.TypeRoulette;
import view.menu.GeneralGui;
import view.menu.GeneralGui2;

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
        final Resizer r = new Resizer();
        //final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthMenu =  (r.resize(1.1f)).width;
        this.heightMenu = (r.resize(1.1f)).height;
        
        System.out.println(this.widthMenu);
        System.out.println(this.heightMenu);
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
    public void setMainMenu(final AccountManager account) {
        this.frame.setResizable(false);
        this.updateMenu(new MainMenu(this, account));
    }

    @Override
    public void setAccountMenu(final AccountManager account) {
        this.updateMenu(new AccountMenu(this, account));
    }

    //CAMBIARE DA ACCOUNT MANAGER A BALANCE MANAGER (SEMPRE ADVANCED)
    @Override
    public void setRouletteMenu(final AccountManager account, final TypeRoulette typeRoulette) {
        this.frame.setResizable(true);
        this.updateMenu(new GeneralGui2(this, account, GeneralGui2.Game.ROULETTE, typeRoulette));
        //this.updateMenu(new GameImpl(this, account, new RouletteGame(this.getSizeMenu())));
        //this.updateMenu(new GameMenu(this, account));
    }

    @Override
    public void setBlackjackMenu(final AccountManager account) {
        final GeneralGui g = new GeneralGui(this, account, Games.BLACKJACK);
        this.updateMenu(new GameImpl(this, g, g.getGame()));
    }

    //CAMBIARE DA ACCOUNT MANAGER A BALANCE MANAGER (SEMPRE ADVANCED)
    @Override
    public void setBaccaratMenu(final AccountManager account) {
        this.frame.setResizable(true);
        //this.updateMenu(new GameImpl(this, account, new BaccaratGui(this, new AdvancedBalanceManagerImpl(account))));
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
