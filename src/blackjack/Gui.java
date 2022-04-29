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

/**
 * GUI principale Blackjack.
 */
public class Gui extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private int puntata;
    private int chipvalue = 1;
    private final Image img = new ImageIcon("res/img/backgrounds/blackjacktableHDwithbet.png").getImage();
    
    private List<JLabel> dcards;
    private List<JLabel> pcards;
    private final Game game;
    
    /**
     * Costruttore.
     */
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
        final JButton reset = new JButton();      
        
        final List<String> s = new ArrayList<>();
        s.add("restart");
        s.add("plus");
        s.add("stay");
        s.add("double");
        
        final List<JButton> l = new ArrayList<>();
        l.add(reset); 
        l.add(draw);
        l.add(stand);
        l.add(Double);

        //momentaneamente tolti
        final JButton chip1 = new JButton("1"); 
        final JButton chip2 = new JButton("5");
        final JButton chip3 = new JButton("25");
        final JButton chip4 = new JButton("100");    
        final JButton chip5 = new JButton("500");
        //l.add(chip1);
        //l.add(chip2);
        //l.add(chip3);
        //l.add(chip4);
        //l.add(chip5);
        
        int i = 0;
        for (final JButton jb : l) { 
            jb.setPreferredSize(new Dimension(110, 100));
            jb.setVisible(false);
            jb.setOpaque(false);
            jb.setIcon(new ImageIcon((new ImageIcon("res/img/buttons/" + s.get(i) + ".png").getImage())
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            jb.setFocusPainted(false);
            buttonsArea.add(jb, setDimensionObj(i, 0, 0, 10, 10));
            i++;
        }
        //aggiungo il jpanel dei pulsanti al jpanel generale
        add(buttonsArea, BorderLayout.SOUTH);

        
        
        
        
        //JPanel a layer che mostra le carte del giocatore CENTER
        final JLayeredPane playerCardsPanel = new JLayeredPane();
        pcards = new LinkedList<>(); //lista di JLabel, ciascuna sarà una carta del giocatore
        
        //Punteggio player
        final JLabel playerpoints = new JLabel();
        playerpoints.setForeground(Color.WHITE);
        playerpoints.setBounds(510, 20, 150, 150);
        playerpoints.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        
        final JButton cancel = new JButton();
        final JButton bet = new JButton();
        final JButton conferma = new JButton();
        
        cancel.setBounds(310, 110, 50, 50);
        bet.setBounds(370, 130, 70, 70);
        conferma.setBounds(310, 165, 50, 50);
        
        cancel.setIcon(new ImageIcon((new ImageIcon("res/img/buttons/cancel.png").getImage())
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        conferma.setIcon(new ImageIcon((new ImageIcon("res/img/buttons/confirm.png").getImage())
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        l.removeAll(l);
        l.add(bet);
        l.add(cancel);
        l.add(conferma);
        
        for (final JButton jb : l) { 
            jb.setVisible(false);
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            jb.setFocusPainted(false);
        }
        
        bet.setVisible(true);
                
        playerCardsPanel.add(conferma, 0);
        playerCardsPanel.add(cancel, 0);
        playerCardsPanel.add(bet, 0);
        playerCardsPanel.add(playerpoints, 0);
        //aggiunto il pannello con tutte le carte del player al pannello generale
        add(playerCardsPanel, BorderLayout.CENTER);
        
        
        //JPanel a layer che mostra le carte del dealer NORTH
        final JLayeredPane dealerCardsPanel = new JLayeredPane();
        dealerCardsPanel.setPreferredSize(new Dimension(300, 300));
        dcards = new LinkedList<>();

        final JLabel dpoints = new JLabel();
        final JLabel vincita = new JLabel("Hai vinto!");
        final JLabel saldo = new JLabel("Saldo: " + account.getBalance());
        final JLabel puntata = new JLabel("Puntata: " + this.puntata);
        dpoints.setBounds(510, 25, 150, 150);
        vincita.setBounds(900, 20, 150, 150);
        saldo.setBounds(100, 80, 150, 150);
        puntata.setBounds(100, 110, 150, 150);

        
        
        
        final List<JLabel> list = new ArrayList<>();
        list.add(dpoints);
        list.add(saldo);
        list.add(puntata);
        list.add(vincita);
        
        for (final JLabel jb : list) { 
            jb.setForeground(Color.WHITE);
            jb.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        }
        
        dealerCardsPanel.add(dpoints, 0);
        dealerCardsPanel.add(vincita);
        dealerCardsPanel.add(saldo);
        dealerCardsPanel.add(puntata);
        //aggiungo le carte del dealer al pannello generale NORTH
        add(dealerCardsPanel, BorderLayout.NORTH);

        
        
        
        
        //da fare refactoring
        draw.addActionListener(e -> {   
            Double.setVisible(false);
            this.game.askCard();
            setCards(pcards, game.getPlayerHand(), playerCardsPanel, -1); //da mettere costante direction
            playerpoints.setText(String.valueOf(game.getPlayerPoints()));
            if (game.getPlayerPoints() >= 21) {
                stand.doClick();
            }
        });
        
        //codice ripetuto
        stand.addActionListener(e -> {
            game.stand();
            setCards(dcards, game.getDealerHand(), dealerCardsPanel, 1);
            dpoints.setText(String.valueOf(game.getDealerPoints()));
            saldo.setText("Saldo: " + account.getBalance());
            
            //disattiva i pulsanti
            draw.setVisible(false);
            stand.setVisible(false);
            reset.setVisible(true);
            Double.setVisible(false);
        });
        
        Double.addActionListener(e -> {   
            if (this.puntata * 2 <= account.getBalance()) {
                this.puntata *= 2;
                game.askCard();
                setCards(pcards, game.getPlayerHand(), playerCardsPanel, -1);
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
            conferma.setVisible(true);
            cancel.setVisible(true);
            if ((this.puntata + this.chipvalue) <= account.getBalance()) {
                this.puntata += this.chipvalue;
                //bet.setText(String.valueOf(this.puntata));
                bet.removeAll();
                bet.setIcon(chooseChip(this.puntata));
                final JPanel jp = new JPanel(new BorderLayout());
                final JLabel punt = new JLabel(String.valueOf(this.puntata), SwingConstants.CENTER);
                punt.setForeground(Color.WHITE);
                jp.setOpaque(false);
                jp.add(punt, BorderLayout.CENTER);
                bet.add(jp, BorderLayout.CENTER);
            }
        });
        
        
        
        cancel.addActionListener(e -> {  
            this.puntata = 0;
            bet.removeAll();
            bet.setIcon(null);
            conferma.setVisible(false);
            cancel.setVisible(false);
        });
       
        reset.addActionListener(e -> {  
            this.puntata = 0;
            puntata.setText("Puntata: " + this.puntata);
            bet.setEnabled(true);
            bet.removeAll();
            bet.setIcon(null);
            reset.setVisible(false);
            dpoints.setText("");
            playerpoints.setText("");
            dpoints.setIcon(null);
            playerpoints.setIcon(null);
            
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
                cancel.setVisible(false);
                bet.setEnabled(false);
                bet.setDisabledIcon(chooseChip(this.puntata));
                conferma.setVisible(false);
                draw.setVisible(true);
                stand.setVisible(true);
                Double.setVisible(true);

                setCards(pcards, game.getPlayerHand(), playerCardsPanel, -1);
                setCards(dcards, game.getDealerHand(), dealerCardsPanel, 1);
                
                saldo.setText("Saldo: " + account.getBalance());
                puntata.setText("Puntata: " + this.puntata);
                dpoints.setText(String.valueOf(game.getDealerHand().getCard(0).getValue()));
                playerpoints.setText(String.valueOf(game.getPlayerPoints()));
                final Image img = ((new ImageIcon("res/img/buttons/points.png")
                        .getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                dpoints.setIcon(new ImageIcon(img));
                dpoints.setHorizontalTextPosition(JLabel.CENTER);
                playerpoints.setIcon(new ImageIcon(img));
                playerpoints.setHorizontalTextPosition(JLabel.CENTER);
                
                
            }
        });
    }
    
    

    

    

    
    /**
     * Testing. Da eliminare in seguito.
     */
    public static void main(final String[] args) {   
        final JFrame jf = new JFrame();
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setPreferredSize(new Dimension(1280, 720)); 
        final AdvancedAccountManager account = new AdvancedAccountManagerImpl();
        account.logger("Username", "Password");
        jf.add(new Gui(new Dimension(1280, 720), new AdvancedBalanceManagerImpl(account)));    
        jf.pack();                                 
        jf.setLocationRelativeTo(null); 
        jf.setVisible(true); 
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
    
    private void setCards(final List<JLabel> cards, final Hand h, final JLayeredPane p, final int direction) {
        for (final JLabel j : cards) {
            p.remove(j);
        }
        cards.removeAll(cards);
        for (int i = 0; i < h.size(); i++) {
            cards.add(new JLabel());
            final JLabel jlabel = cards.get(i);
            jlabel.setBounds(575 + (i * 25), 80 + ((i * 15) * direction), 150, 150);
            jlabel.setIcon(new ImageIcon(h.getCard(i).getImg().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
            p.add(jlabel, 0);
        }
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
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }
}
