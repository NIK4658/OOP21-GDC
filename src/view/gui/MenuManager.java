package view.gui;

import account.AdvancedAccountManager;
import account.SimpleAccountManager;
import view.menu.games.roulette.RouletteGame.TypeRoulette;

import java.awt.Dimension;
import java.awt.Frame;

public interface MenuManager {
    
    void setAccessMenu();

    void setMainMenu(AdvancedAccountManager account);
    
    void setAccountMenu(AdvancedAccountManager account);
    
    void setRouletteMenu(final AdvancedAccountManager account, TypeRoulette typeRoulette);//serve il final nell'interfaccia? controllare
    
    void setBlackjackMenu(final AdvancedAccountManager account);
    
    void setBaccaratMenu(AdvancedAccountManager account);
    
    Frame getFrame();
 
    int getWidthMenu();
    
    int getHeightMenu();
    
    Dimension getSizeMenu();

    

    
    
}
