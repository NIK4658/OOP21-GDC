package model.components;

/**
 * Interface for managing decks of cards.
 */
public interface Deck {
    
    
    /**
     * Fill the deck with cards.
     */
    void generateDeck();
    
    /**
     * Remove a card from a deck.
     * 
     * @param card Card to be removed.
     * @return true if the card has been successfully removed.
     */
    boolean removePreciseCard(Card card);
    
    /**
     * Remove a random card from the deck.
     * 
     * @return true if the card has been successfully removed.
     */
    boolean removeRandomCard();
    
    /**
     * Remove and return a card from the deck.
     * 
     * @param card the card that needs to be removed.
     * @return the card removed.
     */
    Card drawPreciseCard(Card card);
    
    /**
     * Remove and return a random card from the deck.
     * 
     * @return the card removed.
     */
    Card drawRandomCard();
    
    /**
     * Reinitialize the deck with new cards.
     */
    void shuffle();
    
    /**
     * Getter nDecks.
     * @return the numbers of decks.
     */
    int getnDecks();
    
    /**
     * getter size. 
     * @return the size of the deck.
     */
    int size();

}
