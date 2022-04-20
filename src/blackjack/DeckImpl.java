package blackjack;

import blackjack.Card.Suits;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


/**
 * Classe principale gestione mazzi.
 */
public class DeckImpl extends ArrayList<Card> implements Deck {
    
    private static final long serialVersionUID = 1L;

    /**
     * Genera mazzo standard (scala) (ogni valore (1-13)*4semi).
     */
    public DeckImpl(final int ndeck) {   
        for (int counter = 1; counter <= ndeck; counter++) {
            for (final Suits s : Suits.values()) {
                for (int i = 1; i <= 13; i++) {
                    this.add(new CardImpl(s, i));
                }
            }  
        }     
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
}
