package model.blackjack;

/**
 * Interface for managing decks of cards.
 */
public interface Deck {
    
    void generateDeck();
    
    boolean removePreciseCard(Card card);
    
    boolean removeRandomCard();
    
    Card drawPreciseCard(Card card);
    
    Card drawRandomCard();
    
    void shuffle();
    
    int getnDecks();
    
    int size();

}
