package model.roulette;

import java.util.Random;
import model.roulette.number.BaseRouletteNumber;
import model.roulette.numbers.BaseRouletteNumbers;

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