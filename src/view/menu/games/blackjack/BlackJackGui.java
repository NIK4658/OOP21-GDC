package view.menu.games.blackjack;

import controller.blackjack.BlackJackLogic;
import controller.blackjack.BlackJackLogicImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import model.account.BalanceManager;
import model.blackjack.Hand;
import view.MyGridBagConstraints;
import view.Utilities;
import view.gui.MenuManager;
import view.menu.GeneralGui;
import view.menu.games.Game;
import view.menu.games.component.BetButton;
import view.menu.games.component.BetButtonImpl;

/**
 * GUI principale Blackjack.
 */
public class BlackJackGui extends JPanel implements Game {
    
    private static final long serialVersionUID = 1L;
    private static final int DIRECTION_PLAYER = -1;
    private static final int DIRECTION_DEALER = 1;
    private final GeneralGui generalInterface;
    private final BlackJackLogic gameLogic;
    private List<JLabel> dealerCards;
    private List<JLabel> playerCards;
    private final Image img = Utilities.getImage("img/backgrounds/blackjacktableHDwithbet.png");
    private final int width;
    private final int height;
    private final BetButtonImpl bet;
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
    public BlackJackGui(final BalanceManager account, final GeneralGui generalInterface) {
        this.generalInterface = generalInterface;
        final MenuManager frame = generalInterface.getFrame();
        this.setLayout(new BorderLayout());
        this.gameLogic = new BlackJackLogicImpl(account);
        this.setPreferredSize(frame.getSizeMenu());
        this.width = frame.getWidthMenu();
        this.height = frame.getHeightMenu();
        this.draw = new JButton(); 
        this.stand = new JButton();
        this.doubleUp = new JButton();
        this.restart = new JButton();  
        this.playerCards = new LinkedList<>();
        this.dealerCards = new LinkedList<>();
        add(generateSouth(), BorderLayout.SOUTH);
        playerPoints = new JLabel();
        dealerPoints = new JLabel();
        final List<JLabel> visualPoints = new ArrayList<>();
        visualPoints.add(playerPoints);
        visualPoints.add(dealerPoints);
        final Image img = ((Utilities.getImage("img/buttons/points.png"))
                .getScaledInstance(width / 25, width / 25, Image.SCALE_SMOOTH));
        for (final JLabel points : visualPoints) {
            points.setForeground(Color.WHITE);
            points.setBounds((int) (width / 2.5), width / 25, width / 8, width / 8);
            points.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, width / 64));
            points.setIcon(new ImageIcon(img));
            points.setHorizontalTextPosition(JLabel.CENTER);
            points.setVisible(false);
        }
        center = new JLayeredPane();
        this.bet = new BetButtonImpl(frame.getSizeMenu());
        this.bet.setBounds((int) (width / 3.41), width / 8, width / 18, width / 18);
        center.add(this.bet, 0);
        center.add(playerPoints, 0);
        add(center, BorderLayout.CENTER);
        north = new JLayeredPane();
        north.setPreferredSize(new Dimension((int) (width / 4.3), (int) (width / 4.3)));
        north.add(dealerPoints, 0);
        add(north, BorderLayout.NORTH);
        
        draw.addActionListener(e -> {   
            gameLogic.askCard();
            setCards(DIRECTION_PLAYER);
            dealerPoints.setText(String.valueOf(gameLogic.getDealerHand().getCard(0).getValue()));
            doubleUp.setVisible(false);
            if (gameLogic.getPlayerPoints() >= 21) {
                stand.doClick();
            }
        });
        
        stand.addActionListener(e -> {
            gameLogic.stand();
            setCards(DIRECTION_DEALER);
            if (gameLogic.checkWin() != -1 || gameLogic.checkBlackjack(gameLogic.getPlayerHand())) {
                generalInterface.showWinMessage(gameLogic.getLastWin());
                generalInterface.setBetValue(bet.getBet());
            }
            draw.setVisible(false);
            stand.setVisible(false);
            doubleUp.setVisible(false);
            restart.setVisible(true);
        });
        
        doubleUp.addActionListener(e -> {   
            if (gameLogic.askDouble()) {
                bet.setBet(bet.getBet() * 2);
                setCards(DIRECTION_PLAYER);
                generalInterface.updateBalanceValue();
                generalInterface.setBetValue(bet.getBet());
                stand.doClick();
            }
        });
        
        bet.addActionListener(e -> { 
            if (generalInterface.addBetValue(generalInterface.getFichesValue())) {
                bet.incrementBet(generalInterface.getFichesValue());
                generalInterface.showButtons(true);
            }
        });

        restart.addActionListener(e -> {  
            generalInterface.showWinMessage(0);
            generalInterface.setBetValue(0);
            bet.setEnabled(true);
            bet.resetBet();
            restart.setVisible(false);
            dealerPoints.setVisible(false);
            playerPoints.setVisible(false);
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
    
    
    private JPanel generateSouth() {
        final JPanel south = new JPanel(new GridBagLayout());
        final JPanel buttonsArea = new JPanel(new GridBagLayout()); 
        south.setPreferredSize(new Dimension((int) (this.width / 8.5), (int) (this.width / 8.5)));
        buttonsArea.setPreferredSize(new Dimension((int) (this.width / 3.6), (int) (this.width / 8.5)));
        south.setOpaque(false);
        buttonsArea.setOpaque(false);
        south.add(buttonsArea);
        this.draw.setName("draw");
        this.stand.setName("stand");
        this.doubleUp.setName("Double");
        this.restart.setName("restart");
        final List<JButton> buttonList = new ArrayList<>();
        buttonList.add(this.restart); 
        buttonList.add(this.draw);
        buttonList.add(this.stand);
        buttonList.add(this.doubleUp);
        int i = 0;
        for (final JButton jb : buttonList) { 
            jb.setPreferredSize(new Dimension((int) (this.width / 11.6), (int) (this.width / 12.8)));
            jb.setVisible(false);
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            jb.setFocusPainted(false);
            jb.setIcon(new ImageIcon((Utilities.getImage("img/buttons/" + buttonList.get(i).getName() + ".png"))
                    .getScaledInstance((int) (this.width / 12.8), (int) (this.width / 12.8), Image.SCALE_SMOOTH)));
            buttonsArea.add(jb, new MyGridBagConstraints(i, 0, new Insets(0, 0, 0, 0), GridBagConstraints.NONE));
            i++;
        }
        return south;
    }

    @Override
    public void confirmBet() {
        this.gameLogic.startGame(this.bet.getBet());
        this.bet.confirmBet();
        this.generalInterface.showButtons(false);
        this.draw.setVisible(true);
        this.stand.setVisible(true);
        this.doubleUp.setVisible(true);
        this.dealerPoints.setVisible(true);
        this.playerPoints.setVisible(true);
        setCards(DIRECTION_PLAYER);
        setCards(DIRECTION_DEALER);
        this.dealerPoints.setText(String.valueOf(gameLogic.getDealerHand().getCard(0).getValue()));
        if (gameLogic.checkInsurance()) {
            final InsuranceWindow ins = new InsuranceWindow(
                    new Dimension(this.width, this.height), gameLogic.canInsurance());
            if (!gameLogic.calculateInsurance(ins.isInsurance())) {
                stand.doClick();
            }
            this.generalInterface.updateBalanceValue();
        }
        if (gameLogic.checkBlackjack(gameLogic.getPlayerHand())) {
            stand.doClick();
        }
    }

    @Override
    public void resetBet() {
        this.bet.resetBet();
        this.generalInterface.showButtons(false);
    }
    
    
    private void setCards(final int direction) {
        final List<JLabel> cards;
        final Hand h;
        final JLayeredPane pane;
        if (direction == -1) {
            cards = this.playerCards;
            h = this.gameLogic.getPlayerHand();
            pane = this.center;
        } else {
            cards = this.dealerCards;
            h = this.gameLogic.getDealerHand();
            pane = this.north;
        }
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
        playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
        dealerPoints.setText(String.valueOf(gameLogic.getDealerPoints()));
    }

    @Override
    public JPanel getGame() {
        return this;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }
    
}