package model.baccarat;
import model.components.Hand;


/**
 * Baccarat game logic interface
 */
public interface BaccaratLogic {
    
  void startGame(final double bet);
    
  void nextMove();

  int checkWin();
    
  void nextDealerMove();
    
  void nextPlayerMove();
    
  void dealerDraw();
    
  void playerDraw();
    
  boolean checkBaccarat(Hand h);
    
  void endGame();
    
  Hand getPlayerHand();
    
  Hand getDealerHand();
    
  int getPlayerPoints();
    
  int getDealerPoints();
  
  double getBet();
    
}
