package model.roulette;

import java.util.Random;
import model.roulette.number.EuropeanRouletteNumber;
import model.roulette.numbers.EuropeanRouletteNumbers;


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
