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
    
    //private final List<Card> deck = new ArrayList<>(); //da rimuovere final?
    
    /**
     * Genera mazzo standard (scala) (ogni valore 1-13*4semi).
     */
    public DeckImpl() {   
        for (final Suits s : Suits.values()) {
            //if (s != Suits.JOKER) {
                for (int i = 1; i <= 13; i++) {
                    this.add(new CardImpl(s, i));
                }
            //}   
        }
    }

    
    private void showCards(final List<Card> cards) {
        int i = 1;
        for (final Card c : cards) {
            final JFrame jf = new JFrame();
            jf.setResizable(true);
            final JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            jpanel.add(new JLabel(new ImageIcon(c.getImg()), SwingConstants.CENTER));       
            final JTextArea text = new JTextArea("Seme:" + c.getSuit() + " Valore: " + c.getValue() + " Carta N: " + i);
            jf.setPreferredSize(new Dimension(600, 800));
            jf.pack();                                 
            jf.setLocationRelativeTo(null); 
            jf.add(jpanel);
            jf.add(text, BorderLayout.SOUTH);
            jf.setVisible(true); 
            i++;
        }   
    }
    
    @Override
    public void showAllCards() { 
        showCards(this);
    }

    @Override
    public void showPreciseCard(final Card card) {
        final List<Card> s = new ArrayList<>();
        s.add(card);
        showCards(s);
    }

    @Override
    public void showPreciseSuit(final Suits s) {      
        for (final Card c : this) {
            if (c.getSuit() == s) {
                showPreciseCard(c);
            }
        }    
    }

    @Override
    public void showPreciseValue(final int value) {
        for (final Card c : this) {
            if (c.getValue() == value) {
                showPreciseCard(c);
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
        System.out.println(this.size());
        final Card c = this.get(new Random().ints(0, this.size()).findFirst().getAsInt());
        this.remove(c);
        System.out.println(this.size());
        return c;
    }
}
