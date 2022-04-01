package roulette.number;

import java.awt.Color;

public class Number {
    
    //impostare hash e compare
    //even ecc. settarlo nel costruttore o nel metodo? meglio metodo in caso di extends?
    private final int value;
    private final Color color;
    
    public Number(final int value, final Color color) {
        this.value = value;
        this.color = color;
    }

    public int getNumber() {
        return value;
    }

    public Color getColor() {
        return color;
    }
    
}
