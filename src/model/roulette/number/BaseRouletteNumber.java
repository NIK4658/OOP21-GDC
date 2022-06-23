package model.roulette.number;

import java.awt.Color;
import java.util.Objects;

import model.roulette.numbers.BaseRouletteNumbers;

public class BaseRouletteNumber implements RouletteNumber{
    
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
        return this.value;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public Parity getParity() {
        return this.value == 0 ? Parity.NEUTRAL : this.value % 2 == 0 ? Parity.EVEN : Parity.ODD;
    }
    
    public Included getIncluded() {
        return this.value == 0 ? Included.NOT : value >= 1 && value <= 18 ? Included._1_18_ : Included._19_36_;
    }
    
    public Column getColumn() {
        if (this.value == 0) {
            return Column.NOT;
        }
        final int nColumn = this.value / 12;
        return nColumn <= 1 ? Column.FIRST : (nColumn <= 2 ? Column.SECOND : Column.THIRD);
    }
    
    public Row getRow() {
        if (this.value == 0) {
            return Row.NOT;
        }
        final int nRow = this.value % 3;
        return nRow == 0 ? Row.FIRST : (nRow == 2 ? Row.SECOND : Row.THIRD);
    }
    
    @Override
    public <P> boolean isProperty(final P property) {
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
        return this.getValue().toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseRouletteNumber other = (BaseRouletteNumber) obj;
        return Objects.equals(this.color, other.color) && this.value == other.value;
    }

}
