package view.gui;

import java.awt.Dimension;
import java.awt.Frame;

import model.account.AccountManager;
import view.menu.games.Game.Games;//cambiare nome classe Games

public interface MenuManager {
    
    void setAccessMenu();

    void setMainMenu(AccountManager account);
    
    void setAccountMenu(AccountManager account);
    //setAccountMenu, roulette, bj come unico metodo setMenu?
    void setRouletteMenu(final AccountManager account, Games game);//serve il final nell'interfaccia? controllare
    
    void setBlackjackMenu(final AccountManager account);
    
    void setBaccaratMenu(AccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();    
    
}
