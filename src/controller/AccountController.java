package controller;

public interface AccountController {

    boolean login(String username, final String password);
    
    boolean signup(String username, String password, String age);
    
    String getUsername();
    
    boolean changeUsername(String username);

    boolean isPassword(String psw);

    boolean setPassword(String password);

    boolean deleteAccount();
    
}
