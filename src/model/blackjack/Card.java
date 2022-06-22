package model.blackjack;

import java.awt.Image;
import utility.Pair;

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
    
    Pair<Suits, Integer> getCard();
    
    Suits getSuit();
    
    int getValue();
    
    Image getImg();
    
    boolean isFaceDown();
    
    boolean equals(final Object obj);
    
    int hashCode();
    
}


