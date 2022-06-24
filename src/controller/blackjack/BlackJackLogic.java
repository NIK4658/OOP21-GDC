package controller.blackjack;

import model.blackjack.Hand;

/**
 * Interface that defines the main methods of the game "Blackjack".
 */
public interface BlackJackLogic {
    
    void startGame(final double bet);
    
    void askCard();
    
    void stand();
    
    boolean askDouble();

    int checkWin();
    
    void nextDealerMove();
    
    void dealerDraw();
    
    boolean canInsurance();
    
    boolean checkInsurance();
    
    boolean calculateInsurance(final boolean insurance);
    
    boolean checkBlackjack(Hand h);
    
    void endGame();
    
    Hand getPlayerHand();
    
    Hand getDealerHand();
    
    int getPlayerPoints();
    
    int getDealerPoints();
    
    double getBet();
    
    double getLastWin();
    
}
