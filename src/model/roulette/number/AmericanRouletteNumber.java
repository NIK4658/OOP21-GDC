package model.roulette.number;

import java.awt.Color;
import model.roulette.property.Property.Column;
import model.roulette.property.Property.Included;
import model.roulette.property.Property.Parity;
import model.roulette.property.Property.Row;
import model.roulette.wheel.AmericanWheel;

public class AmericanRouletteNumber extends BaseRouletteNumber {

    public AmericanRouletteNumber(final int value, final Color color) {
        super(value, color);
    }
    
    public AmericanRouletteNumber(final RouletteNumber rouletteNumber) {
        super(rouletteNumber.getValue(), rouletteNumber.getColor());
    }
    
    @Override
    public Parity getParity() {
        return this.getValue() == AmericanWheel._00_ ? Parity.NEUTRAL : super.getParity();
    }
    
    @Override
    public Included getIncluded() {
        return this.getValue() == AmericanWheel._00_ ? Included.NOT : super.getIncluded();
    }
    
    @Override
    public Column getColumn() {
        return this.getValue() == AmericanWheel._00_ ? Column.NOT : super.getColumn();
    }
    
    @Override
    public Row getRow() {
        return this.getValue() == AmericanWheel._00_ ? Row.NOT : super.getRow();
    }
    
    @Override
    public String toString() {
        return this.getValue() == AmericanWheel._00_ ? "00" : super.toString();
    }

}