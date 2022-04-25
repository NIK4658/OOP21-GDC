package account;

/**
 * Interfaccia principale che definisce le funzioni utili alla gestione degli account.
 */
public interface SimpleAccountManager {
    
    enum Fields {
        USERNAME, PASSWORD, BALANCE, AGE
        }

    boolean logger(String usr, String psw);

    boolean register(String usr, String psw, String eta);

    boolean changeUsr(String usrnew);

    boolean changePass(String psw);

    boolean deleteAcc();

    boolean checkExisting(String usr);
    
    String getUsr();
    
    String getPsw();
    
    String getAge();
    
}
