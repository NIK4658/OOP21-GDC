package model.account;

/**
 * Interface that defines the methods for managing the balance.
 */
public interface BalanceManager {

    boolean deposit(double amount);

    boolean withdraw(double amount);
    
    boolean changeBalance(double balancenew);
    
    double getBalance();
    
}
