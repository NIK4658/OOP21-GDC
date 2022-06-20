package account;

/**
 * Classe principale AVANZATA gestione balance.
 */
public class AdvancedBalanceManagerImpl extends SimpleBalanceManagerImpl implements BalanceManager {

    public AdvancedBalanceManagerImpl(final AccountManager username) {
        super(username);
    }

    @Override
    public boolean deposit(final double amount) {
        if (amount > 0) {
            return super.deposit(amount);
        } else {
            //Impossibile depositare "amount", numero non valido;
            return false;
        }
    }

    @Override
    public boolean withdraw(final double amount) {
        if (getBalance() - amount >= 0 && amount > 0) {
            return super.withdraw(amount);
        } else {
            //Impossibile ritirare "amount", non si dispone di tale cifra;
            return false;
        }
    }
    
    
    //è necessaria sta funzione o basta quella semplice?
    @Override
    public boolean changeBalance(final double balancenew) {
        if (balancenew >= 0) {
            return super.changeBalance(balancenew);
        } else {
            return false;
        }
    }
    
    
}
