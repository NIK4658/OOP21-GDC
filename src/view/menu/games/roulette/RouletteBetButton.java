package view.menu.games.roulette;

import java.awt.Dimension;

import view.menu.games.component.BetButtonImpl;

public class RouletteBetButton extends BetButtonImpl {
    
    private final Object property;
    
    public RouletteBetButton(final Dimension dimension, final Object property) {
        super(dimension);
        this.property = property;
    }

    public Object getProperty() {
        return this.property;
    }
    
}