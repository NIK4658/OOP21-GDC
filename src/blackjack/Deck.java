package blackjack;

import blackjack.Card.Suits;


/**
 * Interfaccia per classe gestione mazzi.
 */
public interface Deck {
    
    void showAllCards();
    
    void showPreciseCard(Card card);
    
    void showPreciseSuit(Suits s);
    
    void showPreciseValue(int value);
    
    boolean removePreciseCard(Card card);
    
    boolean removeRandomCard();
    
    Card drawPreciseCard(Card card);
    
    Card drawRandomCard();

}
