package model.roulette;

/**
 * Roulette's factory.
 */
public interface RouletteFactory {
    
    /**
     * Create and return a base roulette with 37 numbers.
     * @return create and return a base roulette
     */
    Roulette createBaseRoulette();
    
    /**
     * Create and return an European roulette with 37 numbers and sectors.
     * @return create and return an European roulette with 37 numbers and sectors
     */
    Roulette createEuropeanRoulette();
    
    /**
     * Create and return an American roulette with 38 numbers with 00 included.
     * @return create and return an American roulette with 38 numbers with 00 included
     */
    Roulette createAmericanRoulette();
    
}
