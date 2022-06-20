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
import java.awt.Insets;
import account.BalanceManager;
import view.ImageLoader;
import view.MyGridBagConstraints;
import java.awt.Image;
import java.awt.GridBagLayout;
import view.gui.MenuManager;
import view.menu.GeneralGui;
import view.menu.Menu;
import view.menu.games.Game;

/**
 * GUI principale Blackjack.
 */
public class BlackJackGui extends JPanel implements Game {
    
    private static final long serialVersionUID = 1L;
    private static final int DIRECTION_PLAYER = -1;
    private static final int DIRECTION_DEALER = 1;
    private final GeneralGui generalInterface;
    private final BetButton bet;
    private final JButton draw;
    private final JButton stand;
    private final JButton Double;
    private final JButton restart; 
    private final JLabel playerPoints;
    private final JLabel dealerPoints;
    private final JLayeredPane center;
    private final JLayeredPane north;
    private final Image img = ImageLoader.getImage("res/img/backgrounds/blackjacktableHDwithbet.png");
    private List<JLabel> dealerCards;
    private List<JLabel> playerCards;
    private final BlackJackLogic gameLogic;
    private int puntata;
    
    /**
     * Costruttore.
     */
    public BlackJackGui(final MenuManager frame, final BalanceManager account, final GeneralGui generalInterface) {
        this.generalInterface = generalInterface;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        gameLogic = new GameImpl(account);

        this.draw = new JButton(); 
        this.stand = new JButton();
        this.Double = new JButton();
        this.restart = new JButton();  
        //aggiungo il jpanel dei pulsanti al jpanel generale
        add(generateSouth(draw, stand, Double, restart), BorderLayout.SOUTH);
        
        
        this.playerPoints = new JLabel();
        this.dealerPoints = new JLabel();
        final List<JLabel> visualPoints = new ArrayList<>();
        visualPoints.add(playerPoints);
        visualPoints.add(dealerPoints);
        final Image img = ((ImageLoader.getImage("res/img/buttons/points.png"))
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        
        for (final JLabel points : visualPoints) {
            points.setForeground(Color.WHITE);
            points.setBounds(515, 50, 150, 150);
            points.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
            points.setIcon(new ImageIcon(img));
            points.setHorizontalTextPosition(JLabel.CENTER);
            points.setVisible(false);
        }
        
        //JPanel a layer che mostra le carte del giocatore CENTER
        this.center = new JLayeredPane();
        playerCards = new LinkedList<>(); //lista di JLabel, ciascuna sarà una carta del giocatore
        this.bet = new BetButton();
        bet.setBounds(375, 155, 70, 70);
        center.add(bet, 0);
        center.add(playerPoints, 0);
        //aggiunto il pannello con tutte le carte del player al pannello generale
        add(center, BorderLayout.CENTER);
        
        //JPanel a layer che mostra le carte del dealer NORTH
        this.north = new JLayeredPane();
        dealerCards = new LinkedList<>();
        north.setPreferredSize(new Dimension(300, 300));
        north.add(dealerPoints, 0);
        //aggiungo le carte del dealer al pannello generale NORTH
        add(north, BorderLayout.NORTH);

        
        draw.addActionListener(e -> {   
            this.gameLogic.askCard();
            setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
            playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
            Double.setVisible(false);
            if (gameLogic.getPlayerPoints() >= 21) {
                stand.doClick();
            }
        });
        
        //codice ripetuto
        stand.addActionListener(e -> {
            gameLogic.stand();
            setCards(dealerCards, gameLogic.getDealerHand(), north, DIRECTION_DEALER);
            dealerPoints.setText(String.valueOf(gameLogic.getDealerPoints()));
            if (gameLogic.checkWin() == 1) {
                if (gameLogic.checkBlackjack(gameLogic.getPlayerHand())) {
                    generalInterface.showWinMessage(true, bet.getBet() + ((bet.getBet() * 3) / 2));
                } else {
                    generalInterface.showWinMessage(true, bet.getBet() * 2);
                }
            }
            //disattiva i pulsanti
            draw.setVisible(false);
            stand.setVisible(false);
            Double.setVisible(false);
            restart.setVisible(true);
        });
        
        Double.addActionListener(e -> {   
            if (bet.getBet() * 2 <= account.getBalance()) {
                bet.setBet(bet.getBet() * 2);
                gameLogic.askCard();
                setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
                playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
                stand.doClick();
            }
        });
        
        
        bet.addActionListener(e -> {  
            if ((this.puntata + generalInterface.getFichesValue()) <= account.getBalance()) {
                bet.incrementBet(generalInterface.getFichesValue());
                generalInterface.showButtons(true);
            }
        });
        
        

        restart.addActionListener(e -> {  
            this.puntata = 0;
            generalInterface.setBetValue(0);
            generalInterface.setBalanceValue();
            //g.setWinValue();
            
            bet.setEnabled(true);
            bet.resetBet();
            restart.setVisible(false);
            
            dealerPoints.setVisible(false);
            playerPoints.setVisible(false);
            
            
            generalInterface.showWinMessage(false, 0);
            
            for (final JLabel j : playerCards) {
                center.remove(j);
            }
            
            for (final JLabel j : dealerCards) {
                north.remove(j);
            }

            dealerCards = new LinkedList<>();
            playerCards = new LinkedList<>();     
            center.revalidate();
            center.repaint();
            north.revalidate();
            north.repaint();
        });
    }
    
    
    private JPanel generateSouth(final JButton draw, final JButton stand,
            final JButton doublebet, final JButton restart) {
        //Area Pulsanti in fondo SUD
        final JPanel south = new JPanel(new GridBagLayout());
        final JPanel buttonsArea = new JPanel(new GridBagLayout()); 
        south.setPreferredSize(new Dimension(150, 150));
        buttonsArea.setPreferredSize(new Dimension(350, 150));
        south.setOpaque(false);
        buttonsArea.setOpaque(false);
        south.add(buttonsArea);
        
        //Bottoni Gioco (codice ripetuto)
     
        draw.setName("draw");
        stand.setName("stand");
        doublebet.setName("Double");
        restart.setName("restart");
        
        final List<JButton> buttonList = new ArrayList<>();
        buttonList.add(restart); 
        buttonList.add(draw);
        buttonList.add(stand);
        buttonList.add(doublebet);
        
        int i = 0;
        for (final JButton jb : buttonList) { 
            jb.setPreferredSize(new Dimension(110, 100));
            jb.setVisible(false);
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            jb.setFocusPainted(false);

            jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/buttons/" + buttonList.get(i).getName() + ".png"))
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

            buttonsArea.add(jb, new MyGridBagConstraints(i, 0, new Insets(0, 0, 0, 0), GridBagConstraints.NONE));
            i++;
        }
        return south;
    }

    private void setCards(final List<JLabel> cards, final Hand h, final JLayeredPane pane, final int direction) {
        for (final JLabel j : cards) {
            pane.remove(j);
        }
        cards.removeAll(cards);
        for (int i = 0; i < h.size(); i++) {
            cards.add(new JLabel());
            final JLabel visualCard = cards.get(i);
            visualCard.setBounds(575 + (i * 25), 90 + ((i * 15) * direction), 150, 150);
            visualCard.setIcon(new ImageIcon(h.getCard(i).getImg().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
            pane.add(visualCard, 0);
        }
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    public void confirmBet() {
        if (this.bet.getBet() != 0) {
            this.gameLogic.startGame(this.bet.getBet());
            this.bet.confirmBet();
            
            this.generalInterface.showButtons(false);
            this.generalInterface.setBetValue(this.bet.getBet());
            this.generalInterface.setBalanceValue();

            this.draw.setVisible(true);
            this.stand.setVisible(true);
            this.Double.setVisible(true);
            
            this.dealerPoints.setVisible(true);
            this.playerPoints.setVisible(true);

            setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
            setCards(dealerCards, gameLogic.getDealerHand(), north, DIRECTION_DEALER);

            this.dealerPoints.setText(String.valueOf(gameLogic.getDealerHand().getCard(0).getValue()));
            this.playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));

           
            gameLogic.checkInsurance();

            
            if (gameLogic.checkBlackjack(gameLogic.getPlayerHand())) {
                stand.doClick();
            }
        }
    }


    @Override
    public void resetBet() {
        this.bet.resetBet();
        this.generalInterface.showButtons(false);
    }

    @Override
    public JPanel getGame() {
        return this;
    }
}
