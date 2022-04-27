package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.Border;

import account.AdvancedAccountManager;
import account.AdvancedAccountManagerImpl;
import account.AdvancedBalanceManager;
import account.AdvancedBalanceManagerImpl;
import account.SimpleAccountManager;

import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import ex.ExImageModifier;
import view.gui.MainGui;


public class Gui extends JPanel{

    private static final long serialVersionUID = 1L;
    
    private int puntata;
    private int chipvalue = 1;
    private Image img = new ImageIcon("res/img/backgrounds/blackjacktableHDwithbet.png").getImage();
    
    private List<JLabel> dcards;
    private List<JLabel> pcards;
    private final Game game;
    
    public Gui(final Dimension dim, final AdvancedBalanceManager account) {
        this.setLayout(new BorderLayout());
        
        game = new GameImpl(account);
        
        //Area Pulsanti in fondo SUD
        final JPanel buttonsArea = new JPanel(new GridBagLayout());
        buttonsArea.setPreferredSize(new Dimension(150, 150));
        buttonsArea.setOpaque(false);

        //codice ripetuto
        final JButton draw = new JButton(); 
        final JButton stand = new JButton();
        final JButton Double = new JButton();
        final JButton reset = new JButton("Reset");      
        
        
        draw.setIcon(new ImageIcon((new ImageIcon("res/img/buttons/plus.png").getImage())
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        draw.setOpaque(false);
        draw.setContentAreaFilled(false);
        draw.setBorderPainted(false);
        draw.setFocusPainted(false);
        
        stand.setIcon(new ImageIcon((new ImageIcon("res/img/buttons/stay.png").getImage())
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        stand.setOpaque(false);
        stand.setContentAreaFilled(false);
        stand.setBorderPainted(false);
        stand.setFocusPainted(false);
        
        Double.setIcon(new ImageIcon((new ImageIcon("res/img/buttons/double.png").getImage())
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        Double.setOpaque(false);
        Double.setContentAreaFilled(false);
        Double.setBorderPainted(false);
        Double.setFocusPainted(false);
        
        final JButton chip1 = new JButton("1"); 
        final JButton chip2 = new JButton("5");
        final JButton chip3 = new JButton("25");
        final JButton chip4 = new JButton("100");    
        final JButton chip5 = new JButton("500");
        
        
        final List<JButton> l = new ArrayList<>();
        l.add(draw);
        l.add(stand);
        l.add(Double);
        l.add(reset);   
        
        //l.add(chip1);
        //l.add(chip2);
        //l.add(chip3);
        //l.add(chip4);
        //l.add(chip5);
        
        
        int i = 0;
        for (final JButton jb : l) { 
            jb.setPreferredSize(new Dimension(110, 100));
            jb.setForeground(Color.BLACK);
            jb.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
            buttonsArea.add(jb, setDimensionObj(i, 0, 0, 10, 10));
            i++;
        }
        
        //aggiungo il jpanel dei pulsanti al jpanel generale
        add(buttonsArea, BorderLayout.SOUTH);

        
        

        //JPanel a layer che mostra le carte del giocatore CENTER
        final JLayeredPane playerCardsPanel = new JLayeredPane();
        //playerCardsPanel.setLayout(null); //da rivedere
        pcards = new LinkedList<>(); //lista di JLabel, ciascuna sarà una carta del giocatore
        
        //Punteggio player
        final JLabel playerpoints = new JLabel();
        playerpoints.setForeground(Color.WHITE);
        playerpoints.setBounds(530, 20, 150, 150);
        playerpoints.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        playerCardsPanel.add(playerpoints, 0);
        
        final JLabel amountbet = new JLabel("0 €");
        amountbet.setForeground(Color.WHITE);
        amountbet.setBounds(350, 20, 150, 150);
        amountbet.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        playerCardsPanel.add(amountbet, 0);
        
        
        final JButton bet = new JButton();
        bet.setBounds(370, 130, 70, 70);
        bet.setOpaque(false);
        bet.setContentAreaFilled(false);
        bet.setBorderPainted(false);
        bet.setFocusPainted(false);
        playerCardsPanel.add(bet, 0);
        
        final JButton resetta = new JButton("Resetta");
        resetta.setBounds(220, 125, 90, 30);
        playerCardsPanel.add(resetta, 0);
        
        final JButton conferma = new JButton("Conferma");
        conferma.setBounds(220, 190, 90, 30);
        playerCardsPanel.add(conferma, 0);
        
        
        //aggiunto il pannello con tutte le carte del player al pannello generale
        add(playerCardsPanel, BorderLayout.CENTER);
        
        
        
        
        
 
        //JPanel a layer che mostra le carte del dealer NORTH
        final JLayeredPane dealerCardsPanel = new JLayeredPane();
        //dealerCardsPanel.setLayout(null); //da rivedere
        dealerCardsPanel.setPreferredSize(new Dimension(300, 300));
        //Lista delle carte del dealer
        dcards = new LinkedList<>();
        final JLabel dpoints = new JLabel();
        dpoints.setForeground(Color.WHITE);
        dpoints.setBounds(530, 20, 150, 150);
        dpoints.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        
        dealerCardsPanel.add(dpoints, 0);
        
        
        final JLabel saldo = new JLabel("Saldo: " + account.getBalance());
        final JLabel puntata = new JLabel("Puntata: " + this.puntata);
        saldo.setForeground(Color.WHITE);
        saldo.setBounds(100, 80, 150, 150);
        saldo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        
        puntata.setForeground(Color.WHITE);
        puntata.setBounds(100, 110, 150, 150);
        puntata.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        
        dealerCardsPanel.add(saldo);
        dealerCardsPanel.add(puntata);
        
        //aggiungo le carte del dealer al pannello generale NORTH
        add(dealerCardsPanel, BorderLayout.NORTH);
        
        
        dpoints.setText("");
        playerpoints.setText("");
        
        
        draw.setEnabled(false);
        stand.setEnabled(false);
        reset.setEnabled(false);
        Double.setEnabled(false);
  
        
       

        //da fare refactoring
        draw.addActionListener(e -> {   
            if (game.getPlayerPoints() < 21) {
                game.askCard();
                addCard(pcards, game.getPlayerHand().getCard(game.getPlayerHand().size() - 1), playerCardsPanel);
                playerpoints.setText(String.valueOf(game.getPlayerPoints()));
            } 
      
            if (game.getPlayerPoints() >= 21) {
                stand.doClick();
            }
        });
        
        //codice ripetuto
        stand.addActionListener(e -> {
            //g.nextDealerMove();
            dcards.get(1).setIcon(new ImageIcon(new CardImpl(game.getDealerHand().getCard(1).getSuit(), 
                    game.getDealerHand().getCard(1).getValue()).getImg()
                    .getScaledInstance(100, 150, Image.SCALE_SMOOTH))); 
            dpoints.setText(String.valueOf(game.getDealerPoints()));
            
            while (game.getDealerPoints() < 17) {
                //aggiunge altre carte nel caso non basti la prima
                game.nextDealerMove();
                addCardDealer(dcards, game.getDealerHand().getCard(game.getDealerHand().size() - 1), dealerCardsPanel);
                dpoints.setText(String.valueOf(game.getDealerPoints()));
            }
            
            //dice chi ha vinto e i punteggi
            saldo.setText("Saldo: " + account.getBalance());
            
            //disattiva i pulsanti
            draw.setEnabled(false);
            stand.setEnabled(false);
            reset.setEnabled(true);
            Double.setEnabled(false);
        });
        
        Double.addActionListener(e -> {   
            if (this.puntata * 2 <= account.getBalance()) {
                this.puntata *= 2;
                amountbet.setText(this.puntata + " €");
                game.askCard();
                addCard(pcards, game.getPlayerHand().getCard(game.getPlayerHand().size() - 1), playerCardsPanel);
                playerpoints.setText(String.valueOf(game.getPlayerPoints()));
                stand.doClick();
            }
        });
        
        chip1.addActionListener(e -> {  
            this.chipvalue = 1;
        });
        
        chip2.addActionListener(e -> {  
            this.chipvalue = 5;
        });
        
        chip3.addActionListener(e -> {  
            this.chipvalue = 25;
        });
        
        chip4.addActionListener(e -> {  
            this.chipvalue = 100;
        });
        
        chip5.addActionListener(e -> {  
            this.chipvalue = 500;
        });
        
        bet.addActionListener(e -> {  
            if ((this.puntata + this.chipvalue) <= account.getBalance()) {
                this.puntata += this.chipvalue;
                bet.removeAll();
                final JPanel jp = new JPanel(new BorderLayout());
                final JLabel punt = new JLabel(String.valueOf(this.puntata), SwingConstants.CENTER);
                punt.setForeground(Color.WHITE);
                jp.setOpaque(false);
                jp.add(punt, BorderLayout.CENTER);
                bet.add(jp, BorderLayout.CENTER);
                bet.setIcon(chooseChip(this.puntata));
                amountbet.setText(this.puntata + " €");
            }
        });
        
        resetta.addActionListener(e -> {  
            this.puntata = 0;
            amountbet.setText(this.puntata + " €");
            bet.removeAll();
            bet.setIcon(null);
        });
       
        reset.addActionListener(e -> {  
            this.puntata = 0;
            amountbet.setText(this.puntata + " €");
            puntata.setText("Puntata: " + this.puntata);
            resetta.setEnabled(true);
            bet.setEnabled(true);
            conferma.setEnabled(true);
            bet.removeAll();
            bet.setIcon(null);
            draw.setEnabled(false);
            stand.setEnabled(false);
            reset.setEnabled(false);
            Double.setEnabled(false);
            
            dpoints.setText("");
            playerpoints.setText("");
            
            
            for (final JLabel j : pcards) {
                playerCardsPanel.remove(j);
            }
            
            for (final JLabel j : dcards) {
                dealerCardsPanel.remove(j);
            }

            dcards = new LinkedList<>();
            pcards = new LinkedList<>();     
            playerCardsPanel.revalidate();
            playerCardsPanel.repaint();
            dealerCardsPanel.revalidate();
            dealerCardsPanel.repaint();
        });
        
        conferma.addActionListener(e -> { 
            if (this.puntata != 0) {
                game.startGame(this.puntata);
                resetta.setEnabled(false);
                bet.setEnabled(false);
                conferma.setEnabled(false);
                
                draw.setEnabled(true);
                stand.setEnabled(true);
                reset.setEnabled(true);
                Double.setEnabled(true);
                
                //prima carta scoperta, seconda coperta
                addCardDealer(dcards, game.getDealerHand().getCard(0), dealerCardsPanel);
                addCardDealer(dcards, game.getDealerHand().getCard(1), dealerCardsPanel);

                addCard(pcards, game.getPlayerHand().getCard(0), playerCardsPanel);
                addCard(pcards, game.getPlayerHand().getCard(1), playerCardsPanel);
                
                puntata.setText("Puntata: " + this.puntata);
                dpoints.setText(String.valueOf(game.getDealerHand().getCard(0).getValue()));
                playerpoints.setText(String.valueOf(game.getPlayerPoints()));
            }
        });
        
        
        //NOTE NICO: FARE UNA FUNZIONE PRIVATA PER AGGIUNGERE CARTE A UNA LISTA DI JPANEL
        
        
    }
    
    
    private ImageIcon chooseChip(final int puntata) {
        if (puntata <  5) {
            final Image img = new ImageIcon("res/img/fiches/empty/1HD2.png").getImage();
            return new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        }
        if (puntata < 25) {
            final Image img = new ImageIcon("res/img/fiches/empty/5.png").getImage();
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        if (puntata < 100) {
            final Image img = new ImageIcon("res/img/fiches/empty/25.png").getImage();
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        if (puntata < 500) {
            final Image img = new ImageIcon("res/img/fiches/empty/100.png").getImage();
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        final Image img = new ImageIcon("res/img/fiches/empty/500.png").getImage();
        return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH)); 
    }


    private void addCard(final List<JLabel> cards, final Card c, final JLayeredPane p) {
        cards.add(new JLabel());
        cards.get((cards.size() - 1)).setBounds(575 + ((cards.size() - 1) * 20),
                80 - ((cards.size() - 1) * 15), 150, 150);  //setbounds temporaneo?
        //setta al JLabel di index i l'immagine della nuova carta.
        cards.get((cards.size() - 1)).setIcon(new ImageIcon(c.getImg()
                .getScaledInstance(103, 150, Image.SCALE_SMOOTH)));
        //aggiunge la carta al pannello al layer 1
        p.add(cards.get((cards.size() - 1)), 0);

    }
    
    //da eliminare
    private void addCardDealer(final List<JLabel> cards, final Card c, final JLayeredPane p) {
        cards.add(new JLabel());
        cards.get((cards.size() - 1)).setBounds(575 + ((cards.size() - 1) * 35),
                80 + ((cards.size() - 1) * 10), 150, 150);  //setbounds temporaneo?
        //setta al JLabel di index i l'immagine della nuova carta.
        cards.get((cards.size() - 1)).setIcon(new ImageIcon(c.getImg()
                .getScaledInstance(103, 150, Image.SCALE_SMOOTH)));
        //aggiunge la carta al pannello al layer 1
        p.add(cards.get((cards.size() - 1)), 0);
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
        
        //new Gui(new Dimension(1280, 720));
        final JFrame jf = new JFrame();
        jf.setResizable(false);
        //jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setPreferredSize(new Dimension(1280, 720)); 
        final AdvancedAccountManager account = new AdvancedAccountManagerImpl();
        account.logger("Username", "Password");
        jf.add(new Gui(new Dimension(1280, 720), new AdvancedBalanceManagerImpl(account)));    
        jf.pack();                                 
        jf.setLocationRelativeTo(null); 
        jf.setVisible(true); 
        
        
        
        //new MainGui();
    }
    
    
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }
}
