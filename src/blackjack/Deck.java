package blackjack;

/**
 * Interfaccia per classe gestione mazzi.
 */
public interface Deck {
    
    void generateDeck();
    
    boolean removePreciseCard(Card card);
    
    boolean removeRandomCard();
    
    Card drawPreciseCard(Card card);
    
    Card drawRandomCard();
    
    void shuffle();

}
