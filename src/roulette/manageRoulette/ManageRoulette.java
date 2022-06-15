package roulette.manageRoulette;

import java.awt.Color;
import java.util.List;
import roulette.RouletteNumber;
import roulette.RouletteNumber.Column;
import roulette.RouletteNumber.Included;
import roulette.RouletteNumber.Parity;
import utility.Pair;


public class ManageRoulette {
    public static final int COLOR_PAYOUT = 2;
    public static final int PARITY_PAYOUT = 2;
    public static final int NUMBER_PAYOUT = 36;
    public static final int INCLUDED_PAYOUT = 2;
    public static final int COLUMN_PAYOUT = 3;
    public static final int ROWS_PAYOUT = 3;
    
    public double calculateWin(final List<Pair<Object, Double>> bet, final RouletteNumber winningNumber) {
        double win = 0;
        for (final Pair<Object, Double> p : bet) {
            final Object property = p.getX();
            final double amount = p.getY();
            final Class<? extends Object> classProperty = property.getClass();
            
            if (classProperty == Color.class) {
                if ((Color) property == winningNumber.getColor()) {
                    win += amount * COLOR_PAYOUT;
                } else {
                    win -= amount;
                }
            } else if (classProperty == Integer.class) {
                if ((Integer) property == winningNumber.getValue()) {
                    win += amount * NUMBER_PAYOUT;
                } else {
                    win -= amount;
                }
            } else if (classProperty == Parity.class) {
                if ((Parity) property == winningNumber.getParity()) {
                    win += amount * PARITY_PAYOUT;
                } else {
                    win -= amount;
                }
            } else if (classProperty == Included.class) {
                if ((Included) property == winningNumber.getIncluded()) {
                    win += amount * INCLUDED_PAYOUT;
                } else {
                    win -= amount;
                }
            } else if (classProperty == Column.class) {
                if ((Column) property == winningNumber.getColumn()) {
                    win += amount * COLUMN_PAYOUT;
                } else {
                    win -= amount;
                }
            }
        }
        return win;
    }
    
    private boolean checkWin(final RouletteNumber winningNumber, final Object property) {
        if (property.getClass() == Color.class) {
            return property == winningNumber.getColor();
        }
        return false;
    }
    

}
