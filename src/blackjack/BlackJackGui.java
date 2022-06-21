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
import view.menu.games.GuideGui;

/**
 * GUI principale Blackjack.
 */
public class BlackJackGui extends JPanel implements Game {
    
    private static final long serialVersionUID = 1L;
    private static final int DIRECTION_PLAYER = -1;
    private static final int DIRECTION_DEALER = 1;
    private final GeneralGui generalInterface;
    private final Image img = ImageLoader.getImage("res/img/backgrounds/blackjacktableHDwithbet.png");
    private List<JLabel> dealerCards;
    private List<JLabel> playerCards;
    private final BlackJackLogic gameLogic;
    
    private final int width;
    private final int height;
    private final BetButton bet;
    private final JButton draw;
    private final JButton stand;
    private final JButton doubleUp;
    private final JButton restart; 
    private final JLabel playerPoints;
    private final JLabel dealerPoints;
    private final JLayeredPane center;
    private final JLayeredPane north;
    
    /**
     * Costruttore.
     */
    public BlackJackGui(final MenuManager frame, final BalanceManager account, final GeneralGui generalInterface) {
        this.generalInterface = generalInterface;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        gameLogic = new BlackJackLogicImpl(account);
        this.width = frame.getWidthMenu();
        this.height = frame.getHeightMenu();
        
        System.out.println(width);

        this.draw = new JButton(); 
        this.stand = new JButton();
        this.doubleUp = new JButton();
        this.restart = new JButton();  
        //aggiungo il jpanel dei pulsanti al jpanel generale
        add(generateSouth(draw, stand, doubleUp, restart), BorderLayout.SOUTH);
        
        
        this.playerPoints = new JLabel();
        this.dealerPoints = new JLabel();
        final List<JLabel> visualPoints = new ArrayList<>();
        visualPoints.add(playerPoints);
        visualPoints.add(dealerPoints);
        final Image img = ((ImageLoader.getImage("res/img/buttons/points.png"))
                .getScaledInstance(width / 25, width / 25, Image.SCALE_SMOOTH));
        
        for (final JLabel points : visualPoints) {
            points.setForeground(Color.WHITE);
            points.setBounds((int) (width / 2.5), width / 25, width / 8, width / 8);
            points.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, width / 64));
            points.setIcon(new ImageIcon(img));
            points.setHorizontalTextPosition(JLabel.CENTER);
            points.setVisible(false);
        }
        
        //JPanel a layer che mostra le carte del giocatore CENTER
        this.center = new JLayeredPane();
        playerCards = new LinkedList<>(); //lista di JLabel, ciascuna sarà una carta del giocatore
        this.bet = new BetButton(frame.getSizeMenu());
        bet.setBounds((int) (width / 3.41), width / 8, width / 18, width / 18);
        center.add(bet, 0);
        center.add(playerPoints, 0);
        //aggiunto il pannello con tutte le carte del player al pannello generale
        add(center, BorderLayout.CENTER);
        
        //JPanel a layer che mostra le carte del dealer NORTH
        this.north = new JLayeredPane();
        dealerCards = new LinkedList<>();
        north.setPreferredSize(new Dimension((int) (width / 4.3), (int) (width / 4.3)));
        north.add(dealerPoints, 0);
        //aggiungo le carte del dealer al pannello generale NORTH
        add(north, BorderLayout.NORTH);

        
        draw.addActionListener(e -> {   
            this.gameLogic.askCard();
            setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
            playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
            doubleUp.setVisible(false);
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
                    generalInterface.showWinMessage(bet.getBet() + ((bet.getBet() * 3) / 2));
                } else {
                    generalInterface.showWinMessage(bet.getBet() * 2);
                }
            }
            
            //disattiva i pulsanti
            draw.setVisible(false);
            stand.setVisible(false);
            doubleUp.setVisible(false);
            restart.setVisible(true);
        });
        
        doubleUp.addActionListener(e -> {   
            if (bet.getBet() * 2 <= account.getBalance()) {
                bet.setBet(bet.getBet() * 2);
                gameLogic.askCard();
                setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
                playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
                stand.doClick();
            }
        });
        
        
        bet.addActionListener(e -> {  
            if ((this.bet.getBet() + generalInterface.getFichesValue()) <= account.getBalance()) {
                bet.incrementBet(generalInterface.getFichesValue());
                generalInterface.showButtons(true);
            }
        });
        
        

        restart.addActionListener(e -> {  
            generalInterface.setBetValue(0);
            generalInterface.updateBalanceValue();
            
            bet.setEnabled(true);
            bet.resetBet();
            restart.setVisible(false);
            
            dealerPoints.setVisible(false);
            playerPoints.setVisible(false);
            
            
            generalInterface.showWinMessage(0);
            
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
        south.setPreferredSize(new Dimension((int) (width / 8.5), (int) (width / 8.5)));
        buttonsArea.setPreferredSize(new Dimension((int) (width / 3.6), (int) (width / 8.5)));
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
            jb.setPreferredSize(new Dimension((int) (width / 11.6), (int) (width / 12.8)));
            jb.setVisible(false);
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            jb.setFocusPainted(false);

            jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/buttons/" + buttonList.get(i).getName() + ".png"))
                    .getScaledInstance((int) (width / 12.8), (int) (width / 12.8), Image.SCALE_SMOOTH)));

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
            visualCard.setBounds((int) (width / 2.23) + (i * (int) (width / 51.2)),
                    (int) (width / 14.2) + ((i * (int) (width / 85.3)) * direction),
                    (int) (width / 8.5), (int) (width / 8.5));
            visualCard.setIcon(new ImageIcon(h.getCard(i).getImg()
                    .getScaledInstance((int) (width / 12.8), (int) (width / 8.5), Image.SCALE_SMOOTH)));
            pane.add(visualCard, 0);
            pane.validate();
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
            this.generalInterface.updateBalanceValue();
            this.generalInterface.setWinValue(0);

            this.draw.setVisible(true);
            this.stand.setVisible(true);
            this.doubleUp.setVisible(true);
            
            this.dealerPoints.setVisible(true);
            this.playerPoints.setVisible(true);

            setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
            setCards(dealerCards, gameLogic.getDealerHand(), north, DIRECTION_DEALER);

            this.dealerPoints.setText(String.valueOf(gameLogic.getDealerHand().getCard(0).getValue()));
            this.playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));

           
            if (gameLogic.checkInsurance()) {
                final InsuranceWindow ins = new InsuranceWindow(new Dimension(width, height), gameLogic.canInsurance());
                if (!gameLogic.calculateInsurance(ins.isInsurance())) {
                    stand.doClick();
                }
                this.generalInterface.updateBalanceValue();
            }
            

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
