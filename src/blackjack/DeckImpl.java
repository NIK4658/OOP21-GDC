package blackjack;

import blackjack.Card.Suits;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


/**
 * Classe principale gestione mazzi.
 */
public class DeckImpl implements Deck {
    
    private final Set<Card> deck = new HashSet<>();
    
    /**
     * Genera mazzo.
     */
    public DeckImpl() {
        for (final Suits s : Suits.values()) {
            if (s != Suits.JOKER) {
                for (int i = 1; i <= 13; i++) {
                    deck.add(new CardImpl(s, i));
                }
            }   
        }
    }

    /**
     * Testing.
     */
    @Override
    public void showAllCards() { 
        int i = 1;
        for (final Card c : this.deck) {
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
}
