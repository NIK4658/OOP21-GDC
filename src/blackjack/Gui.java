package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.*;
import view.ImageModifier;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class Gui extends JFrame{

    private static final long serialVersionUID = 1L;
    
    private int value;
    private int i = 0;
    
    Gui(final Dimension dim) {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        final BackgroundPanel bgpanel = new BackgroundPanel(
                new ImageIcon("res/img/backgrounds/blackjacktable.jpg").getImage(), BackgroundPanel.SCALED, 0.0f, 0.0f);
        final JPanel jl = new JPanel(new GridBagLayout());
        jl.setPreferredSize(new Dimension(150, 150));
        final JButton draw = new JButton("Draw"); 
        final JButton stay = new JButton("Stay"); 
        draw.setPreferredSize(new Dimension(100, 100));
        stay.setPreferredSize(new Dimension(100, 100));
        jl.add(draw, setDimensionObj(0, 0, 0, 0));
        jl.add(stay, setDimensionObj(1, 0, 0, 0));
        bgpanel.add(jl, BorderLayout.SOUTH);

        final JLayeredPane jpan = new JLayeredPane();
        jpan.setLayout(null);
        //jpan.setPreferredSize(new Dimension(150, 250));
        List<JLabel> imgs = new LinkedList();
        imgs.add(new JLabel());
        imgs.get(i).setBounds(575, 380, 150, 150);
        final Card c = new CardImpl(); 
        imgs.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c.getImg(), new Dimension(150, 150))));
        //img.setPreferredSize(new Dimension(150, 150));
        //jpan.setPreferredSize(new Dimension(150, 150));
        jpan.add(imgs.get(i), 1);
        bgpanel.add(jpan, BorderLayout.CENTER); 
        
        //final JPanel jpanel = new JPanel();
        //jpanel.setPreferredSize(new Dimension(150, 350));
        //final JButton draww = new JButton("Draw"); 
        //jpanel.add(draww);
        //bgpanel.add(jpanel, BorderLayout.NORTH); 
        this.i++;
        //this.value += c.getValue();    
        //img.setText(String.valueOf(this.value));    
        //final JLabel jtxt = new JLabel();
        //jtxt.setPreferredSize(new Dimension(100, 100));
        //jtxt.setForeground(Color.WHITE);
        //jtxt.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
        //jl.add(jtxt, setDimensionObj(2, 1, 20, 15));
        //jl.add(jll, setDimensionObj(1, 1, 20, 15));
        //jll.setPreferredSize(new Dimension(150, 150));
     
        setPreferredSize(dim); 
        add(bgpanel);    
        pack();                                 
        setLocationRelativeTo(null); 
        setVisible(true); 
       
        
        
        
        draw.addActionListener(e -> {               
            imgs.add(new JLabel());
            imgs.get(i).setBounds(575 + (this.i * 20), 380 - (this.i * 15), 150, 150);        
            final Card c2 = new CardImpl(); 
            imgs.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c2.getImg(), new Dimension(150, 150))));
            //img.setPreferredSize(new Dimension(150, 150));
            //jpan.setPreferredSize(new Dimension(150, 150));
            jpan.add(imgs.get(i), 0);
            this.i++;
            //bgpanel.add(jpan, BorderLayout.CENTER); 
        });
        
        
        
        
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
        new Gui(new Dimension(1280, 720));
    }
    
}
