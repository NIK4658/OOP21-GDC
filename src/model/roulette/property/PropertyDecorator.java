package model.roulette.property;

import java.awt.Color;

import model.roulette.number.RouletteNumber;

public abstract class PropertyDecorator implements RouletteNumber {
    
    private final RouletteNumber decorated;
    
    protected PropertyDecorator(final RouletteNumber decorated) {
        this.decorated = decorated;
    }

    @Override
    public Integer getValue() {
        return this.decorated.getValue();
    }
    
    @Override
    public Color getColor() {
        return this.decorated.getColor();
    }
    
    @Override
    public <P> boolean isProperty(final P property) {
        return this.decorated.isProperty(property);
    }
    
    @Override
    public String toString() {
        return this.decorated.toString();
    }
    
    @Override
    public int hashCode() {
        return this.decorated.hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this.decorated.equals(obj);
    }
    
}
