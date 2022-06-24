package controller;

import view.menu.games.Game.Games;

public interface MenuController {
    
    void setAccessMenu();
      
    void setMainMenu();
      
    void setAccountMenu();
      
    void setRouletteMenu(Games game);
          
    void setBlackjackMenu();
      
    void setBaccaratMenu();

    boolean login(String username, final String password);
    
    boolean signup(String username, String password, String age);

    double getBalance();
    
    boolean deposit(double deposit);
    
    boolean withdraw(double withdraw);

    String getUsername();
    
    boolean changeUsername(String username);

    String getPassword();

    boolean setPassword(String password);

    boolean deleteAccount();

}
