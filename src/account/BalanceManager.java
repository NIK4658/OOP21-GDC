package account;

/**
 * Interfaccia principale che definisce le funzioni utili alla gestione del saldo.
 */
public interface BalanceManager {

    boolean deposit(double amount);

    boolean withdraw(double amount);
    
    boolean changeBalance(double balancenew);
    
    double getBalance();
    
}
