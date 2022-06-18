package roulette.number;

import java.awt.Color;
import java.util.Objects;

import roulette.numbers.BaseRouletteNumbers;

public class BaseRouletteNumber implements RouletteNumber {
    
    private final int value;
    private final Color color;

    
    public enum Parity{
        EVEN, ODD, NEUTRAL
    }
    
    public enum Included{
        _1_18_, _19_36_, NOT
    }
    
    public enum Row{
        FIRST, SECOND, THIRD, NOT
    }
    
    public enum Column{
        FIRST, SECOND, THIRD, NOT
    }
    
    public BaseRouletteNumber(final int value, final Color color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public Integer getValue() {
        return Integer.valueOf(value); //provare l'autocast
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getNumber() {
        return this.getValue().toString();
    }
    
    public Parity getParity() {
        return this.value == 0 ? Parity.NEUTRAL : this.value % 2 == 0 ? Parity.EVEN : Parity.ODD;
    }
    
    public Included getIncluded() {
        return this.value == 0 ? Included.NOT : value >= 1 && value <= 18 ? Included._1_18_ : Included._19_36_;
    }
    
    public Column getColumn() {
        if (value == 0) {
            return Column.NOT;
        }
        final int nColumn = value / 12;
        return nColumn <= 1 ? Column.FIRST : (nColumn <= 2 ? Column.SECOND : Column.THIRD);
    }
    
    public Row getRow() {
        if (value == 0) {
            return Row.NOT;
        }
        final int nRow = value % 3;
        return nRow == 0 ? Row.FIRST : (nRow == 2 ? Row.SECOND : Row.THIRD);
    }
    
//    public Object getProperty(final Class<?> classProperty) {
//        if (classProperty == Integer.class) {
//            return getValue();
//        } else if (classProperty == Color.class) {
//            return getColor();
//        } else if (classProperty == Parity.class) {
//            return getParity();
//        } else if (classProperty == Included.class) {
//            return getIncluded();
//        } else if (classProperty == Column.class) {
//            return getColumn();
//        } else if (classProperty == Row.class) {
//            return getRow();
//        }
//        return null;//cambiare, magari usare exception o gli optional
//    }
    
    @Override
    public <P> boolean isProperty(P property) {
        if (property.equals(this.getValue())) {
            return true;
        } else if (property.equals(this.getColor())) {
            return true;
        } else if (property.equals(this.getParity())) {
            return true;
        } else if (property.equals(this.getIncluded())) {
            return true;
        } else if (property.equals(this.getColumn())) {
            return true;
        } else if (property.equals(this.getRow())) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "RouletteNumber [value=" + value + ", color=" + color + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseRouletteNumber other = (BaseRouletteNumber) obj;
        return Objects.equals(color, other.color) && value == other.value;
    }


    
}
