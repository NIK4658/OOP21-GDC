package roulette.number;

import java.awt.Color;

public interface RouletteNumber {
    
    Integer getValue();

    Color getColor();
    
    String getNumber();
    
    <P> boolean isProperty(P property);
    
}
