package blackjack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Gui extends JFrame{

    private static final long serialVersionUID = 1L;

    Gui(final Dimension dim) {
        setResizable(false);
        final BackgroundPanel bgpanel = new BackgroundPanel(new ImageIcon("res/img/backgrounds/blackjacktable.jpg").getImage(), BackgroundPanel.SCALED, 0.0f, 0.0f);   
        final JPanel jl = new JPanel(new GridBagLayout());
        final JButton draw = new JButton("Draw"); 
        final JButton stay = new JButton("Stay"); 
        draw.setPreferredSize(new Dimension(100,100));
        stay.setPreferredSize(new Dimension(100,100));    
        jl.add(draw, setDimensionObj(0, 0, 20, 15));
        jl.add(stay, setDimensionObj(1, 0, 20, 0));
        bgpanel.add(jl, BorderLayout.SOUTH);   
        setPreferredSize(dim); 
        add(bgpanel);    
        pack();                                 
        setLocationRelativeTo(null); 
        setVisible(true); 
    }
    
    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int spacedown, final int space) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, spacedown, space); // terzo parametro definisce la distanza verticale
        //(verso il basso) tra i vari oggetti della gui
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }
    
    /**
     * Testing. Da eliminare in seguito.
     */
    public static void main(final String[] args) {      
        //new DeckImpl().showPreciseValue(5); 
        //new DeckImpl().showPreciseSuit(Suits.DIAMONDS);
        //new DeckImpl().showPreciseCard(new CardImpl());
        //new DeckImpl().showAllCards();
        new Gui(new Dimension(1280,720));
    }
    
}
