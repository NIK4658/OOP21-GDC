package roulette;


import java.awt.Color;

public class RouletteNumber {
    
    //impostare hash e compare
    //even ecc. settarlo nel costruttore o nel metodo? meglio metodo in caso di extends?
    private final int value;
    private final Color color;
    private final Sector sector;
    
    public enum Sector{
        TIER, ORPHELINS, VOISINS, ZERO
    }
    
    public RouletteNumber(final int value,final Color color, final Sector sector) {
        this.value = value;
        this.color = color;
        this.sector = sector;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }
    
    public Sector getSector() {
        return sector;
    }

    @Override
    public String toString() {
        return "RouletteNumber [value=" + value + ", color=" + color + ", sector=" + sector + "]";
    }
    
}
