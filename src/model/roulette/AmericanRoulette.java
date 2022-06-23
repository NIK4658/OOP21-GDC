package model.roulette;

import java.util.Random;
import model.roulette.number.AmericanRouletteNumber;
import model.roulette.wheel.AmericanWheel;


public class AmericanRoulette implements Roulette {
    
    private final Random random;
    private final AmericanWheel roulettenumbers;
    
    public AmericanRoulette() {
        this.random = new Random();
        this.roulettenumbers = new AmericanWheel();
    }

    @Override
    public AmericanRouletteNumber spin() {
        return roulettenumbers.get(this.random.nextInt(AmericanWheel.NUMBERS));
    }
    
}