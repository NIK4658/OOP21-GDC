package blackjack;


/**
 * Interfaccia principale gestione gioco blackjack.
 */
public interface Game {
    
    void startGame(final double bet);
    
    void askCard();
    
    void stand();
    
    void askDouble();
    
    void split();

    int checkWin();
    
    void nextDealerMove();
    
    void dealerDraw();
    
    boolean checkInsurance();
    
    boolean checkBlackjack(Hand h);
    
    void endGame();
    
    Hand getPlayerHand();
    
    Hand getDealerHand();
    
    int getPlayerPoints();
    
    int getDealerPoints();
    
}
