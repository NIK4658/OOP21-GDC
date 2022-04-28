package blackjack;

import account.AdvancedBalanceManager;


/**
 * Classe principale gestione gioco blackjack.
 */
public class GameImpl implements Game {
    
    private final AdvancedBalanceManager account;
    private final Deck deck;
    private int bet;
    private Hand player;
    private Hand dealer;
    
    GameImpl(final AdvancedBalanceManager account) {
        this.deck = new DeckImpl(6);
        this.deck.generateDeck();
        this.account = account;
        this.player = new HandImpl();
        this.dealer = new HandImpl();
        this.bet = 0;
    }
    
    @Override
    public void startGame(final int bet) {
        this.bet = bet;
        this.player = new HandImpl();
        this.dealer = new HandImpl();    
        this.player.addCard(this.deck.drawRandomCard());
        this.player.addCard(this.deck.drawRandomCard());   
        this.dealer.addCard(this.deck.drawRandomCard());
        this.dealer.addCard(this.deck.drawRandomCard());  
        this.dealer.getCard(1).turnOver();
        //System.out.println(this.dealer.getCard(1).isFaceDown());
        this.dealer.calculatePoints();
        this.player.calculatePoints();
        
        if (this.player.getPoints() == 21) {
            System.out.println("Blackjack!");
            checkWin();
        }
        
    }
    
    
    
    
    @Override
    public void askCard() {
        this.player.addCard(this.deck.drawRandomCard()); 
        this.player.calculatePoints();
    }

    @Override
    public void stand() {
        nextDealerMove();
    }

    @Override
    public void split() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void askDouble() {
        //raddoppio puntata
        askCard();
        stand();
    }



    @Override
    public void checkWin() { 
        int win = 0;
        if (this.player.getPoints() > 21) {
            win = -1;
        }
        
        if (this.dealer.getPoints() > 21) {
            win = 1;
        }

        if (this.player.getPoints() == this.dealer.getPoints()) {
            win = 0;
        } 
        
        if (this.player.getPoints() < this.dealer.getPoints()) {
            win = -1;
        } else {
            win = 1;
        }
        
        
        account.changeBalance(account.getBalance() + (this.bet * win));
        
        //check mischiare mazzo
        
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
        } else {
            if (getDealerPoints() < 17) {
                dealerDraw();
            } else {
                checkWin();
            }
        }
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
        return this.player.getPoints();
    }

    @Override
    public int getDealerPoints() {
        return this.dealer.getPoints();
    }
}
