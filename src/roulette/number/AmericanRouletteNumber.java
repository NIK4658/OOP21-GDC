package roulette.number;

import java.awt.Color;

import roulette.numbers.AmericanRouletteNumbers;

public class AmericanRouletteNumber extends BaseRouletteNumber {

    public AmericanRouletteNumber(final int value, final Color color) {
        super(value, color);
    }
    
    public AmericanRouletteNumber(final BaseRouletteNumber baseRouletteNumber) {
        super(baseRouletteNumber.getValue(), baseRouletteNumber.getColor());
    }
    
    @Override
    public String getNumber() {
        return this.getValue() == AmericanRouletteNumbers._00_ ? "00" : super.getNumber();
    }
    
    @Override
    public Parity getParity() {
        return this.getValue() == AmericanRouletteNumbers._00_ ? Parity.NEUTRAL : super.getParity();
    }
    
    @Override
    public Included getIncluded() {
        return this.getValue() == AmericanRouletteNumbers._00_ ? Included.NOT : super.getIncluded();
    }
    
    @Override
    public Column getColumn() {
        return this.getValue() == AmericanRouletteNumbers._00_ ? Column.NOT : super.getColumn();
    }
    
    @Override
    public Row getRow() {
        return this.getValue() == AmericanRouletteNumbers._00_ ? Row.NOT : super.getRow();
    }

}