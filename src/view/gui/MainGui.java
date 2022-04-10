package view.gui;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JPanel;
import account.AccountManager;

public interface MainGui {
    
    void updateMenu(JPanel panel);
    
    void setAccessMenu();

    void setGamesMenu(AccountManager account);
    
    void setAccountMenu(AccountManager account);
    
    Frame getFrame();
    
    Dimension getSize();
    
    int getHeight();
    
    int getWidth();
    
    
}
