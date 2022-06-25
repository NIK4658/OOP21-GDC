package model.roulette.wheel;

import java.util.List;
import model.roulette.number.RouletteNumber;

/**
 * The collection that contains the roulette numbers.
 */
public interface Wheel {
    
    /**
     * Return the list of the roulette numbers.
     * 
     * @return the list of the roulette numbers
     */
    List<RouletteNumber> getList();
    
}
