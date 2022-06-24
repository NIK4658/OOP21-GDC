package controller.blackjack;

import model.blackjack.Hand;

public interface BlackJackController {

    void startGame(final double bet);
    
    void askCard();
    
    void stand();
    
    boolean askDouble();
    
    int checkWin();
    
    boolean canInsurance();
    
    boolean checkInsurance();
    
    boolean calculateInsurance(final boolean insurance);
    
    Hand getPlayerHand();
    
    Hand getDealerHand();
    
    double getLastWin();
    
    boolean checkBlackjack(Hand h);
    
    int getPlayerPoints();
    
    int getDealerPoints();
    
}
