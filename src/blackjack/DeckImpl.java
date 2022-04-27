package blackjack;

import blackjack.Card.Suits;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe principale gestione mazzi.
 */
public class DeckImpl extends ArrayList<Card> implements Deck {
    
    private final int ndeck;
    
    private static final long serialVersionUID = 1L;

    public DeckImpl(final int ndeck) {   
        this.ndeck = ndeck; 
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
    public void shuffle() {
        this.removeAll(this);
        generateDeck();
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
}
