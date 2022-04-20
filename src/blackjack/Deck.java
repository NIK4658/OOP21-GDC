package blackjack;

import blackjack.Card.Suits;


/**
 * Interfaccia per classe gestione mazzi.
 */
public interface Deck {
    
    boolean removePreciseCard(Card card);
    
    boolean removeRandomCard();
    
    Card drawPreciseCard(Card card);
    
    Card drawRandomCard();

}
