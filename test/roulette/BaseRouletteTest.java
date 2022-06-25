package roulette;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import model.roulette.Roulette;
import model.roulette.RouletteFactoryImpl;
import model.roulette.number.RouletteNumber;
import model.roulette.property.Property.Column;
import model.roulette.property.Property.Included;
import model.roulette.property.Property.Parity;
import model.roulette.property.Property.Row;
import model.roulette.wheel.Wheel;
import model.roulette.wheel.WheelFactoryImpl;
import model.roulette.win.Wins;
import org.junit.Before;
import org.junit.Test;
import utility.Pair;

/**
 * JTest for base roulette.
 */
public class BaseRouletteTest {
    
    private static final int TESTCASE = 1000;
    private final Roulette roulette = new RouletteFactoryImpl().createBaseRoulette();
    private final Wheel wheel = new WheelFactoryImpl().createBaseWheel();
    private final Wins wins = new Wins();
    private List<Pair<Object, Double>> bets;
    private double amountBet;
    
    @Before
    public void initRoulette() {
        bets = new LinkedList<>();
        amountBet = 1.00;
    }
    
    
    // test if the roulette returns a number between 0 and 36 in 1000 cases
    @Test
    public void testValidWinningNumber() {
        for (int i = 0; i < TESTCASE; i++) {
            final int n = roulette.spin().getValue();
            assertTrue(n >= 0 && n <= 36);
        }
        
    }

    // test if roulette returns all 37 numbers in 1000 cases
    @Test
    public void testValidIntervalWinningNumber() {
        final List<Integer> valuesWinningNumbers = new LinkedList<>();
        for (int i = 0; i < TESTCASE; i++) {
            final int n = roulette.spin().getValue();
            if (!valuesWinningNumbers.contains(n)) {
                valuesWinningNumbers.add(n);
            }   
        }
        assertEquals(valuesWinningNumbers.size(), 37);
    }
    
    
    // test the pay on the number
    @Test
    public void testNumberBet() {
        // I bet on 0 and the winning number is zero, I win 36
        Integer betNumber = 0;
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, wins.win(bets, wheel.getList().get(0)));
        
        // I bet on 0 and the winning number is 1, I lose the bet
        assertEquals(0, wins.win(bets, wheel.getList().get(1)));
        
        // I bet all the numbers but only 0 will win I win once the bet * 36
        for (betNumber++; betNumber < 38; betNumber++) {
            bets.add(new Pair<Object, Double>(betNumber, amountBet)); 
        }
        assertEquals(36, wins.win(bets, wheel.getList().get(0)));
        
        // I bet 2.22eur on the winning number
        betNumber = 0;
        amountBet = 2.22;
        bets = new LinkedList<Pair<Object, Double>>();
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, wins.win(bets, wheel.getList().get(betNumber)));
    }
    
    
    // test the pay on the color
    @Test
    public void testColorBet() {
        // I bet on red and text win on all red numbers and loss on all others
        final Color winProperty = Color.BLACK;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : wheel.getList()) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 2, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
        
        // I bet on red and black but zero comes out (neutral)
        bets.add(new Pair<Object, Double>(Color.RED, amountBet));
        assertEquals(0, wins.win(bets, wheel.getList().get(0)));
    }    
    

    // test the pay on parity
    @Test
    public void testParityBet() {
        // I bet on even numbers and test the win on all even numbers and the loss on all others
        final Parity winProperty = Parity.EVEN;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : wheel.getList()) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 2, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
        
        // I bet on even and odd but zero comes out (neutral)
        bets.add(new Pair<Object, Double>(Parity.ODD, amountBet));
        assertEquals(0, wins.win(bets, wheel.getList().get(0)));
    }
    
    
    // test the pay if numbers are included
    @Test
    public void testNumberIncludedBet() {
        
        // I bet on the range 1.18 and test the winnings and losses of all numbers
        final Included winProperty = Included._1_18_;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : wheel.getList()) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 2, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
        
        // I bet on the interval 1-18 and 19-36 but zero comes out (neutral)
        bets.add(new Pair<Object, Double>(Included._19_36_, amountBet));
        assertEquals(0, wins.win(bets, wheel.getList().get(0)));
    }
    
   
    
    // test the pay on the column
    @Test
    public void testColumnBet() {        

        // I bet on the first column and text the winnings and losses on all numbers
        final List<RouletteNumber> numbers = wheel.getList();
        final Column winProperty = Column.FIRST;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : numbers) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 3, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
    
        // I bet on the three properties but zero comes out (neutral)
        bets.add(new Pair<Object, Double>(Column.SECOND, amountBet));
        bets.add(new Pair<Object, Double>(Column.THIRD, amountBet));
        assertEquals(0, wins.win(bets, numbers.get(0)));
    }
    
    // test the pay on the line
    @Test
    public void testRowBet() {
        // I bet on the first line and text the winnings and losses on all numbers
        final List<RouletteNumber> numbers = wheel.getList();
        final Row winProperty = Row.FIRST;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : numbers) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 3, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
    
        // I bet on the three properties but zero comes out (neutral)
        bets.add(new Pair<Object, Double>(Row.SECOND, amountBet));
        bets.add(new Pair<Object, Double>(Row.THIRD, amountBet));
        assertEquals(0, wins.win(bets, numbers.get(0)));
        
        //scommetto sulle tre proprietà ma esce il doppio zero(neutro)
        assertEquals(0, wins.win(bets, numbers.get(0)));
    }


}
