package baccarat;

import model.account.BalanceManager;
import model.blackjack.Hand;
import model.blackjack.HandImpl;
import model.blackjack.Deck;
import model.blackjack.DeckImpl;

/**
 * baccarat logic implementation
 */
public class BaccaratLogicImpl implements BaccaratLogic {
    
  private final BalanceManager account;
  private final Deck deck;
  private double bet;
  private Hand player;
  private Hand dealer;
  private int baccaratcard;

  BaccaratLogicImpl(final BalanceManager account) {
    this.deck = new DeckImpl(6);
    this.deck.generateDeck();
    this.account = account;
    this.player = new HandImpl();
    this.dealer = new HandImpl();
    this.bet = 0;
  }
    
  @Override
    public void startGame(final double bet) {
    this.bet = bet;
    account.withdraw(this.bet);
    this.player = new HandImpl();
    this.dealer = new HandImpl();
    
    //both dealer and player start with 2 cards
    this.player.addCard(this.deck.drawRandomCard());
    this.player.addCard(this.deck.drawRandomCard());
    this.baccaratcard = this.player.getCard(1).getValue();
    this.dealer.addCard(this.deck.drawRandomCard());
    this.dealer.addCard(this.deck.drawRandomCard());
    this.dealer.calculatePoints();
    this.player.calculatePoints();
  }
    
  //next move of the game
  @Override
  public void nextMove() {
    nextPlayerMove();   	
    nextDealerMove();        
  }
  
  //player draw a card
  @Override
  public void playerDraw() {
    this.player.addCard(this.deck.drawRandomCard()); 
    this.player.calculatePoints();    
  }
  
  //dealer draw a card
  @Override
  public void dealerDraw() {
    this.dealer.addCard(this.deck.drawRandomCard());
    this.dealer.calculatePoints();
  }
  
  //check who wins
  @Override
  public int checkWin() { 
    if (getPlayerPoints() == getDealerPoints()) {
      return 0;
    }         
    if (getPlayerPoints() <= getDealerPoints()) {
      return -1;          
    } else {
      return 1;
    }
  }
    
  //next move of the dealer, can draw or do nothing
  @Override
  public void nextDealerMove() {
    if (this.dealer.getCard(1).isFaceDown()) {
      this.dealer.getCard(1).turnOver();
      nextDealerMove();
    } else if (checkBaccarat(this.dealer)) {
      endGame();
    } else if (getDealerPoints() == 2 || getDealerPoints() == 1 || checkBaccarat(this.dealer)) {
      dealerDraw();               
    } else if (getDealerPoints() == 3 && this.baccaratcard != 8 && this.baccaratcard != 9) {
      dealerDraw();
    } else if (getDealerPoints() == 4 && this.baccaratcard >= 2 && this.baccaratcard <= 7) {
      dealerDraw();
    } else if (getDealerPoints() == 5 && this.baccaratcard >= 4 && this.baccaratcard <= 7) {
      dealerDraw();
    } else if (getDealerPoints() == 6 && this.baccaratcard == 6 || this.baccaratcard == 7) {
      dealerDraw();
    } else if (getDealerPoints() == 7) {
        //no draw
    } 
  }
  
  //next move of the player, can draw or do nothing  
  @Override
  public void nextPlayerMove() {
    if (checkBaccarat(this.player)) {
      endGame();
    } else if (getPlayerPoints() != 6 && getPlayerPoints() != 7) {
      playerDraw();	
    }
    //no draw
  }

  @Override
  public Hand getPlayerHand() {
    return this.player;
  }

  @Override
  public Hand getDealerHand() {
    return this.dealer;
  }

  @Override
  public int getPlayerPoints() {
    return this.player.getBaccaratPoints();
    
  }

  @Override
  public int getDealerPoints() {
    return this.dealer.getBaccaratPoints();
  }

  @Override
  public boolean checkBaccarat(final Hand h) {
    return ((h.getBaccaratPoints() == 8 || h.getBaccaratPoints() == 9) && h.size() == 2);
  }

  @Override
  public void endGame() {
    if (this.deck.size() <= (this.deck.getnDecks() * 13 * 4) / 2) {
      this.deck.shuffle();
    }
    if (checkWin() == 1) {
      account.changeBalance(account.getBalance() + (this.bet * 2));
    } else if (checkWin() == 0) {
      account.changeBalance(account.getBalance() + (this.bet));
    }
  }

@Override
public double getBet() {
	// TODO Auto-generated method stub
	return this.bet;
}	
}

