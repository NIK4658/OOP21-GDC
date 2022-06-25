package model.roulette.number;

import java.awt.Color;
import model.roulette.property.Property.Column;
import model.roulette.property.Property.Included;
import model.roulette.property.Property.Parity;
import model.roulette.property.Property.Row;

/**
 * An american roulette number, it contains 00 besides the traditional 37 numbers.
 */
public class AmericanRouletteNumber extends BaseRouletteNumber {
    
    /**
     * The effective value of 00
     */
    public static final int _00_ = 37;

    /**
     * Create an american roulette number with the specif parameters.
     * 
     * @param value
     * 
     * @param color
     */
    public AmericanRouletteNumber(final int value, final Color color) {
        super(value, color);
    }
    
    /**
     * Create an american roulette number with the specif parameters.
     * @param rouletteNumber
     */

    
    @Override
    public Parity getParity() {
        return this.getValue() == _00_ ? Parity.NEUTRAL : super.getParity();
    }
    
    @Override
    public Included getIncluded() {
        return this.getValue() == _00_ ? Included.NOT : super.getIncluded();
    }
    
    @Override
    public Column getColumn() {
        return this.getValue() == _00_ ? Column.NOT : super.getColumn();
    }
    
    @Override
    public Row getRow() {
        return this.getValue() == _00_ ? Row.NOT : super.getRow();
    }
    
    @Override
    public String toString() {
        return this.getValue() == _00_ ? "00" : super.toString();
    }

}