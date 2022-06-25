package model.blackjack;

/**
 * Interface that manages the methods of managing the hands of the players and the dealer.
 */
public interface Hand {

    
    /**
     * Add a card to the hand. 
     * 
     * @param c card that will be added.
     * @return true if the card has been added, false otherwise.
     */
    boolean addCard(final Card c);
    
    /**
     * Remove a card to the hand.
     * 
     * @param c card that will be removed.
     * @return true if the card has been removed, false otherwise. 
     */
    boolean removeCard(final Card c);
    
    
    /**
     * Calculate the points of the hand.
     */
    void calculatePoints();
    
    /**
     * Getter card.
     * 
     * @param index index of the card in the hand.
     * @return the card requested.
     */
    Card getCard(int index);
    
    /**
     * Getter the last card of the hand.
     * 
     * @return the card requested.
     */
    Card getLastCard();
    
    /**
     * Getter of the hand. 
     * 
     * @return the whole hand. 
     */
    Hand getHand();
    
    /**
     * Getter of points for Baccarat Game. 
     * 
     * @return an int with baccarat points.
     */
    int getBaccaratPoints();
    
    /**
     * Getter of points for Blackjack Game. 
     * 
     * @return an int with Blackjack points.
     */
    int getBlackJackPoints();
    
    /**
     * Getter size of the hand.
     * 
     * @return amount of elements in the hand. 
     */
    int size();
    
}
