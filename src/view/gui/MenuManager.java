package view.gui;

import java.awt.Dimension;
import java.awt.Frame;
import model.account.AccountManager;
import view.menu.games.Game.Games;

/**
 * Menumanager manages the various menus and provides some properties of the menu.
 */
public interface MenuManager {
    
    /**
     * Set the access menu which must be accessed with credential.
     * 
     * @param account
     * 
     */
    void setAccessMenu(AccountManager account);

    /**
     * Set the main menu which is set after login .
     * 
     * @param account
     * 
     */
    void setMainMenu(AccountManager account);
    
    /**
     * Set the account menu wich is possible change your account information.
     * 
     * @param account
     * 
     */
    void setAccountMenu(AccountManager account);
    
    /**
     * Set the roulette game passed as an argument.
     * 
     * @param account
     * 
     * @param game
     * 
     */
    void setRouletteMenu(final AccountManager account, Games game);
    
    /**
     * Set the blackjack game.
     * 
     * @param account
     * 
     */
    void setBlackjackMenu(AccountManager account);
    

    /**
     * Set the baccarat game.
     * 
     * @param account
     * 
     */
    void setBaccaratMenu(AccountManager account);
    
    /**
     * Returns the main frame.
     * 
     * @return the frame of menu manager
     */
    Frame getFrame();
 
    /**
     * Returns the width of menu.
     * 
     * @return returns the width of menu
     */
    int getWidthMenu();
    
    /**
     * Returns the height of menu.
     * 
     * @return returns the height of menu
     */
    int getHeightMenu();
    
    /**
     * Returns the size of menu.
     * 
     * @return returns the size of menu
     */
    Dimension getSizeMenu();
    
}
