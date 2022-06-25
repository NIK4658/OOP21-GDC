package model.roulette.wheel;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import model.roulette.number.AmericanRouletteNumber;
import model.roulette.number.RouletteNumber;

/**
 * Wheel' factory, creates different type of wheel.
 */
public class WheelFactoryImpl implements WheelFactory {
    
    @Override
    public Wheel createBaseWheel() {
        return new BaseWheel();
    }
    
    @Override
    public Wheel createAmericanWheel() {
        final List<RouletteNumber> list = new LinkedList<>(this.createBaseWheel().getList());
        list.add(new AmericanRouletteNumber(AmericanRouletteNumber._00_, Color.GREEN));
        return () -> Collections.unmodifiableList(list);
    }

}
