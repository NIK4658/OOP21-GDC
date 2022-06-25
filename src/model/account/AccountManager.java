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

    /**
     * Login of the user.
     * 
     * @param usr Username of the user. 
     * @param psw Password of the user.
     * @return true if the login is succesfull, false otherwise.
     */
    boolean logger(String usr, String psw);

    /**
     * Sign up of the user.
     * 
     * @param usr Username of the user. 
     * @param psw Password of the user.
     * @param age Age of the user. 
     * @return true if the sign up is succesfull, false otherwise.
     */
    boolean register(String usr, String psw, String age);
    
    /**
     * Change the username. 
     * 
     * @param usrnew New username of the user.
     * @return true if any changes have been made, false otherwise. 
     */
    boolean changeUsr(String usrnew);

    /**
     * Change the password. 
     * 
     * @param psw New password of the user.
     * @return true if any changes have been made, false otherwise. 
     */
    boolean changePass(String psw);

    /**
     * Delete the account. 
     * 
     * @param usr Username of the account.
     * @return true if the account has been removed, false otherwise. 
     */
    boolean deleteAcc(String usr);

    /**
     * Check if an account is already existing. 
     * 
     * @param usr Username of the account. 
     * @return true if that account is already existing, false otherwise. 
     */
    boolean checkExisting(String usr);
    
    /**
     * Confront the username password with a string. 
     * 
     * @param psw String to confront with the username password.
     * @return true if the two string are the same, false otherwise. 
     */
    boolean isPsw(String psw);
    
    /**
     * Getter of the username.
     * 
     * @return The username of the Account.
     */
    String getUsr();
    
    /**
     * Getter of the password.
     * 
     * @return The password of the Account.
     */
    String getPsw();
    
    /**
     * Getter of the age.
     * 
     * @return The age of the Account.
     */
    String getAge();
    
}
