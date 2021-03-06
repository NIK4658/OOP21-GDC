package view.menu.games.baccarat;

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
import view.MyGridBagConstraints;
import view.Utilities;
import java.awt.Image;
import java.awt.GridBagLayout;
import view.gui.MenuManager;
import view.menu.GeneralGui;
import view.menu.games.Game;
import view.menu.games.component.BetButtonImpl;
import model.account.BalanceManager;
import model.baccarat.BaccaratLogic;
import model.baccarat.BaccaratLogicImpl;
import model.components.Hand;

/**
 * GUI principale Baccarat.
 */
public class BaccaratGui extends JPanel implements Game {
    
  private static final long serialVersionUID = 1L;
  private static final int DIRECTION_PLAYER = -1;
  private static final int DIRECTION_DEALER = 1;
  private final GeneralGui generalInterface;
  private final int width;
  private final BetButtonImpl bet;
  private final JButton next;
  private final JButton restart; 
  private final JLabel playerPoints;
  private final JLabel dealerPoints;
  private final JLayeredPane center;
  private final JLayeredPane north;
  private final Image img = Utilities.getImage("img/backgrounds/BacTable.png");
  private List<JLabel> dealerCards;
  private List<JLabel> playerCards;
  private final BalanceManager account;
  private final BaccaratLogic controller;

    
    /**
     * Constructor.
     */
  public BaccaratGui(final MenuManager frame, final BalanceManager account, final GeneralGui generalInterface) {
	  
	    

    this.generalInterface = generalInterface;
    this.setLayout(new BorderLayout());
    this.setPreferredSize(frame.getSizeMenu());	
    this.account = account;
    this.width = frame.getWidthMenu();
    this.next = new JButton();
    this.restart = new JButton();  
    this.controller = new BaccaratLogicImpl(account);
    
    //Add button panel to south panel
    add(generateSouth(next, restart), BorderLayout.SOUTH);
    
    //label shows points of both players
    this.playerPoints = new JLabel();
    this.dealerPoints = new JLabel();
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
        
    //JPanel with player cards
    this.center = new JLayeredPane();
    playerCards = new LinkedList<>();
    this.bet = new BetButtonImpl(frame.getSizeMenu());
    bet.setBounds((int) (width / 3.41), width / 8, width / 18, width / 18);
    center.add(bet, 0);
    center.add(playerPoints, 0);
   
    //Add player cards to center
    add(center, BorderLayout.CENTER);
        
    //JPanel with dealer cards
    this.north = new JLayeredPane();
    dealerCards = new LinkedList<>();
    north.setPreferredSize(new Dimension((int) (width / 4.3), (int) (width / 4.3)));
    north.add(dealerPoints, 0);
    
    //Add dealer cards to north
    add(north, BorderLayout.NORTH);

  
    //codice ripetuto
    next.addActionListener(e -> {
      controller.nextMove();
      setCards(dealerCards, controller.getDealerHand(), north, DIRECTION_DEALER);
      dealerPoints.setText(String.valueOf(controller.getDealerPoints()));
      setCards(playerCards, controller.getPlayerHand(), center, DIRECTION_PLAYER);
      playerPoints.setText(String.valueOf(controller.getPlayerPoints()));
      //player wins
      if (controller.checkWin() == 1) {    	
        generalInterface.showWinMessage(bet.getBet() * 2);
      //tie
      } else if (controller.checkWin() == 0) {
        generalInterface.showWinMessage(bet.getBet());
      }
      //Dealer win
                   
      next.setVisible(false);
      restart.setVisible(true);
    });
        
        
    bet.addActionListener(e -> {  
      if ((this.bet.getBet() + generalInterface.getFichesValue()) <= this.account.getBalance()) {
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
    
    
  private JPanel generateSouth(final JButton next, final JButton restart) {
    //South Panel
    final JPanel south = new JPanel(new GridBagLayout());
    final JPanel buttonsArea = new JPanel(new GridBagLayout()); 
    south.setPreferredSize(new Dimension((int) (width / 8.5), (int) (width / 8.5)));
    buttonsArea.setPreferredSize(new Dimension((int) (width / 3.6), (int) (width / 8.5)));
    south.setOpaque(false);
    buttonsArea.setOpaque(false);
    south.add(buttonsArea);
        
    //Game buttons
    next.setName("next");
    restart.setName("restart");
    final List<JButton> buttonList = new ArrayList<>();
    buttonList.add(restart); 
    buttonList.add(next);

    int i = 0;
    for (final JButton jb : buttonList) { 
      jb.setPreferredSize(new Dimension((int) (width / 11.6), (int) (width / 12.8)));
      jb.setVisible(false);
      jb.setOpaque(false);
      jb.setContentAreaFilled(false);
      jb.setBorderPainted(false);
      jb.setFocusPainted(false);
      jb.setIcon(new ImageIcon(Utilities.getImage("img/buttons/" + buttonList.get(i).getName() + ".png")
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
      visualCard.setBounds((int) (width / 2.23) + (i * (int) (width / 51.2)), (int) (width / 14.2) + ((i * (int) (width / 85.3)) * direction), (int) (width / 8.5), (int) (width / 8.5));
      visualCard.setIcon(new ImageIcon(h.getCard(i).getImg().getScaledInstance((int) (width / 12.8), (int) (width / 8.5), Image.SCALE_SMOOTH)));
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
      this.controller.startGame(this.bet.getBet());
      this.bet.confirmBet();            
      this.generalInterface.showButtons(false);
      this.generalInterface.setBetValue(this.bet.getBet());
      this.generalInterface.updateBalanceValue();
      this.next.setVisible(true);
      this.dealerPoints.setVisible(true);
      this.playerPoints.setVisible(true);

      setCards(playerCards, controller.getPlayerHand(), center, DIRECTION_PLAYER);
      setCards(dealerCards, controller.getDealerHand(), north, DIRECTION_DEALER);

      this.dealerPoints.setText(String.valueOf(controller.getDealerPoints()));
      this.playerPoints.setText(String.valueOf(controller.getPlayerPoints()));

      if (controller.checkBaccarat(controller.getPlayerHand())) {
        next.doClick();
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
