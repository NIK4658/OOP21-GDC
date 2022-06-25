package model.roulette.number;

import java.awt.Color;

/**
 * A roulette number that explicitly contains the number and color and provides if it contains a specific properties.
 */
public interface RouletteNumber {
    
    /**
     * Returns the value of the roulette number.
     * 
     * @return the value of the roulette number
     */
    Integer getValue();

    /**
     * Returns the color of the roulette number.
     * 
     * @return gives the value of the roulette number
     */
    Color getColor();
    
    /**
     * Returns if the specific property is included in the roulette number.
     * 
     * @param <P>
     * 
     * @param property
     * 
     * @return returns true if the specific property is included in the roulette number, false otherwise.
     */
    <P> boolean isProperty(P property);
    
}
