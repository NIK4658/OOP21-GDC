package blackjack;

public interface Game {
    
    void askCard();
    
    void stay();
    
    void split();
    
    void askDouble();
    
    void startGame();

    void checkWin();
    
    void dealerDraw();
    
    void dealerStay();
    
    void checkDealerMove();

}
