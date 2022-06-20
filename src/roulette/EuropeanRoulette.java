package roulette;

import java.util.Random;

import roulette.number.EuropeanRouletteNumber;
import roulette.numbers.EuropeanRouletteNumbers;


public class EuropeanRoulette implements Roulette {
    
    private final Random random;
    private final EuropeanRouletteNumbers rouletteNumbers;
    
    public EuropeanRoulette() {
        this.random = new Random();
        this.rouletteNumbers = new EuropeanRouletteNumbers();
    }

    @Override
    public EuropeanRouletteNumber spin() {
        return this.rouletteNumbers.get(this.random.nextInt(EuropeanRouletteNumbers.NUMBERS));
    }
}
