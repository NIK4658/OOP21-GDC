package model.roulette;

import model.roulette.number.RouletteNumber;

/**
 * A roulette that returns a roulette number.
 */
public interface Roulette {
    
    /**
     * Return a random roulette number contains in roulette.
     * 
     * @return return a random roulette number contains in roulette
     */
    RouletteNumber spin();

}
