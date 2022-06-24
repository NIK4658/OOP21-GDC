package model.account;

/**
 * Interface that defines methods for managing accounts.
 */
public interface AccountManager {
    
    /**
     * Fields of all users.
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
