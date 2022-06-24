package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.account.AccountManager;
import model.blackjack.Card;
import model.blackjack.CardImpl;
import model.blackjack.Card.Suits;

public class CardTest {

    private Card card;
    private final Card.Suits[] suitsValues = Suits.values();

    
    private boolean checkEnum(final Suits s) {
        for (final Suits suits : this.suitsValues) {
            if (suits.name().equals(s.name())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkPoints(final int points) {
        return (points <= 10 && points > 0);
    }
    
    @Test
    public void testGenerateCard() {
        //carta random
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl();
            assertTrue(checkEnum(this.card.getSuit()));
            assertTrue(checkPoints(this.card.getValue()));
            assertFalse(this.card.isFaceDown());
        }
        //Seme preciso
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl(Suits.DIAMONDS);
            assertTrue(Suits.DIAMONDS.name().equals(this.card.getSuit().name()));
            assertTrue(checkPoints(this.card.getValue()));
            assertFalse(this.card.isFaceDown());
        }
        //Valore preciso
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl(5);
            assertTrue(checkEnum(this.card.getSuit()));
            assertEquals(this.card.getValue(), 5);
            assertFalse(this.card.isFaceDown());
        }
        //carta random coperta
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl(true);
            assertTrue(checkEnum(this.card.getSuit()));
            assertTrue(checkPoints(this.card.getValue()));
            assertTrue(this.card.isFaceDown());
        }
        //carta precisa
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl(Suits.DIAMONDS, 5);
            assertTrue(Suits.DIAMONDS.name().equals(this.card.getSuit().name()));
            assertEquals(this.card.getValue(), 5);
            assertFalse(this.card.isFaceDown());
        }
    }
    
    @Test
    public void testGetters() {
        //Uguale a carta precisa
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl(Suits.DIAMONDS, 5);
            assertTrue(Suits.DIAMONDS.name().equals(this.card.getSuit().name()));
            assertEquals(this.card.getValue(), 5);
            assertFalse(this.card.isFaceDown());
        }
    }
    
    @Test
    public void testTurnOver() {
        //Uguale a carta precisa
        for (int i = 0; i <= 100; i++) {
            this.card = new CardImpl(Suits.DIAMONDS, 5);
            assertTrue(Suits.DIAMONDS.name().equals(this.card.getSuit().name()));
            assertEquals(this.card.getValue(), 5);
            assertFalse(this.card.isFaceDown());
            this.card.turnOver();
            assertTrue(this.card.isFaceDown());
        }
    }
    
}
