package account;

/**
 * Interfaccia principale che definisce le funzioni utili alla gestione degli account.
 */
public interface AccountManager {

    boolean logger(String usr, String psw);

    boolean register(String usr, String psw, String eta);

    boolean deposit(double amount);

    boolean withdraw(double amount);

    boolean changeUsr(String usrnew);

    boolean changePass(String psw);
    
    boolean changeBalance(double balancenew);

    boolean deleteAcc();

    boolean checkExisting(String usr);
    
    String getUsr();
    
    String getPsw();
    
    double getBalance();
    
    String getAge();
    

}
