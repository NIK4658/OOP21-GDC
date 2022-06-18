package roulette;

import java.util.Random;
import roulette.number.AmericanRouletteNumber;
import roulette.numbers.AmericanRouletteNumbers;


public class AmericanRoulette implements Roulette {
    
    protected final Random random;
    
    public AmericanRoulette() {
        this.random = new Random();
    }

    @Override
    public AmericanRouletteNumber spin() {
        return new AmericanRouletteNumbers().get(this.random.nextInt(AmericanRouletteNumbers.NUMBERS));
    }
}


//Si possono eliminare tutte le classi *Roulette e implementare una interfaccia sul momento?