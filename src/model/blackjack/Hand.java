package model.blackjack;

/**
 * ..
 */
public interface Hand {

    boolean addCard(final Card c);
    
    boolean removeCard(final Card c);
    
    void calculatePoints();
    
    Card getCard(int index);
    
    Card getLastCard();
    
    Hand getHand();
    
    int getBaccaratPoints();
    
    int getBlackJackPoints();
    
    int size();
    
}
