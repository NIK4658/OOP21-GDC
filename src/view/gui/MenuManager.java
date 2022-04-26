package view.gui;

import account.AdvancedAccountManager;
import account.SimpleAccountManager;
import java.awt.Dimension;
import java.awt.Frame;

public interface MenuManager {
    
    void setAccessMenu();

    void setMainMenu(AdvancedAccountManager account);
    
    void setAccountMenu(AdvancedAccountManager account);
    
    void setGameMenu(final AdvancedAccountManager account);
    
    void setBlackjackMenu(final AdvancedAccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();

    
    
}
