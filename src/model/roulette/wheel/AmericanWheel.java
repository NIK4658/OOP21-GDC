package model.roulette.wheel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.roulette.number.AmericanRouletteNumber;
import model.roulette.number.BaseRouletteNumber;
import model.roulette.number.RouletteNumber;

public class AmericanWheel implements Wheel {

    public static final int NUMBERS = 38;
    public static final int _00_ = 37;
    
    private final List<AmericanRouletteNumber> rouletteNumbers;
    
    public AmericanWheel() {
        this.rouletteNumbers = new ArrayList<>(NUMBERS);
        for (final var baseRouletteNumber : new BaseWheel().getList()) {
            this.rouletteNumbers.add(new AmericanRouletteNumber(baseRouletteNumber));
        }
        this.rouletteNumbers.add(new AmericanRouletteNumber(_00_, Color.GREEN));
    }

    @Override
    public List<RouletteNumber> getList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }

}
