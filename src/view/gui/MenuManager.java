package view.gui;

import account.SimpleAccountManager;
import java.awt.Dimension;
import java.awt.Frame;

public interface MenuManager {
    
    void setAccessMenu();

    void setMainMenu(SimpleAccountManager account);
    
    void setAccountMenu(SimpleAccountManager account);
    
    void setGameMenu(final SimpleAccountManager account);
    
    void setBlackjackMenu(final SimpleAccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();

    
    
}
