package view.gui;

import account.AccountManager;
import view.menu.games.Game.Games;

import java.awt.Dimension;
import java.awt.Frame;

public interface MenuManager {
    
    void setAccessMenu();

    void setMainMenu(AccountManager account);
    
    void setAccountMenu(AccountManager account);
    
    void setRouletteMenu(final AccountManager account, Games game);//serve il final nell'interfaccia? controllare
    
    void setBlackjackMenu(final AccountManager account);
    
    void setBaccaratMenu(AccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();

    

    
    
}
