package baccarat;


/**
 * Interfaccia principale gestione gioco blackjack.
 */
public interface BaccaratLogic {
    
    void startGame(final double bet);
    
    //void askCard();
    
    void stand();
    
    //void askDouble();

    int checkWin();
    
    void nextDealerMove();
    
    void nextPlayerMove();
    
    void dealerDraw();
    
    void playerDraw();
    
    //boolean checkBaccarat();
    
    boolean checkBaccarat(Hand h);
    
    void endGame();
    
    Hand getPlayerHand();
    
    Hand getDealerHand();
    
    int getPlayerPoints();
    
    int getDealerPoints();
    
}
