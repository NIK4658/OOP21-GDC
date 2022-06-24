package model.blackjack;

import java.awt.Image;
import utility.Pair;

/**
 * Interface that models the methods for managing a single card.
 */
public interface Card {
    
    /**
     * Enum with the 4 suits of the cards.
     */
    enum Suits {
    CLUBS, DIAMONDS, HEARTS, SPADES
    }

    void turnOver();
    
    Pair<Suits, Integer> getCard();
    
    Suits getSuit();
    
    int getValue();
    
    Image getImg();
    
    boolean isFaceDown();
    
    boolean equals(final Object obj);
    
    int hashCode();
    
}


