package roulette.manageRoulette;

import java.awt.Color;
import java.util.List;
import roulette.RouletteNumber;
import roulette.RouletteNumber.Column;
import roulette.RouletteNumber.Included;
import roulette.RouletteNumber.Parity;
import roulette.RouletteNumber.Row;
import roulette.RouletteNumber.Sector;
import utility.Pair;


public class ManageRoulette {
    public static final double COLOR_PAYOUT = 2.0;
    public static final double PARITY_PAYOUT = 2.0;
    public static final double NUMBER_PAYOUT = 36.0;
    public static final double INCLUDED_PAYOUT = 2.0;
    public static final double COLUMN_PAYOUT = 3.0;
    public static final double ROW_PAYOUT = 3.0;
    public static final double ZERO_PAYOUT = 36.0 / 7;
    public static final double VOISINS_PAYOUT = 36.0 / 17;
    public static final double TIER_PAYOUT = 36.0 / 12;
    public static final double ORPHELINS_PAYOUT = 36.0 / 8;
    
    public double calculateWin(final List<Pair<Object, Double>> bets, final RouletteNumber winningNumber) {
        double win = 0.0;
        for (final Pair<Object, Double> p : bets) {
            final Object property = p.getX();
            final double amount = p.getY();
            final Class<? extends Object> classProperty = property.getClass();
            
            if (property == winningNumber.getProperty(classProperty)
                    || (property == Sector.VOISINS && winningNumber.getSector() == Sector.ZERO)) {
                if (classProperty == Integer.class) {
                    win += amount * NUMBER_PAYOUT;
                } else if (classProperty == Color.class || classProperty == Parity.class
                        || classProperty == Included.class) {
                    win += amount * COLOR_PAYOUT;
                } else if (classProperty == Row.class || classProperty == Column.class) {
                    win += amount * ROW_PAYOUT;
                } else if (classProperty == Sector.class) {
                    switch ((Sector) property) {
                        case ZERO:
                            win += amount * ZERO_PAYOUT;
                            break;
                        case VOISINS:
                            win += amount * VOISINS_PAYOUT;
                            break;
                        case TIER:
                            win += amount * TIER_PAYOUT;
                            break;
                        case ORPHELINS:
                            win += amount * ORPHELINS_PAYOUT;
                            break;
                        default:
                            break;
                    }
                }
            } else {
                win -= amount;
            }    
        }
        win = Math.round(win * 100.0) / 100.0;
        //System.out.println(win);
        return win;
    }
    
//    private boolean checkWin(final RouletteNumber winningNumber, final Object property) {
//        if (property.getClass() == Color.class) {
//            return property == winningNumber.getColor();
//        }
//        return false;
//    }
    

}
