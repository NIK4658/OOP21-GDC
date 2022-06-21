package baccarat;

import account.BalanceManager;
import blackjack.Deck;
import blackjack.DeckImpl;

/**
 * Classe principale gestione gioco blackjack.
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
        this.player.addCard(this.deck.drawRandomCard());
        this.player.addCard(this.deck.drawRandomCard());
        this.baccaratcard = this.player.getCard(1).getValue();
        this.dealer.addCard(this.deck.drawRandomCard());
        this.dealer.addCard(this.deck.drawRandomCard());
        this.dealer.getCard(1).turnOver();
        this.dealer.calculatePoints();
        this.player.calculatePoints();
        
        
        
        
        
        
      
    }
    
    @Override
    public void stand() {
    	nextPlayerMove();
    	
        nextDealerMove();
        
    }
    
    @Override
    public void playerDraw() {
        this.player.addCard(this.deck.drawRandomCard()); 
        this.player.calculatePoints();    
    }
    
    

   

    /*@Override
    public void askDouble() {
        //raddoppio puntata
        this.bet *= 2;
        askCard();
        stand();
    }*/



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

    @Override
    public void dealerDraw() {
        this.dealer.addCard(this.deck.drawRandomCard());
        this.dealer.calculatePoints();
    }
    
    

    @Override
    public void nextDealerMove() {
        if (this.dealer.getCard(1).isFaceDown()) {
            this.dealer.getCard(1).turnOver();
            nextDealerMove();
        } else if (checkBaccarat(this.dealer)) {
            endGame();
        } else if(getDealerPoints() == 2 || getDealerPoints() == 1 || checkBaccarat(this.dealer)) {        	
            dealerDraw();               
        } else if(getDealerPoints() == 3 && this.baccaratcard != 8 && this.baccaratcard != 9) {
        	dealerDraw();
        } else if(getDealerPoints() == 4 && this.baccaratcard >= 2 && this.baccaratcard <= 7) {
        	dealerDraw();
        } else if(getDealerPoints() == 5 && this.baccaratcard >= 4 && this.baccaratcard <= 7) {
        	dealerDraw();
        } else if(getDealerPoints() == 6 && this.baccaratcard == 6 || this.baccaratcard == 7) {
        	dealerDraw();
        } else if(getDealerPoints() == 7) {
        	//no draw
        }
        
        
    }
    
    @Override
	public void nextPlayerMove() {
    	if (checkBaccarat(this.player)) {
            endGame();
        }
    	else if(getPlayerPoints() != 6 && getPlayerPoints() != 7 ) {
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
    	if((this.player.getPoints())>= 10)
    	{
    		return ((this.player.getPoints())%10);
    	}
        return this.player.getPoints();
    }

    @Override
    public int getDealerPoints() {
    	if((this.dealer.getPoints())>= 10)
    	{
    		return ((this.dealer.getPoints())%10);
    	}
        return this.dealer.getPoints();
    }

    
 

    @Override
    public boolean checkBaccarat(final Hand h) {
        return ((h.getPoints() == 8 || h.getPoints() == 9 ) && h.size() == 2);
    }

    @Override
    public void endGame() {
        if (this.deck.size() <= (this.deck.getnDecks() * 13 * 4) / 2) {
            this.deck.shuffle();
        }
        
        //if (checkBlackjack(this.player) && !checkBlackjack(this.dealer)) {
           // account.changeBalance(account.getBalance() + ((this.bet + ((this.bet * 3) / 2))));
       // } else {
            if (checkWin() == 1) {
                account.changeBalance(account.getBalance() + (this.bet * 2));
            } else if (checkWin() == 0) {
                account.changeBalance(account.getBalance() + (this.bet));
            }
        }

	
    }
//}
