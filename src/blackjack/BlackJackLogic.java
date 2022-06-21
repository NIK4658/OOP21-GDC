package blackjack;


/**
 * Interfaccia principale gestione gioco blackjack.
 */
public interface BlackJackLogic {
    
    void startGame(final double bet);
    
    void askCard();
    
    void stand();
    
    void askDouble();

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
    
    double getLastWin();
    
}
