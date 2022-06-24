package controller;

import model.account.AdvancedBalanceManagerImpl;

public class BalanceControllerImpl {

    
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
    
}
