package roulette;

import java.util.Random;

import roulette.number.EuropeanRouletteNumber;
import roulette.numbers.EuropeanRouletteNumbers;


public class EuropeanRoulette implements Roulette {
    
    protected final Random random;
    
    public EuropeanRoulette() {
        this.random = new Random();
    }

    @Override
    public EuropeanRouletteNumber spin() {
        return new EuropeanRouletteNumbers().get(this.random.nextInt(EuropeanRouletteNumbers.NUMBERS));
    }
}
