package roulette.numbers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import roulette.number.AmericanRouletteNumber;
import roulette.number.BaseRouletteNumber;
import roulette.number.RouletteNumber;

public class AmericanRouletteNumbers implements RouletteNumbers {

    public static final int NUMBERS = 38;//da togliere?
    public static final int _00_ = 37;
    
    private final List<AmericanRouletteNumber> rouletteNumbers;
    
    public AmericanRouletteNumbers() {
        this.rouletteNumbers = new ArrayList<>(NUMBERS);
        for (final var baseRouletteNumber : new BaseRouletteNumbers().getBaseList()) {
            this.rouletteNumbers.add(new AmericanRouletteNumber(baseRouletteNumber));
        }
        this.rouletteNumbers.add(new AmericanRouletteNumber(_00_, Color.GREEN));
    }
    
    
    @Override
    public AmericanRouletteNumber get(final int index) {
        return this.rouletteNumbers.get(index);
    }

    @Override
    public List<RouletteNumber> getList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }
    
    public List<AmericanRouletteNumber> getAmericanList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }


}
