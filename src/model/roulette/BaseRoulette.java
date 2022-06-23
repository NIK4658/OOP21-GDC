package model.roulette;

import java.util.Random;
import model.roulette.number.RouletteNumber;
import model.roulette.wheel.BaseWheel;

public class BaseRoulette implements Roulette {
    
    
    private final Random random;
    private final BaseWheel wheel;
    
    public BaseRoulette() {
        this.random = new Random();
        this.wheel = new BaseWheel();
    }

    @Override
    public RouletteNumber spin() {
        return this.wheel.getList().get(this.random.nextInt(BaseWheel.NUMBERS));
    }

}
