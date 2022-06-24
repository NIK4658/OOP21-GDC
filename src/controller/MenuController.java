package controller;

import view.menu.games.Game.Games;

public interface MenuController {
    
    void setAccessMenu();
      
    void setMainMenu();
      
    void setAccountMenu();
      
    void setRouletteMenu(Games game);
          
    void setBlackjackMenu();
      
    void setBaccaratMenu();


}
