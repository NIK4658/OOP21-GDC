package model.account;

/**
 * ADVANCED class for balance management, includes preventive checks in each method.
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
            return false;
        }
    }

    @Override
    public boolean withdraw(final double amount) {
        if (isBalanceValid(getBalance() - amount) && amount > 0) {
            return super.withdraw(amount);
        } else {
            return false;
        }
    }
    
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
