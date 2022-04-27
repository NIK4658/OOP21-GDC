package blackjack;

/**
 * ..
 */
public interface Hand {

    boolean addCard(final Card c);
    
    boolean removeCard(final Card c);
    
    void calculatePoints();
    
    Card getCard(int index);
    
    Hand getHand();
    
    int getPoints();
    
    int size();
    
}
