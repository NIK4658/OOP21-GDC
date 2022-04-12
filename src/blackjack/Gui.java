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
    
    private int playerValue;
    private int dealerValue;
    private boolean rotatefirstcard = false;
    private int i = 0;
    private int k = 0;
    
    Gui(final Dimension dim) {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //JPanel con immagine di sfondo
        final BackgroundPanel bgpanel = new BackgroundPanel(
                new ImageIcon("res/img/backgrounds/blackjacktable.jpg").getImage(), BackgroundPanel.SCALED, 0.0f, 0.0f);
        
        //Area Pulsanti in fondo SUD
        final JPanel buttonsArea = new JPanel(new GridBagLayout());
        buttonsArea.setPreferredSize(new Dimension(150, 150));
        
        //codice ripetuto
        final JButton draw = new JButton("Draw"); 
        final JButton stay = new JButton("Stay"); 
        draw.setPreferredSize(new Dimension(100, 100));
        stay.setPreferredSize(new Dimension(100, 100)); 
        buttonsArea.add(draw, setDimensionObj(0, 0, 0, 10, 10));
        buttonsArea.add(stay, setDimensionObj(1, 0, 0, 10, 10));
        
        //aggiungo il jpanel dei pulsanti al jpanel generale
        bgpanel.add(buttonsArea, BorderLayout.SOUTH);

        
        //JPanel a layer che mostra le carte del giocatore CENTER
        final JLayeredPane playerCardsPanel = new JLayeredPane();
        playerCardsPanel.setLayout(null); //da rivedere
        final List<JLabel> pCards = new LinkedList<>(); //lista di JLabel, ciascuna sarà una carta del giocatore
        pCards.add(new JLabel());
        pCards.get(i).setBounds(575 + (this.i * 20), 80 - (this.i * 15), 150, 150);  //setbounds temporaneo?
        
        //istanza prima carta giocatore (variabile non final per poterla riusare(?))
        Card c = new CardImpl(); 
        //setta al JLabel di index i l'immagine della nuova carta.
        pCards.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c.getImg(), new Dimension(150, 150))));
        //aggiunge la carta al pannello al layer 1
        playerCardsPanel.add(pCards.get(i), 1);
        
        //punteggio temporaneo giocatore
        this.playerValue += c.getValue();    
        this.i++;
        
        //Creo nuovo Jlabel e lo metto un po' piu spostato
        pCards.add(new JLabel());
        pCards.get(i).setBounds(575 + (this.i * 20), 80 - (this.i * 15), 150, 150);  
        //riutilizzo la variabile
        c = new CardImpl(); 
        //assegno l'immagine e aggiungo al panel nel layer 0
        pCards.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c.getImg(), new Dimension(150, 150))));
        playerCardsPanel.add(pCards.get(i), 0);
        
        //aggiunto il pannello con tutte le carte del player al pannello generale
        bgpanel.add(playerCardsPanel, BorderLayout.CENTER);
        
        //aumento punteggio giocatore
        this.playerValue += c.getValue(); 
        this.i++;
        
        //JPanel a layer che mostra le carte del dealer NORTH
        final JLayeredPane dealerCardsPanel = new JLayeredPane();
        dealerCardsPanel.setLayout(null); //da rivedere
        dealerCardsPanel.setPreferredSize(new Dimension(300, 300));
        
        //Lista delle carte del dealer
        final List<JLabel> dCards = new LinkedList<>();
        
        //prima carta scoperta
        dCards.add(new JLabel());
        c = new CardImpl(); 
        dCards.get(k).setIcon(new ImageIcon(new ImageModifier().scale(c.getImg(), new Dimension(150, 150))));
        dCards.get(k).setBounds(575 + (this.k * 35), 80 + (this.k * 10), 150, 150);
        dealerCardsPanel.add(dCards.get(k), 1);
        
        this.dealerValue += c.getValue(); 
        k++;
        
        //seconda carta coperta
        dCards.add(new JLabel());
        c = new CardImpl(false); //carta coperta 
        dCards.get(k).setIcon(new ImageIcon(new ImageModifier().scale(c.getImg(), new Dimension(150, 150))));
        dCards.get(k).setBounds(575 + (this.k * 35), 80 + (this.k * 10), 150, 150);
        dealerCardsPanel.add(dCards.get(k), 0);    
        
  
        //aggiungo le carte del dealer al pannello generale NORTH
        bgpanel.add(dealerCardsPanel, BorderLayout.NORTH);
     
        
        
        setPreferredSize(dim); 
        add(bgpanel);    
        pack();                                 
        setLocationRelativeTo(null); 
        setVisible(true); 
       

        //da fare refactoring
        draw.addActionListener(e -> {   
            if (this.playerValue < 21) {
                pCards.add(new JLabel());
                pCards.get(i).setBounds(575 + (this.i * 20), 80 - (this.i * 15), 150, 150);        
                final Card c2 = new CardImpl(); 
                pCards.get(i).setIcon(new ImageIcon(new ImageModifier().scale(c2.getImg(), new Dimension(150, 150))));
                playerCardsPanel.add(pCards.get(i), 0);
                this.playerValue += c2.getValue();    
                //System.out.println(this.value);
                //jtxt.setText(String.valueOf(this.value));
                this.i++;
            } 
            
            
            if (this.playerValue >= 21) {
                stay.doClick();
            }
            
        });
        
        //codice ripetuto
        stay.addActionListener(e -> {   
            while (this.dealerValue < 17) {
                if (!this.rotatefirstcard) {
                    //ruota la prima carta prima di aggiungerne altre
                    this.rotatefirstcard = true;
                    final Card c1 = new CardImpl(); 
                    dCards.get(k).setIcon(new ImageIcon(new ImageModifier().scale(c1.getImg(),
                            new Dimension(150, 150))));
                    this.dealerValue += c1.getValue(); 
                    k++;
                } else {
                    //aggiunge altre carte nel caso non basti la prima
                    final Card c2 = new CardImpl();   
                    dCards.add(new JLabel());
                    dCards.get(k).setIcon(new ImageIcon(new ImageModifier().scale(c2.getImg(),
                            new Dimension(150, 150))));
                    dCards.get(k).setBounds(575 + (this.k * 35), 80 + (this.k * 10), 150, 150);
                    dealerCardsPanel.add(dCards.get(k), 0);   
                    this.dealerValue += c2.getValue(); 
                    this.k++;
                }
            }
            
            //dice chi ha vinto e i punteggi
            System.out.println(checkWin(this.playerValue, this.dealerValue));
            
            //disattiva i pulsanti
            draw.setEnabled(false);
            stay.setEnabled(false);
            
        });
        
        //NOTE NICO: FARE UNA FUNZIONE PRIVATA PER AGGIUNGERE CARTE A UNA LISTA DI JPANEL
        
        
    }
    
    
    
    private String checkWin(final int uservalue, final int dealervalue) {
        
        System.out.println("finegioco");
        
        System.out.println("Il tuo punteggio: " + uservalue);
        System.out.println("Il punteggio del dealer: " + dealervalue);
   
        if (uservalue > 21) {
            return "hai perso";
        }
        
        if (dealervalue > 21) {
            return "hai vinto";
        }
        
        if (uservalue == dealervalue) {
            return "patta";
        }
        if (uservalue > dealervalue) {
            return "hai vinto";
        } else {
            return "hai perso";
        }  
    }
    
    private GridBagConstraints setDimensionObj(final int gridx, final int gridy,
            final int spacedown, final int spaceright, final int spaceleft) {
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
