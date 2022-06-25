package model.blackjack;

import java.util.ArrayList;
import java.util.Random;
import model.blackjack.Card.Suits;

/**
 * Class that implements card deck management methods.
 */
public class DeckImpl extends ArrayList<Card> implements Deck {
    
    private static final long serialVersionUID = 1L;
    private final int ndeck;

    public DeckImpl(final int ndeck) {   
        this.ndeck = ndeck; 
    }
    
    @Override
    public void generateDeck() {
        for (int counter = 1; counter <= this.ndeck; counter++) {
            for (final Suits s : Suits.values()) {
                for (int i = 1; i <= 13; i++) {
                    this.add(new CardImpl(s, i));
                }
            }  
        }  
    }
    
    @Override
    public void shuffle() {
        this.removeAll(this);
        generateDeck();
    }

    @Override
    public boolean removePreciseCard(final Card card) {
        int i = 0;
        for (final Card c : this) {
            if (c.getSuit() == card.getSuit() && c.getCard().getY() == card.getCard().getY()) {
                this.remove(i);
                return true;
            }
            i++;   
        }
        return false;
    }

    @Override
    public boolean removeRandomCard() {
        if (this.size() != 0) {
            return this.remove(this.get(new Random().ints(0, this.size()).findFirst().getAsInt()));
        } else {
            return false;
        }
    }


    @Override
    public Card drawPreciseCard(final Card card) {
        if (this.size() != 0) {
            int i = 0;
            for (final Card c : this) {
                if (c.getSuit() == card.getSuit() && c.getCard().getY() == card.getCard().getY()) {
                    return this.remove(i);
                }
                i++;   
            }
            return null;
        } else {
            return null;
        }
    }


    @Override
    public Card drawRandomCard() {
        if (this.size() != 0) {
            final Card c = this.get(new Random().ints(0, this.size()).findFirst().getAsInt());
            this.remove(c);
            return c;
        } else {
            return null;
        }
    }

    @Override
    public int getnDecks() {
        return this.ndeck;
    }
    
    @Override
    public int size() {
        int counter = 0;
        for (final Card c : this) {
            counter++;
        }
        return counter;
    }  
}
