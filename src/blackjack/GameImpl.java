package blackjack;

import java.util.LinkedList;
import java.util.List;

import account.AccountManager;

public class GameImpl implements Game {
    
    private final AccountManager account;
    private List<Card> player = new LinkedList<>(); //creare classe hand? usare classe deck?
    private List<Card> dealer = new LinkedList<>(); //creare classe hand? usare classe deck?
    private final Deck deck;


    
    GameImpl(AccountManager account) {
        this.deck = new DeckImpl(6);
        this.account = account;
        
        newTurn();
        
        
        //System.out.println(this.deck);
    }
    
    
    @Override
    public void newTurn() {  
        this.player = new LinkedList<>();
        this.dealer = new LinkedList<>();     
        this.player.add(this.deck.drawRandomCard());
        this.player.add(this.deck.drawRandomCard());   
        this.dealer.add(this.deck.drawRandomCard());
        this.dealer.add(this.deck.drawRandomCard());  
    }

    @Override
    public void askCard() {
        this.player.add(this.deck.drawRandomCard()); 
    }

    @Override
    public void stay() {
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
        stay();
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean checkWin() { 
        if (calculatePoints(this.player) > 21) {
            return false;
        }
        
        if (calculatePoints(this.dealer) > 21) {
            return true;
        }

        return (calculatePoints(this.player) > calculatePoints(this.dealer));
    }

    @Override
    public void dealerDraw() {
        this.dealer.add(this.deck.drawRandomCard());
    }

    @Override
    public void dealerStay() {
        checkWin();
    }

    @Override
    public void nextDealerMove() {
        if (calculatePoints(this.dealer) < 17) {
            dealerDraw();
            //nextDealerMove();
        } else {
            dealerStay();
        }
    }

    @Override
    public int calculatePoints(final List<Card> cards) {
        int points = 0;
        boolean ace = false;
        boolean converted = false;
        
        for (final Card c : cards) {
            if (c.getValue() == 1 && !ace) {
                points += (c.getValue() + 10);
                ace = true;
            } else {
                points += c.getValue();
            }
            
            if (points > 21 && ace && !converted) {
                points -= 10;
                converted = true;
            }  
        }
        return points;
    }


    

    @Override
    public List<Card> getPlayerHand() {
        return this.player;
    }



    @Override
    public List<Card> getDealerHand() {
        return this.dealer;
    }



    @Override
    public int getPlayerPoints() {
        return calculatePoints(this.player);
    }



    @Override
    public int getDealerPoints() {
        return calculatePoints(this.dealer);
    }





  

}
