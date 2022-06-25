package view.gui;

import java.awt.Dimension;
import java.awt.Frame;
import model.account.AccountManager;
import model.account.BalanceManager;
import view.menu.games.Game.Games;//cambiare nome classe Games

public interface MenuManager {
    
    void setAccessMenu(AccountManager account);

    void setMainMenu(AccountManager account);
    
    void setAccountMenu(AccountManager account);
    
    //setGameMenu, roulette, bj come unico metodo setMenu?
    void setRouletteMenu(final AccountManager account, Games game);
    
    void setBlackjackMenu(AccountManager account);
    
    void setBaccaratMenu(AccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();
    
}
