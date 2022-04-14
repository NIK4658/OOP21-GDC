package roulette;


import java.awt.Color;

public class RouletteNumber {
    
    //impostare hash e compare
    //even ecc. settarlo nel costruttore o nel metodo? meglio metodo in caso di extends?
    private final int value;
    private final Color color;
    
    public RouletteNumber(final int value) {
        this.value = value;
        this.color = new RouletteNumbers(37, 1).get(value); //da risolvere
    }

    public int getNumber() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "RouletteNumber [value=" + value + ", color=" + color + "]";
    }
    
}
