package model.roulette.wheel;

import java.util.List;

import model.roulette.number.RouletteNumber;

public interface Wheel {

//    RouletteNumber get(int index);
    
    List<RouletteNumber> getList();
    
}
