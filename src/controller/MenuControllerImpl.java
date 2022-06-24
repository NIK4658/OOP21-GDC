package controller;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.AdvancedBalanceManagerImpl;
import start.Warning;
import view.gui.MainGui;
import view.gui.MenuManager;
import view.menu.games.Game.Games;

public class MenuControllerImpl implements MenuController {
    
    private final MenuManager gui;
    private AccountManager account;
    
    public MenuControllerImpl() {
        this.gui = new MainGui(this);
        this.gui.setAccessMenu();//creare poi interfaccia della maingui con setVisible
    }
    
    @Override
    public void setAccessMenu() {
        this.gui.setAccessMenu();
    }
    
    @Override
    public void setMainMenu() {
        this.gui.setMainMenu();
    }
    
    @Override
    public void setAccountMenu() {
        this.gui.setAccountMenu();
    }
    
    @Override
    public void setRouletteMenu(final Games game) {
        this.gui.setRouletteMenu(this.account, game, this);
    }
    
    @Override
    public void setBlackjackMenu() {
        this.gui.setBlackjackMenu(this.account, this);
    }
    
    @Override
    public void setBaccaratMenu() {
        this.gui.setBaccaratMenu(this.account, this);
    }

}
