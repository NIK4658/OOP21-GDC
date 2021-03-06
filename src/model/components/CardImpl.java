package model.components;

import java.awt.Image;
import java.util.Objects;
import java.util.Random;
import utility.Pair;
import view.Utilities;

/**
 * Class that models the methods for handling a single card.
 */
public class CardImpl implements Card {

    private final Pair<Suits, Integer> card;
    private boolean facedown;
    private Image img;
    
    /**
     * Generate a card with precise values.
     * 
     * @param s     Suit of the card.
     * @param value Value of the card.
     */
    public CardImpl(final Suits s, final int value) {
        this.card = new Pair<>(s, value);
        this.img = Utilities.getImage("img/cards/" + this.card.getX() + "/" + this.card.getY() + ".png");
        this.facedown = false;
    }
    
    /**
     * Generate a random card.
     */
    public CardImpl() {
        this(getRandomSuit(), getRandomValue());
        this.facedown = false;
    }
    

    /**
     * Generate a card with a precise Suit.
     * 
     * @param s     Suit of the card.
     */
    public CardImpl(final Suits s) {
        this(s, getRandomValue());
    }
    
    /**
     * Generate a card with a precise value.
     * 
     * @param value Value of the card.
     */
    public CardImpl(final int value) {
        this(getRandomSuit(), value);
    }
    
    /**
     * Generate a random card face down.
     * 
     * @param isFacedown    True if the card should be face down, false otherwise.
     */
    public CardImpl(final boolean isFacedown) {
        this(getRandomSuit(), getRandomValue());      
        this.facedown = isFacedown;
    }
    

    @Override
    public Suits getSuit() {
        return this.card.getX(); 
    }
    
    @Override
    public int getValue() {   
        return (this.card.getY() >= 10) ? 10 : this.card.getY();
    }
    
    @Override
    public Pair<Suits, Integer> getCard() {   
        return this.card;
    }
    
    @Override
    public Image getImg() {
        return this.img;
    }
    
    @Override
    public boolean isFaceDown() {
        return this.facedown;
    }
    
    private static Suits getRandomSuit() {
        return Suits.values()[new Random().nextInt(Suits.values().length)];
    }
    
    private static int getRandomValue() {
        return new Random().ints(1, 14).findFirst().getAsInt();
    }
    
    private boolean isRedColored() {
        return (this.card.getX() == Suits.DIAMONDS || this.card.getX() == Suits.HEARTS);
    }
    
    @Override
    public void turnOver() {
        if (this.facedown) {
            this.img = Utilities.getImage("img/cards/" + this.card.getX() + "/" + this.card.getY() + ".png");
            this.facedown = false;
        } else {
            if (isRedColored()) {
                this.img = Utilities.getImage("img/cards/back/red.png");
            } else {
                this.img = Utilities.getImage("img/cards/back/black.png");
            } 
            this.facedown = true;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.card, this.img, this.facedown);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        } 
        if (getClass() != obj.getClass()) {
            return false;
        } 
        final Card other = (Card) obj;
        return Objects.equals(this.card.getX(), other.getSuit()) && Objects.equals(this.card.getY(), other.getValue());
    }

}


