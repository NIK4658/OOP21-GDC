package model.account;

/**
 * Classe principale AVANZATA gestione balance.
 */
public class AdvancedBalanceManagerImpl extends SimpleBalanceManagerImpl implements BalanceManager {
    
    private static final int BALANCELIMIT = 1000000;

    public AdvancedBalanceManagerImpl(final AccountManager username) {
        super(username);
    }

    @Override
    public boolean deposit(final double amount) {
        if (isBalanceValid(getBalance() + amount) && amount > 0) {
            return super.deposit(amount);
        } else {
            //Impossibile depositare "amount", numero non valido;
            return false;
        }
    }

    @Override
    public boolean withdraw(final double amount) {
        if (isBalanceValid(getBalance() - amount) && amount > 0) {
            return super.withdraw(amount);
        } else {
            //Impossibile ritirare "amount", non si dispone di tale cifra;
            return false;
        }
    }
    
    //è necessaria sta funzione o basta quella semplice?
    @Override
    public boolean changeBalance(final double balancenew) {
        if (isBalanceValid(balancenew)) {
            return super.changeBalance(balancenew);
        } else {
            return false;
        }
    }
    
    private boolean isBalanceValid(final double balancenew) {
        return (balancenew >= 0 && balancenew < BALANCELIMIT);
    }
}
