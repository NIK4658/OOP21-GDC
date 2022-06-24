package controller.baccarat;

import model.blackjack.Hand;

public interface BaccaratController {
    
	void startGame(final double bet);
    
    void NextMove();
    
    int checkWin();
    
    Hand getPlayerHand();
    
    Hand getDealerHand();
    
    boolean checkBaccarat(Hand h);
    
    int getPlayerPoints();
    
    int getDealerPoints();

}
