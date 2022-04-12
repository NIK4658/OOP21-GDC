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
    private int dealer;
    private boolean rotatefirstcard=false;
    private int i = 0;
    private int k = 0;
    
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
        jl.add(draw, setDimensionObj(0, 0, 0, 10, 10));
        jl.add(stay, setDimensionObj(1, 0, 0, 10, 10));
        bgpanel.add(jl, BorderLayout.SOUTH);

        final JLayeredPane jpan = new JLayeredPane();
        jpan.setLayout(null);
        //jpan.setPreferredSize(new Dimension(150, 250));
        final List<JLabel> imgs = new LinkedList();
        imgs.add(new JLabel());
        imgs.get(i).setBounds(575 + (this.i * 20), 80 - (this.i * 15), 150, 150);  
        final Card c = new CardImpl(); 
        imgs.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c.getImg(), new Dimension(150, 150))));
        //img.setPreferredSize(new Dimension(150, 150));
        //jpan.setPreferredSize(new Dimension(150, 150));
        jpan.add(imgs.get(i), 1);
        this.value += c.getValue();    
        this.i++;
        
        imgs.add(new JLabel());
        imgs.get(i).setBounds(575 + (this.i * 20), 80 - (this.i * 15), 150, 150);  
        final Card c7 = new CardImpl(); 
        imgs.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c7.getImg(), new Dimension(150, 150))));
        jpan.add(imgs.get(i), 0);
        this.value += c7.getValue();    
        
        bgpanel.add(jpan, BorderLayout.CENTER); 
        
        //final JPanel jpanel = new JPanel();
        //jpanel.setPreferredSize(new Dimension(150, 350));
        //final JButton draww = new JButton("Draw"); 
        //jpanel.add(draww);
        //bgpanel.add(jpanel, BorderLayout.NORTH); 
        this.i++;
       
        //System.out.println(this.value);
        //img.setText(String.valueOf(this.value));   
        
        final JLayeredPane jpan2 = new JLayeredPane();
        
        jpan2.setLayout(null);
        jpan2.setPreferredSize(new Dimension(300, 300));
        
        final JLabel jtxt = new JLabel();
        //jtxt.setPreferredSize(new Dimension(100, 100));
        jtxt.setForeground(Color.WHITE);
        jtxt.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
        //jtxt.setBounds(575, 380, 150, 150);
        jtxt.setText(String.valueOf(this.value));
        jpan2.add(jtxt);
        
        final List<JLabel> imgsdealer = new LinkedList();
        final JLabel carddealer = new JLabel();
        final Card c4 = new CardImpl(); 
        carddealer.setIcon(new ImageIcon(new ImageModifier().scale(c4.getImg(), new Dimension(150, 150))));
        carddealer.setBounds(575, 80, 150, 150);
        
        this.dealer += c4.getValue();  
        
        final JLabel backdealer = new JLabel();
        backdealer.setIcon(new ImageIcon(new ImageModifier().scale(new CardImpl(false).getImg(),
                new Dimension(150, 150))));
        backdealer.setBounds(610, 90, 150, 150);
        
        
        jpan2.add(backdealer);
        jpan2.add(carddealer);
        bgpanel.add(jpan2, BorderLayout.NORTH);
        
        //jl.add(jtxt, setDimensionObj(2, 1, 20, 15));
        //jl.add(jll, setDimensionObj(1, 1, 20, 15));
        //jll.setPreferredSize(new Dimension(150, 150));
     
        setPreferredSize(dim); 
        add(bgpanel);    
        pack();                                 
        setLocationRelativeTo(null); 
        setVisible(true); 
       
        
        
        
        draw.addActionListener(e -> {   
            if (this.value < 21) {
                imgs.add(new JLabel());
                imgs.get(i).setBounds(575 + (this.i * 20), 80 - (this.i * 15), 150, 150);        
                final Card c2 = new CardImpl(); 
                imgs.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c2.getImg(), new Dimension(150, 150))));
                jpan.add(imgs.get(i), 0);
                this.value += c2.getValue();    
                //System.out.println(this.value);
                //jtxt.setText(String.valueOf(this.value));
                this.i++;
            } 
            
            
            if (this.value >= 21) {
                stay.doClick();
            }
            
        });
        
        stay.addActionListener(e -> {   
            while (this.dealer < 17) {
                if (!this.rotatefirstcard) {
                    this.rotatefirstcard = true;
                    final Card c3 = new CardImpl(); 
                    backdealer.setIcon(new ImageIcon(new ImageModifier().scale(c3.getImg(),
                            new Dimension(150, 150))));
                    this.dealer += c3.getValue();   
                } else {
                    imgsdealer.add(new JLabel());
                    imgsdealer.get(k).setBounds(640 + (this.k * 35), 100 + (this.k * 10), 150, 150);        
                    final Card c2 = new CardImpl(); 
                    imgsdealer.get(k).setIcon(new ImageIcon(new ImageModifier().scale(c2.getImg(),
                            new Dimension(150, 150))));
                    jpan2.add(imgsdealer.get(k), 0);
                    this.dealer += c2.getValue();    
                    //System.out.println(this.dealer);
                    //jtxt.setText(String.valueOf(this.value));
                    this.k++;
                }
            }
            
            System.out.println(checkWin(this.value, this.dealer));
            
            draw.setEnabled(false);
            stay.setEnabled(false);
            
        });
        
        
        
        
    }
    
    
    
    private String checkWin(final int uservalue, final int dealervalue) {
        
        System.out.println("finegioco");
        
        System.out.println("Il tuo punteggio: " + uservalue);
        System.out.println("Il punteggio del dealer: " + dealervalue);
   
        if(uservalue>21) {
            return "hai perso";
        }
        
        if(dealervalue>21) {
            return "hai vinto";
        }
        
        if(uservalue==dealervalue) {
            return "patta";
        }
        if (uservalue > dealervalue) {
            return "hai vinto";
        } else {
            return "hai perso";
        }  
    }
    
    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int spacedown, final int spaceright, final int spaceleft) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, spaceleft, spacedown, spaceright); // terzo parametro definisce la distanza verticale
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
