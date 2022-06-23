package model.roulette.numbers;

import java.util.List;

import model.roulette.number.RouletteNumber;

public interface RouletteNumbers {

    RouletteNumber get(int index);
    
    List<RouletteNumber> getList();
    
}
