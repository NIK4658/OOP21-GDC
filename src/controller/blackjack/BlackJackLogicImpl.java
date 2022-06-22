package controller.blackjack;

import model.account.BalanceManager;
import model.blackjack.Deck;
import model.blackjack.DeckImpl;
import model.blackjack.Hand;
import model.blackjack.HandImpl;

/**
 * Classe principale gestione gioco blackjack.
 */
public class BlackJackLogicImpl implements BlackJackLogic {
    
    private final BalanceManager account;
    private final Deck deck;
    private double bet;
    private double lastwin;
    private Hand player;
    private Hand dealer;
    
    public BlackJackLogicImpl(final BalanceManager account) {
        this.deck = new DeckImpl(6);
        this.deck.generateDeck();
        this.account = account;
        this.bet = 0;
    }
    
    @Override
    public void startGame(final double bet) {
        this.bet = bet;
        this.player = new HandImpl();
        this.dealer = new HandImpl();
        this.player.addCard(this.deck.drawRandomCard());
        this.player.addCard(this.deck.drawRandomCard());
        this.dealer.addCard(this.deck.drawRandomCard());
        this.dealer.addCard(this.deck.drawRandomCard());
        this.dealer.getCard(1).turnOver();
        this.dealer.calculatePoints();
        this.player.calculatePoints();
        
        if (checkBlackjack(this.player)) {
            endGame();
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
    public boolean askDouble() {
        if (account.withdraw(bet)) {
            this.bet *= 2;
            askCard();
            stand();
            return true;
        } else {
            return false;
        }
    }



    @Override
    public int checkWin() { 
        if (this.player.getPoints() > 21) {
            return -1;
        }
        if (this.dealer.getPoints() > 21) {
            return 1;
        }
        if (this.player.getPoints() == this.dealer.getPoints()) {
            return 0;
        } 
        if (this.player.getPoints() < this.dealer.getPoints()) {
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
        } else {
            if (getDealerPoints() < 17) {
                dealerDraw();
                nextDealerMove();
            } else {
                endGame();
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

    @Override
    public boolean checkInsurance() {
        return (this.dealer.getCard(0).getValue() == 1 && this.dealer.size() == 2 && !checkBlackjack(this.player));
    }

    @Override
    public boolean checkBlackjack(final Hand h) {
        return (h.getPoints() == 21 && h.size() == 2);
    }

    @Override
    public void endGame() {
        if (this.deck.size() <= (this.deck.getnDecks() * 13 * 4) / 2) {
            this.deck.shuffle();
        }
        if (checkBlackjack(this.player) && !checkBlackjack(this.dealer)) {
            this.lastwin = (this.bet + ((this.bet * 3) / 2));
        } else {
            if (checkWin() == 1) {
                this.lastwin = this.bet * 2;
            } else if (checkWin() == 0) {
                this.lastwin = this.bet;
            }
        }
    }

    @Override
    public boolean calculateInsurance(final boolean insurance) {
        if (insurance) {
            if (checkBlackjack(this.dealer)) {
                account.changeBalance(account.getBalance() + (this.bet));
                return false;
            } else {
                account.changeBalance(account.getBalance() - (this.bet / 2));
                return true;
            }
        } else {
            return (!checkBlackjack(this.dealer));
        }
    }
    
    public double getLastWin() {
        return this.lastwin;
    }

    public double getBet() {
        return this.bet;
    }
    
    @Override
    public boolean canInsurance() {
        return (account.getBalance() >= (this.bet / 2));
    }
}