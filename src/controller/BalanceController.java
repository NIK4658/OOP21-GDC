package controller;

public interface BalanceController {

    double getBalance();
    
    boolean deposit(double deposit);
    
    boolean withdraw(double withdraw);

    
}
