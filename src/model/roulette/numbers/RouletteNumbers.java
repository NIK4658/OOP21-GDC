package model.roulette.numbers;

import java.util.List;

import model.roulette.number.RouletteNumber;

public interface RouletteNumbers {

    RouletteNumber get(final int index); //ci va final?
    
    List<RouletteNumber> getList();
    
}
