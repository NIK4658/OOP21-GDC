package view.gui;

import java.awt.Dimension;
import java.awt.Frame;

import controller.MenuController;
import model.account.AccountManager;
import view.menu.games.Game.Games;//cambiare nome classe Games

public interface MenuManager {
    
    void setAccessMenu();

    void setMainMenu();
    
    void setAccountMenu();
    //setAccountMenu, roulette, bj come unico metodo setMenu?
    void setRouletteMenu(final AccountManager account, Games game, MenuController menuController);//serve il final nell'interfaccia? controllare
    
    void setBlackjackMenu(final AccountManager accountfinal, MenuController menuController);
    
    void setBaccaratMenu(AccountManager account, MenuController menuController);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();    
    
}
