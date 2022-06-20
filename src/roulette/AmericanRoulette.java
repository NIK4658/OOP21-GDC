package roulette;

import java.util.Random;
import roulette.number.AmericanRouletteNumber;
import roulette.numbers.AmericanRouletteNumbers;


public class AmericanRoulette implements Roulette {
    
    private final Random random;
    private final AmericanRouletteNumbers roulettenumbers;
    
    public AmericanRoulette() {
        this.random = new Random();
        this.roulettenumbers = new AmericanRouletteNumbers();
    }

    @Override
    public AmericanRouletteNumber spin() {
        return roulettenumbers.get(this.random.nextInt(AmericanRouletteNumbers.NUMBERS));
    }
}