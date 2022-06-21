package view.menu.games.roulette;

import blackjack.BetButton;
import java.awt.Dimension;

public class RouletteBetButton extends BetButton {
    
    private final Object property;
    
    public RouletteBetButton(final Dimension dimension, final Object property) {
        super(dimension);
        this.property = property;
    }

    public Object getProperty() {
        return this.property;
    }
    
    public void incrementBet(final double fvalue) {
        super.incrementBet(fvalue);
    }

}
