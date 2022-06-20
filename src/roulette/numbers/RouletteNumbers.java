package roulette.numbers;

import java.util.List;

import roulette.number.RouletteNumber;

public interface RouletteNumbers {

    RouletteNumber get(final int index); //ci va final?
    
    List<RouletteNumber> getList();
    
}
