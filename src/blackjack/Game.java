package blackjack;


/**
 * Interfaccia principale gestione gioco blackjack.
 */
public interface Game {
    
    void startGame(final int bet);
    
    void askCard();
    
    void stand();
    
    void askDouble();
    
    void split();

    void checkWin();
    
    void nextDealerMove();
    
    void dealerDraw();
    
    Hand getPlayerHand();
    
    Hand getDealerHand();
    
    int getPlayerPoints();
    
    int getDealerPoints();
    
}
