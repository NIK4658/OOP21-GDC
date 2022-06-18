package roulette.numbers;

import java.util.List;

import roulette.number.EuropeanRouletteNumber;
import roulette.number.BaseRouletteNumber;

public interface RouletteNumbers {//da cambiare i tipi

    EuropeanRouletteNumber get(final int index);
    
    List<EuropeanRouletteNumber> getList();
    
}
