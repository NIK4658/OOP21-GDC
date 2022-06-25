package model.roulette.wheel;

/**
 * Wheel's factory
 */
public interface WheelFactory {
    
    /**
     * Creates a basic roulette wheel.
     * 
     * @return a basic roulette wheel
     */
    Wheel createBaseWheel();
    
    /**
     * Creates an American roulette wheel.
     * 
     * @return an American roulette wheel
     */
    Wheel createAmericanWheel();

}
