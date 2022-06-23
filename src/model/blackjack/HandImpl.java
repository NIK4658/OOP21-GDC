package model.blackjack;

import java.util.LinkedList;

/**
 * ..
 */
public class HandImpl extends LinkedList<Card> implements Hand {

    private static final long serialVersionUID = 1L;
    private int points;

    public HandImpl() {
        this.points = 0;
    }

    @Override
    public boolean addCard(final Card c) {
        return this.add(c);
    }

    @Override
    public boolean removeCard(final Card c) {
        return this.remove(c);
    }

    @Override
    public void calculatePoints() {
        this.points = 0;
        boolean ace = false;
        boolean converted = false;
        for (final Card c : this) {
            if (c.getValue() == 1 && !ace) {
                this.points += (c.getValue() + 10);
                ace = true;
            } else {
                this.points += c.getValue();
            }
            if (this.points > 21 && ace && !converted) {
                this.points -= 10;
                converted = true;
            }  
        }
    }
    

    @Override
    public Card getCard(final int index) {
        return this.get(index);
    }

    @Override
    public Hand getHand() {
        return this;
    }
    
    @Override
    public int getBlackJackPoints() {
        return this.points;
    }
    
    @Override
    public int getBaccaratPoints() {
        return this.points % 10;
    }
    
    
    @Override
    public int size() {
        int i = 0;
        for (final Card c : this) {
            i++;
        }
        return i;
    }

    @Override
    public Card getLastCard() {
        return this.get(size() - 1);
    }
    
}
