package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.blackjack.Card;
import model.blackjack.CardImpl;
import model.blackjack.Hand;
import model.blackjack.HandImpl;
import model.blackjack.Card.Suits;


public class HandTest {
    
    private final Card.Suits[] suitsValues = Suits.values();
    private Hand hand;
    
    @Before
    public void initHand() {
        this.hand = new HandImpl();
    }
    
    @Test
    public void testAddCard() {
        for (int i = 0; i <= 50; i++) {
            final Card card = new CardImpl();
            assertTrue(this.hand.addCard(card));
            assertEquals(this.hand.getCard(i), card);
            assertEquals(this.hand.size(), i + 1);
        } 
    }
    
    @Test
    public void testRemoveCard() {
        for (int i = 0; i <= 50; i++) {
            final Card card = new CardImpl();
            this.hand.addCard(card);
            assertEquals(this.hand.size(), 1);
            assertTrue(this.hand.removeCard(card));
            assertEquals(this.hand.size(), 0);
        } 
    }
    
    @Test
    public void testCalculatePoints() {
        final Card card = new CardImpl(5);
        for (int i = 0; i <= 50; i++) {
            this.hand.addCard(card);
            this.hand.calculatePoints();
            assertEquals(this.hand.getPoints(), card.getValue() * (i + 1));
        }
        
        this.hand = new HandImpl();
        final Card ace = new CardImpl(1);
        for (int i = 0; i < 10; i++) {
            this.hand.addCard(ace);
            this.hand.calculatePoints();
            assertEquals(this.hand.getPoints(), 11 + (1 * i));
        }
        
        this.hand = new HandImpl();
        final Card king = new CardImpl(13);
        for (int i = 0; i < 10; i++) {
            this.hand.addCard(king);
            this.hand.calculatePoints();
            assertEquals(this.hand.getPoints(), 10 * (i + 1));
        }
    }
    
    @Test
    public void testGetters() {
        this.hand = new HandImpl();
        final Card card = new CardImpl(Suits.DIAMONDS, 5);
        final Card card2 = new CardImpl(Suits.HEARTS, 10);
        this.hand.addCard(card);
        this.hand.addCard(card2);
        assertEquals(this.hand.getCard(0), card);
        assertEquals(this.hand.getLastCard(), card2);
        this.hand.calculatePoints();
        assertEquals(this.hand.getPoints(), 15);
        assertEquals(this.hand.getHand(), this.hand);
        assertEquals(this.hand.size(), 2);
    }
    

}
