package model.roulette.number;

import java.awt.Color;

public interface RouletteNumber {
    
    Integer getValue();

    Color getColor();
    
    <P> boolean isProperty(P property);
    
}
