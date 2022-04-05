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
    CLUBS, DIAMONDS, HEARTS, SPADES, JOKER
    }

    Suits getSuit();
    
    int getValue();
    
    void setImg();
    
    Image getImg();
    
}


