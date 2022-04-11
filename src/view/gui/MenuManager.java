package view.gui;

import account.AccountManager;
import java.awt.Dimension;
import java.awt.Frame;

public interface MenuManager {
    
    void setAccessMenu();

    void setGamesMenu(AccountManager account);
    
    void setAccountMenu(AccountManager account);
    
    void setRouletteMenu(final AccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();
    
}
