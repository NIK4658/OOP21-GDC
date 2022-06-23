package model.roulette.manageRoulette;

import java.awt.Color;
import java.util.List;
import model.roulette.number.BaseRouletteNumber.Column;
import model.roulette.number.BaseRouletteNumber.Included;
import model.roulette.number.BaseRouletteNumber.Parity;
import model.roulette.number.BaseRouletteNumber.Row;
import model.roulette.number.EuropeanRouletteNumber.Sector;
import model.roulette.number.RouletteNumber;
import utility.Pair;


public class Wins {
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
    
    public double win(final List<Pair<Object, Double>> bets, final RouletteNumber winningNumber) {
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
            }
        }
        win = Math.round(win * 100.0) / 100.0;
        return win;
    }

}