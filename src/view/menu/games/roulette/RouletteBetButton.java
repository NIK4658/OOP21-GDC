package view.menu.games.roulette;

import java.awt.Dimension;
import view.menu.games.component.BetButtonImpl;

/**
 * Create an invisible bet button for roulette.
 */
public class RouletteBetButton extends BetButtonImpl {
    
    private static final long serialVersionUID = 1L;
    private final Object property;
    
    /**
     * Create an invisible bet button with the given property and scalable with the given dimension.
     * 
     * @param dimension
     * 
     * @param property
     * 
     */
    public RouletteBetButton(final Dimension dimension, final Object property) {
        super(dimension);
        this.property = property;
    }

    /**
     * Returns the property.
     * 
     * @return returns the property
     * 
     */
    public Object getProperty() {
        return this.property;
    }
    
}