package blackjack;

import java.awt.Image;

/**
 * Interfaccia per classe gestione carte.
 */
public interface Card {
    
    /**
     * Enum.
     */
    enum Suits {
    CLUBS, DIAMONDS, HEARTS, SPADES
    }

    void turnOver();
    
    Suits getSuit();
    
    int getValue();
    
    Image getImg();
    
    boolean isFaceDown();
    
    boolean equals(final Object obj);
    
    int hashCode();
    
}


