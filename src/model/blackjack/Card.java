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

    /**
     * Rotate card changing the image.
     */
    void turnOver();
    
    /**
     * Getter of the card.
     * 
     * @return Card that is a pair of a suit and an int value
     */
    Pair<Suits, Integer> getCard();
    
    /**
     * Getter Suit.
     * 
     * @return the suit of the card.
     */
    Suits getSuit();
    
    
    /**
     * Getter Value.
     * 
     * @return the points of the card.
     */
    int getValue();
    
    /**
     * Getter Img.
     * 
     * @return the image of the card.
     */
    Image getImg();
    
    /**
     * Getter FaceDown.
     * 
     * @return true if the card is facing down, false otherwise.
     */
    boolean isFaceDown();
    
    
    /**
     * Equals method.
     * 
     * @param obj object that will be compared.
     * @return true if the objects are the same. 
     */
    boolean equals(final Object obj);
    
    /**
     * Generate hashcode.
     * 
     * @return an int hashcode.
     */
    int hashCode();
    
}


