package blackjack;

import java.util.List;

public interface Game {
    
    void askCard();
    
    void stay();
    
    void split();
    
    void askDouble();
    
    void startGame();

    void checkWin();
    
    void dealerDraw();
    
    void dealerStay();
    
    int calculatePoints(List<Card> cards);
    
    void checkDealerMove();

}
