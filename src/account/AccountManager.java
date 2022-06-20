package account;

/**
 * Interfaccia principale che definisce le funzioni utili alla gestione degli account.
 */
public interface AccountManager {
    
    /**
     * Campi di ogni account.
     */
    enum Fields {
        USERNAME, PASSWORD, BALANCE, AGE
    }

    boolean logger(String usr, String psw);

    boolean register(String usr, String psw, String eta);

    boolean changeUsr(String usrnew);

    boolean changePass(String psw);

    boolean deleteAcc(String usr);

    boolean checkExisting(String usr);
    
    String getUsr();
    
    String getPsw();
    
    String getAge();
    
}
