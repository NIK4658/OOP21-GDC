package controller;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.AdvancedBalanceManagerImpl;
import model.account.BalanceManager;
import view.menu.GeneralGui;

public class GeneralGameController {

    protected final GeneralGui gui;
    private final BalanceManager balanceManager;
    protected double bet; 
    
    public GeneralGameController(final GeneralGui g) {
        this.gui = g;
        this.balanceManager = new AdvancedBalanceManagerImpl(new AdvancedAccountManagerImpl());
        setBet(0);
    }
    
    
    public void showWinMessage(final double value) {
        if(value>0) {
            this.gui.setWinMessage(value);
            this.balanceManager.deposit(value);
            this.gui.updateBalanceValue(value);
        } else {
            this.gui.removeWinMessage();
        }
        setBet(0);
        this.gui.setBetValue(0);
        
    }
    
    public void addBetValue(final double value) {
        if (balanceManager.withdraw(value)) {
            setBet(this.bet += value);
            this.gui.setBetValue(this.bet);
            this.gui.updateBalanceValue(this.balanceManager.getBalance());
        }
        
    }
    
    public void updateBalanceValue() {
        this.gui.updateBalanceValue(this.balanceManager.getBalance());
    }
    
    public void reset() {
        this.balanceManager.deposit(bet);
        setBet(0);
        this.gui.updateBalanceValue(this.balanceManager.getBalance());
        this.gui.setBetValue(bet);
    }
    
    public void setBetValue(final double bet) {
    this.gui.setBetValue(bet);
    }

    
    public void showButtons(final boolean val) {
        this.gui.showButtons(val);
    }
    
    
}
