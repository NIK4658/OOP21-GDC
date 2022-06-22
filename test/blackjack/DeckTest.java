package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import blackjack.Card.Suits;
import utility.Pair;


public class DeckTest {

    private Deck deck;
    private int ndeck;
    
    @Before
    public void initAccount() {
        this.deck = new DeckImpl(6);
    }
    
    private void GenerateDeck(final int i) {
        this.ndeck = i;
        this.deck = new DeckImpl(ndeck);
        this.deck.generateDeck();
        this.deck.shuffle();
    }
    
    private int checknCardContained(final Card c) {
        int k = 0;
        for (int i = 0; i < this.deck.size(); i++) {
            if ((((ArrayList<Card>) this.deck).get(i).getCard().getY() == c.getCard().getY() 
                    && ((ArrayList<Card>) this.deck).get(i).getSuit() == c.getSuit())) {
                k++;
            }
        }
        return k;
    }
    
    
    @Test
    public void testGenerateDeck() {
        for (int i = 0; i <= 25; i++) {
            GenerateDeck(i);
            assertEquals(this.deck.size(), ndeck * 13 * 4);
        }
    }
    
    @Test
    public void testShuffle() {
        GenerateDeck(6);
        this.deck.drawRandomCard();
        assertTrue(this.deck.removeRandomCard());
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 2);
        this.deck.shuffle();
        assertEquals(this.deck.size(), ndeck * 13 * 4);
    }
    
    @Test
    public void testRemoveCards() {
        GenerateDeck(6);
        assertEquals(this.deck.size(), (ndeck * 13 * 4));
        assertTrue(this.deck.removeRandomCard());
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 1);
        assertTrue(this.deck.removeRandomCard());
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 2);
        GenerateDeck(6);
        final Card cardremoved = new CardImpl(Suits.DIAMONDS, 11);
        //System.out.println(checknCardContained(cardremoved));
        for (int i = 0; i <= 5; i++) {
            assertTrue(this.deck.removePreciseCard(cardremoved));
            assertEquals(this.deck.size(), (ndeck * 13 * 4) - i - 1);
            //System.out.println(checknCardContained(cardremoved));
            assertEquals(checknCardContained(cardremoved), 5 - i);
        }
        assertFalse(this.deck.removePreciseCard(cardremoved));
        assertEquals(checknCardContained(cardremoved), 0);
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 6);
    }
    
    @Test
    public void testDrawCards() {
        GenerateDeck(6);
        assertEquals(this.deck.size(), (ndeck * 13 * 4));
        this.deck.drawRandomCard();
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 1);
        this.deck.drawRandomCard();
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 2);
        
        GenerateDeck(6);
        final Card cardremoved = new CardImpl(Suits.DIAMONDS, 11);
        System.out.println(checknCardContained(cardremoved));
        for (int i = 0; i <= 5; i++) {
            Card card2 = this.deck.drawPreciseCard(cardremoved);
            assertEquals(card2.getSuit(), Suits.DIAMONDS);
            assertSame(card2.getCard().getY(), 11);
            System.out.println(checknCardContained(cardremoved));
            assertEquals(this.deck.size(), (ndeck * 13 * 4) - i - 1);
            assertEquals(checknCardContained(cardremoved), 5 - i);
        }
        assertEquals(this.deck.drawPreciseCard(cardremoved), null);
        assertEquals(checknCardContained(cardremoved), 0);
        assertEquals(this.deck.size(), (ndeck * 13 * 4) - 6);
    }
}
