package view.gui;

import java.awt.Dimension;
import java.awt.Frame;

import controller.MenuController;
import model.account.AccountManager;
import view.menu.games.Game.Games;//cambiare nome classe Games

public interface MenuManager {
    
    void setAccessMenu();

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

    void setAccountMenu();    
    
}
