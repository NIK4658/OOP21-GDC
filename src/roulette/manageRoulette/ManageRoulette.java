package roulette.manageRoulette;

import java.awt.Color;
import java.util.List;

import roulette.number.BaseRouletteNumber;
import roulette.number.BaseRouletteNumber.Column;
import roulette.number.BaseRouletteNumber.Included;
import roulette.number.BaseRouletteNumber.Parity;
import roulette.number.BaseRouletteNumber.Row;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.EuropeanRouletteNumber.Sector;
import roulette.number.RouletteNumber;
import utility.Pair;


public class ManageRoulette {
    public static final double NUMBER_PAYOUT = 36.0;
    public static final double COLOR_PAYOUT = 2.0;
    public static final double PARITY_PAYOUT = 2.0;
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

            
            
            if (winningNumber.isProperty(property)) {
                if (property instanceof Integer) {
                    win += amount * NUMBER_PAYOUT;
                } else if (property instanceof Color) {
                    win += amount * COLOR_PAYOUT;
                } else if (property instanceof Parity) {
                    win += amount * PARITY_PAYOUT;
                } else if (property instanceof Included) {
                    win += amount * INCLUDED_PAYOUT;
                } else if (property instanceof Column) {
                    win += amount * COLUMN_PAYOUT;
                } else if (property instanceof Row) {
                    win += amount * ROW_PAYOUT;
                } else if (property instanceof Sector) {
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
            
            
            
            
            
//          final Class<? extends Object> classProperty = property.getClass();
//            if (property == winningNumber.getProperty(classProperty)
//                    || (property == Sector.VOISINS && winningNumber.getSector() == Sector.ZERO)) {
//                if (classProperty == Integer.class) {
//                    win += amount * NUMBER_PAYOUT;
//                } else if (classProperty == Color.class || classProperty == Parity.class
//                        || classProperty == Included.class) {
//                    win += amount * COLOR_PAYOUT;
//                } else if (classProperty == Row.class || classProperty == Column.class) {
//                    win += amount * ROW_PAYOUT;
//                } else if (classProperty == Sector.class) {
//                    switch ((Sector) property) {
//                        case ZERO:
//                            win += amount * ZERO_PAYOUT;
//                            break;
//                        case VOISINS:
//                            win += amount * VOISINS_PAYOUT;
//                            break;
//                        case TIER:
//                            win += amount * TIER_PAYOUT;
//                            break;
//                        case ORPHELINS:
//                            win += amount * ORPHELINS_PAYOUT;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            } else {
//                win -= amount;
//            }  
            
            
        }
        win = Math.round(win * 100.0) / 100.0;
        return win;
    }
    

}
