package account;

import account.SimpleAccountManager.Fields;


/**
 * Classe principale SEMPLICE gestione balance.
 */
public class SimpleBalanceManagerImpl implements SimpleBalanceManager {

    protected final AdvancedAccountManager username;
    
    public SimpleBalanceManagerImpl(final AdvancedAccountManager username) {
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
        return Utility.changeField(Fields.BALANCE, String.valueOf(balancenew), username.getUsr(), username.getUsr());
    }
    
    @Override
    public double getBalance() { 
        return Double.valueOf(Utility.getField(Fields.BALANCE, username.getUsr()));
    }
}
