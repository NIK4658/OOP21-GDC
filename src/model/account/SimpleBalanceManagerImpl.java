package model.account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import model.account.AccountManager.Fields;


/**
 * Classe principale SEMPLICE gestione balance.
 */
public class SimpleBalanceManagerImpl implements BalanceManager {

    private final AccountManager username;
    
    public SimpleBalanceManagerImpl(final AccountManager username) {
        this.username = username;
    }

    @Override
    public boolean deposit(final double amount) {
        return changeBalance(getBalance() + amount);
    }

    @Override
    public boolean withdraw(final double amount) {
        return changeBalance(getBalance() - amount);
    }
    
    @Override
    public boolean changeBalance(final double balancenew) {
        return ManagerUtility.changeField(Fields.BALANCE, String.valueOf((new BigDecimal(balancenew)
                .setScale(2, RoundingMode.HALF_UP)).doubleValue()), username.getUsr(), username.getUsr());
    }
    
    @Override
    public double getBalance() { 
        return Double.valueOf(ManagerUtility.getField(Fields.BALANCE, username.getUsr()));
    }
    
    protected AccountManager getUsername() {
        return this.username;
    }
}
