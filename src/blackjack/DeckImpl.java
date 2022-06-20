package blackjack;

import blackjack.Card.Suits;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe principale gestione mazzi.
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
        return (this.remove(card));
    }

    @Override
    public boolean removeRandomCard() {
        return (this.remove(new CardImpl()));
    }


    @Override
    public Card drawPreciseCard(final Card card) {
        this.remove(card);
        return card;
    }


    @Override
    public Card drawRandomCard() {
        final Card c = this.get(new Random().ints(0, this.size()).findFirst().getAsInt());
        this.remove(c);
        return c;
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
