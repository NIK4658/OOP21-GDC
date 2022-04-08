package view.gui;

import java.awt.Dimension;
import javax.swing.JPanel;


import account.AccountManager;

public interface MainGui {
    
    void updateMenu(JPanel panel);
    
    void setAccessMenu();

    void setMainMenu(AccountManager account);
    
    void setAccountMenu(AccountManager account);
    
    Dimension getSize();
    
    int getHeight();
    
    int getWidth();
}
