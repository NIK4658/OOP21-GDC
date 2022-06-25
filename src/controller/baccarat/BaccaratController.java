package controller.baccarat;

import model.blackjack.Hand;

/**
 * 
 * Controller interface for baccarat
 *
 */

public interface BaccaratController {
    
	/**
	 * Inizialize the game from selected bet
	 * @param bet
	 */
	void startGame(final double bet);
	
	
    /**
     * Define the next move of the game 
     */
    void nextMove();
    
    /**
     * check who win 
     * @return 1 for player win, -1 for dealer win, 0 for tie
     */
    int checkWin();
    
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
     * @param h
     * @return true if the hand have baccarat, false otherwise
     */
    boolean checkBaccarat(Hand h);
    
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

}
