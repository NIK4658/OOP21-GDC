package model.account;

/**
 * Interface that defines the methods for managing the balance.
 */
public interface BalanceManager {

    /**
     * Increase the balance by the requested amount.
     * 
     * @param amount Amount to be added to the user balance.
     * @return true if any changes have been made, false otherwise. 
     */
    boolean deposit(double amount);

    /**
     * Decrease the balance by the requested amount.
     * 
     * @param amount Amount to be subtracted to the user balance.
     * @return true if any changes have been made, false otherwise. 
     */
    boolean withdraw(double amount);
    
    /**
     * Change the balance of the account.
     * 
     * @param amount New balance of the account.
     * @return true if any changes have been made, false otherwise. 
     */
    boolean changeBalance(double balancenew);
    
    /**
     * Getter of Balance.
     * @return the balance of the account. 
     */
    double getBalance();
    
}
