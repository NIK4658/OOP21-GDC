package blackjack;

import java.util.LinkedList;
import java.util.List;

public class GameImpl implements Game {
    
    
    private final List<Card> player = new LinkedList<>(); //creare classe hand? usare classe deck?
    private final List<Card> dealer = new LinkedList<>(); //creare classe hand? usare classe deck?
    private final Deck deck;


    
    GameImpl() {
        this.deck = new DeckImpl();
        
        this.player.add(new CardImpl());
        this.player.add(new CardImpl());
        
        this.dealer.add(new CardImpl());
        this.dealer.add(new CardImpl(false)); 
    }
    
    
    
    @Override
    public void askCard() {
        this.player.add(new CardImpl()); 
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
        this.dealer.add(new CardImpl());
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
