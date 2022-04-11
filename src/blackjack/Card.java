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

    Suits getSuit();
    
    int getValue();
    
    Image getImg();
    
}


