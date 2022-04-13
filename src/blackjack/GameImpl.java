package blackjack;

import java.util.LinkedList;
import java.util.List;

public class GameImpl implements Game {
    
    
    private final List<Card> player = new LinkedList<>();
    private final List<Card> dealer = new LinkedList<>();
    private final Deck deck;


    
    GameImpl(){
        this.deck = new DeckImpl();
        
        
    }
    
    
    
    @Override
    public void askCard() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stay() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void split() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void askDouble() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkWin() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dealerDraw() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dealerStay() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkDealerMove() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int calculatePoints(List<Card> cards) {
        // TODO Auto-generated method stub
        return 0;
    }

}
