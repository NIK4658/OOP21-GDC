package account;

public interface BalanceManager {

    boolean deposit(double amount);

    boolean withdraw(double amount);
    
    boolean changeBalance(double balancenew);
    
    double getBalance();
    
}
