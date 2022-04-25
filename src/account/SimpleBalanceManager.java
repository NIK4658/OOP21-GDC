package account;

public interface SimpleBalanceManager {

    boolean deposit(double amount);

    boolean withdraw(double amount);
    
    boolean changeBalance(double balancenew);
    
    double getBalance();
    
}
