package model.roulette;

import java.util.Random;

import model.roulette.number.RouletteNumber;
import model.roulette.wheel.AmericanWheel;


public class AmericanRoulette implements Roulette {
    
    private final Random random;
    private final AmericanWheel roulettenumbers;
    
    public AmericanRoulette() {
        this.random = new Random();
        this.roulettenumbers = new AmericanWheel();
    }

    @Override
    public RouletteNumber spin() {
        return roulettenumbers.getList().get(this.random.nextInt(AmericanWheel.NUMBERS));
    }
    
}