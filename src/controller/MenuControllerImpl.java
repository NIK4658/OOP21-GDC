package controller;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.AdvancedBalanceManagerImpl;
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

    @Override
    public boolean login(final String username, final String password) {
        account = new AdvancedAccountManagerImpl();
        return account.logger(username, password);
    }
    
    @Override
    public boolean signup(final String username, final String password, final String age) {
        return new AdvancedAccountManagerImpl().register(username, password, age);
    }
    
    @Override
    public double getBalance() {
        return new AdvancedBalanceManagerImpl(this.account).getBalance();
    }
    
    @Override
    public boolean deposit(final double deposit) {
        return new AdvancedBalanceManagerImpl(this.account).deposit(deposit);
    }
    
    @Override
    public boolean withdraw(final double withdraw) {
        return new AdvancedBalanceManagerImpl(this.account).withdraw(withdraw);
    }

    @Override
    public String getUsername() {
        return this.account.getUsr();
    }
    
    @Override
    public boolean changeUsername(final String username) {
        return this.account.changeUsr(username);
    }
    
    @Override
    public boolean isPassword(final String psw) {
        return this.account.isPsw(psw);
    }
    
    @Override
    public boolean setPassword(final String password) {
        return this.account.changePass(password);//cambiare nome metodo NICO in setPsw
    }
    
    @Override
    public boolean deleteAccount() {
        return this.account.deleteAcc(this.getUsername());//NICO per rimuovere l'account non deve servire il nome dell'account
    }


}
