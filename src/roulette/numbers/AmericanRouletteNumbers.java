package roulette.numbers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import roulette.number.AmericanRouletteNumber;
import roulette.number.BaseRouletteNumber;

public class AmericanRouletteNumbers /*implements RouletteNumbers*/{

    public static final int NUMBERS = 38;//da togliere?
    public static final int _00_ = 37;
    
    private final List<AmericanRouletteNumber> rouletteNumbers;
    
    public AmericanRouletteNumbers() {
        this.rouletteNumbers = new ArrayList<>(NUMBERS);
        for (final var bRouletteNumber : new BaseRouletteNumbers().getList()) {
            this.rouletteNumbers.add(new AmericanRouletteNumber(bRouletteNumber));
        }
        this.rouletteNumbers.add(new AmericanRouletteNumber(_00_, Color.GREEN));
    }
    
    
   // @Override
    public AmericanRouletteNumber get(final int index) {
        return this.rouletteNumbers.get(index);
    }

//    @Override
    public List<AmericanRouletteNumber> getList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }


}
