package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Insets;
import account.AdvancedBalanceManager;
import view.ImageLoader;
import view.MyGridBagConstraints;
import java.awt.Image;
import java.awt.GridBagLayout;
import view.gui.MenuManager;
import view.menu.GeneralGui;
import view.menu.Menu;

/**
 * GUI principale Blackjack.
 */
public class Gui extends JPanel implements Menu {
    
    private static final long serialVersionUID = 1L;
    private static final int DIRECTION_PLAYER = -1;
    private static final int DIRECTION_DEALER = 1;
    private int puntata;
    private final Image img = ImageLoader.getImage("res/img/backgrounds/blackjacktableHDwithbet.png");
    private List<JLabel> dcards;
    private List<JLabel> pcards;
    private final Game game;
    
    
    
    /**
     * Costruttore.
     */
    public Gui(final MenuManager frame, final AdvancedBalanceManager account, final GeneralGui g) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        
        game = new GameImpl(account);
        
        //Area Pulsanti in fondo SUD
        final JPanel south = new JPanel(new GridBagLayout());
        final JPanel buttonsArea = new JPanel(new GridBagLayout()); 
        south.setPreferredSize(new Dimension(150, 150));
        buttonsArea.setPreferredSize(new Dimension(350, 150));
        south.setOpaque(false);
        buttonsArea.setOpaque(false);
        south.add(buttonsArea);
        
        //Bottoni Gioco (codice ripetuto)
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
        
        int i = 0;
        for (final JButton jb : l) { 
            jb.setPreferredSize(new Dimension(110, 100));
            jb.setVisible(false);
            jb.setOpaque(false);
            jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/buttons/" + s.get(i) + ".png"))
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            jb.setFocusPainted(false);
            buttonsArea.add(jb, new MyGridBagConstraints(i, 0, new Insets(0, 0, 0, 0), GridBagConstraints.NONE));
            i++;
        }
        //aggiungo il jpanel dei pulsanti al jpanel generale
        add(south, BorderLayout.SOUTH);
        
        //JPanel a layer che mostra le carte del giocatore CENTER
        final JLayeredPane playerCardsPanel = new JLayeredPane();
        pcards = new LinkedList<>(); //lista di JLabel, ciascuna sarà una carta del giocatore
        
        //Punteggio player
        final JLabel playerpoints = new JLabel();
        playerpoints.setForeground(Color.WHITE);
        playerpoints.setBounds(515, 70, 150, 150);
        playerpoints.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        
        final BetButton bet = new BetButton();
        bet.setBounds(375, 155, 70, 70);
                
        playerCardsPanel.add(bet, 0);
        playerCardsPanel.add(playerpoints, 0);
        //aggiunto il pannello con tutte le carte del player al pannello generale
        add(playerCardsPanel, BorderLayout.CENTER);
        
        
        //JPanel a layer che mostra le carte del dealer NORTH
        final JLayeredPane dealerCardsPanel = new JLayeredPane();
        dealerCardsPanel.setPreferredSize(new Dimension(300, 300));
        dcards = new LinkedList<>();

        final JLabel dpoints = new JLabel();
        dpoints.setBounds(515, 70, 150, 150);
        dpoints.setForeground(Color.WHITE);
        dpoints.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        
        dealerCardsPanel.add(dpoints, 0);
        //aggiungo le carte del dealer al pannello generale NORTH
        add(dealerCardsPanel, BorderLayout.NORTH);

        
        
        
        
        //da fare refactoring
        draw.addActionListener(e -> {   
            Double.setVisible(false);
            this.game.askCard();
            setCards(pcards, game.getPlayerHand(), playerCardsPanel, DIRECTION_PLAYER); //da mettere costante direction
            playerpoints.setText(String.valueOf(game.getPlayerPoints()));
            if (game.getPlayerPoints() >= 21) {
                stand.doClick();
            }
        });
        
        //codice ripetuto
        stand.addActionListener(e -> {
            game.stand();
            setCards(dcards, game.getDealerHand(), dealerCardsPanel, DIRECTION_DEALER);
            dpoints.setText(String.valueOf(game.getDealerPoints()));
            
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
                setCards(pcards, game.getPlayerHand(), playerCardsPanel, DIRECTION_PLAYER);
                playerpoints.setText(String.valueOf(game.getPlayerPoints()));
                stand.doClick();
            }
        });
        
      
        
        bet.addActionListener(e -> {  
            g.showButtons(true);
            if ((this.puntata + g.getFichesValue()) <= account.getBalance()) {
                bet.incrementBet(g.getFichesValue());
            }
        });
        
        
        
        g.getResetButton().addActionListener(e -> {  
            bet.resetBet();
            g.showButtons(false);
        });
       
        reset.addActionListener(e -> {  
            this.puntata = 0;
           
            bet.setEnabled(true);
            bet.resetBet();
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
        
        g.getConfirmButton().addActionListener(e -> { 
            if (bet.getBet() != 0) {
                game.startGame(this.puntata);
                g.showButtons(false);
                bet.confirmBet();
                

                draw.setVisible(true);
                stand.setVisible(true);
                Double.setVisible(true);

                setCards(pcards, game.getPlayerHand(), playerCardsPanel, DIRECTION_PLAYER);
                setCards(dcards, game.getDealerHand(), dealerCardsPanel, DIRECTION_DEALER);
                
                
                dpoints.setText(String.valueOf(game.getDealerHand().getCard(0).getValue()));
                playerpoints.setText(String.valueOf(game.getPlayerPoints()));
                final Image img = ((ImageLoader.getImage("res/img/buttons/points.png"))
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                dpoints.setIcon(new ImageIcon(img));
                dpoints.setHorizontalTextPosition(JLabel.CENTER);
                playerpoints.setIcon(new ImageIcon(img));
                playerpoints.setHorizontalTextPosition(JLabel.CENTER);
            }
        });
    }
    

    
    private void setCards(final List<JLabel> cards, final Hand h, final JLayeredPane p, final int direction) {
        for (final JLabel j : cards) {
            p.remove(j);
        }
        cards.removeAll(cards);
        for (int i = 0; i < h.size(); i++) {
            cards.add(new JLabel());
            final JLabel jlabel = cards.get(i);
            jlabel.setBounds(575 + (i * 25), 110 + ((i * 15) * direction), 150, 150);
            jlabel.setIcon(new ImageIcon(h.getCard(i).getImg().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
            p.add(jlabel, 0);
        }
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }


    @Override
    public JPanel getMenu() {
        return this;
    }
}
