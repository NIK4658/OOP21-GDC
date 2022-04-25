package account;

public interface SimpleBalanceManager {
    
    enum Fields {
        USERNAME, PASSWORD, BALANCE, AGE
        }


    boolean deposit(double amount);

    boolean withdraw(double amount);
    
    boolean changeBalance(double balancenew);
    
    double getBalance();
    
}
