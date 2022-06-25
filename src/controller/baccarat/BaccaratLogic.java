package controller.baccarat;
import model.blackjack.Hand;


/**
 * Baccarat game logic interface
 */

public interface BaccaratLogic {
  
  /**
   * Inizialize game from selected bet
   * @param bet
   */
  void startGame(final double bet);
  
  /**
   * Define next move of the game, calling nextDealerMove and nextPlayerMove
   */
  void nextMove();
  
  /**
   * check who win
   * @return 1 for player wins, -1 for dealer wins and 0 for tie
   */
  int checkWin();
  
  /**
   * Define the next move of the dealer, he can draw or do nothing
   */
  void nextDealerMove();
  
  /**
   * Define the next move of the player, he can draw or do nothing
   */
  void nextPlayerMove();
  
  /**
   * Dealer draw a card
   */
  void dealerDraw();
  
  /**
   * Player draw a card
   */
  void playerDraw();
  
  /**
   * 
   * @param h
   * @return true if the hand in input have baccarat, false otherwise
   */
  boolean checkBaccarat(Hand h);
  
  /**
   * end of the game
   */
  void endGame();
  
  /**
   * 
   * @return Player hand
   */
  Hand getPlayerHand();
  
  /**
   * 
   * @return Dealer hand
   */
  Hand getDealerHand();
  
  /**
   * 
   * @return total player points
   */
  int getPlayerPoints();
  
  /**
   * 
   * @return total dealer points
   */
  int getDealerPoints();
  
  /**
   * 
   * @return the bet set by the player
   */
  double getBet();
    
}
