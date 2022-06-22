package model.roulette;

import java.util.Random;

import model.roulette.number.BaseRouletteNumber;
import model.roulette.number.EuropeanRouletteNumber;
import model.roulette.number.RouletteNumber;
import model.roulette.numbers.BaseRouletteNumbers;
import model.roulette.numbers.EuropeanRouletteNumbers;
import model.roulette.numbers.RouletteNumbers;

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