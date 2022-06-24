package controller;

import model.account.AccountManager;
import model.account.AdvancedBalanceManagerImpl;

public class BalanceControllerImpl implements BalanceController{

    private final AccountManager account;
    
    public BalanceControllerImpl(final AccountManager usr) {
        this.account = usr;
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
    public boolean changeBalance(final double balancenew) {
        return new AdvancedBalanceManagerImpl(this.account).changeBalance(balancenew);
    }
    
    
}
