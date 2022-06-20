package roulette;

import java.util.Random;

import roulette.number.BaseRouletteNumber;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.RouletteNumber;
import roulette.numbers.BaseRouletteNumbers;
import roulette.numbers.EuropeanRouletteNumbers;
import roulette.numbers.RouletteNumbers;

public class BaseRoulette implements Roulette {
    
    private final Random random;
    private final BaseRouletteNumbers rouletteNumbers;
    
    public BaseRoulette() {
        this.random = new Random();
        this.rouletteNumbers = new BaseRouletteNumbers();
    }
    
    @Override
    public BaseRouletteNumber spin() {
        return this.rouletteNumbers.get(this.random.nextInt(BaseRouletteNumbers.NUMBERS));
    }

}