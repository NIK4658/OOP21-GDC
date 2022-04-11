package account;

/**
 * Interfaccia principale che definisce le funzioni utili alla gestione degli account.
 */
public interface AccountManager {

    boolean logger(String usr, String psw);

    boolean register(String usr, String psw, String eta);

    boolean deposit(int amount);

    boolean withdraw(int amount, String psw);

    int balanceAmount();

    boolean changeUsr(String usrnew);

    boolean changePass(String psw);
    
    boolean changeBalance(int balancenew);

    boolean deleteAcc();

    boolean checkExisting(String usr);

}
