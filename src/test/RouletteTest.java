package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import roulette.Roulette;
import roulette.RouletteNumber;
import roulette.RouletteNumber.Column;
import roulette.RouletteNumber.Included;
import roulette.RouletteNumber.Parity;
import roulette.RouletteNumbers;
import roulette.manageRoulette.ManageRoulette;
import utility.Pair;


public class RouletteTest {
    
    private Roulette roulette;
    private int testcases;
    
    @Before
    public void initRoulette() {
        roulette = new Roulette();
        testcases = 1000;
    }
    
    //testa se la roulette ritorna un numero compreso tra 0 e 36 in 1000 casi
    @Test
    public void testValidWinningNumber() {
        for (int i = 0; i < testcases; i++) {
            final int n = roulette.spin().getValue();
            assertTrue(n >= 0 && n <= 36);
        }
    }

    //testa se la roulette ritorna tutti e 37 i numeri in 1000 casi
    @Test
    public void testValidIntervalWinningNumber() {
        final List<Integer> valuesWinningNumbers = new LinkedList<>();
        for (int i = 0; i < testcases; i++) {
            final int n = roulette.spin().getValue();
            if (!valuesWinningNumbers.contains(n)) {
                valuesWinningNumbers.add(n);
            }   
        }
        assertSame(valuesWinningNumbers.size(), 37);
    }
    
    //testa se il numero vincente possiede le proprietà corrette(color, value, ecc.) in 1000 casi
    @Test
    public void testValidPropertiesWinningNumber() {
        for (int i = 0; i < testcases; i++) {
            final RouletteNumber winningNumber = roulette.spin();
            final int value = winningNumber.getValue();
            assertEquals(winningNumber, new RouletteNumbers().get(value));
        }
    }
    
    //testa la paga sul colore
    @ParameterizedTest
    @ValueSource(ints = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 })//da sistemare, togliere
    public void testWinningColorBet(final int redNumber) {
        final double amountBet = 1;
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        bets.add(new Pair<Object, Double>(Color.RED, Double.valueOf(amountBet)));
        final ManageRoulette manageRoulette = new ManageRoulette();
        RouletteNumber rouletteNumber = new RouletteNumbers().get(redNumber);
        //scommessa vincente verifico importo vinto
        assertTrue(amountBet * ManageRoulette.COLOR_PAYOUT == manageRoulette.calculateWin(bets, rouletteNumber));
        rouletteNumber = new RouletteNumbers().get(0);
        //scommessa perdente verifico importo perso
        System.out.println(redNumber);
        assertTrue(-amountBet == manageRoulette.calculateWin(bets, rouletteNumber));
    }    
    
    //testa la paga sul numero
    @Test
    public void testWinningNumberBet() {
        double amountBet = 1;
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        bets.add(new Pair<Object, Double>(1, Double.valueOf(amountBet)));
        final ManageRoulette manageRoulette = new ManageRoulette();
        RouletteNumber rouletteNumber = new RouletteNumbers().get(1);
        //scommessa vincente verifico importo vinto
        assertTrue(amountBet * ManageRoulette.NUMBER_PAYOUT == manageRoulette.calculateWin(bets, rouletteNumber));
        rouletteNumber = new RouletteNumbers().get(0);
        //scommessa perdente verifico importo perso
        assertTrue(-amountBet == manageRoulette.calculateWin(bets, rouletteNumber));
    }
    
    //testa la paga sulla parità
    @Test
    public void testWinningParityBet() {
        double amountBet = 1;
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        bets.add(new Pair<Object, Double>(Parity.EVEN, Double.valueOf(amountBet)));
        final ManageRoulette manageRoulette = new ManageRoulette();
        RouletteNumber rouletteNumber = new RouletteNumbers().get(2);
        //scommessa vincente verifico importo vinto
        assertTrue(amountBet * ManageRoulette.PARITY_PAYOUT == manageRoulette.calculateWin(bets, rouletteNumber));
        rouletteNumber = new RouletteNumbers().get(0);
        //scommessa perdente verifico importo perso
        assertTrue(-amountBet == manageRoulette.calculateWin(bets, rouletteNumber));
    }
    
    //testa la paga se numeri compresi 
    @Test
    public void testWinningNumberIncludedBet() {
        double amountBet = 1;
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        bets.add(new Pair<Object, Double>(Included._1_18_, Double.valueOf(amountBet)));
        final ManageRoulette manageRoulette = new ManageRoulette();
        RouletteNumber rouletteNumber = new RouletteNumbers().get(18);
        //scommessa vincente verifico importo vinto
        assertTrue(amountBet * ManageRoulette.INCLUDED_PAYOUT == manageRoulette.calculateWin(bets, rouletteNumber));
        rouletteNumber = new RouletteNumbers().get(0);
        //scommessa perdente verifico importo perso
        assertTrue(-amountBet == manageRoulette.calculateWin(bets, rouletteNumber));
    }
    
    //testa la paga sulla colonna
    @Test
    public void testWinningColumnBet() {
        double amountBet = 1;
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        bets.add(new Pair<Object, Double>(Column.FIRST, Double.valueOf(amountBet)));
        final ManageRoulette manageRoulette = new ManageRoulette();
        RouletteNumber rouletteNumber = new RouletteNumbers().get(1);
        //scommessa vincente verifico importo vinto
        assertTrue(amountBet * ManageRoulette.COLUMN_PAYOUT == manageRoulette.calculateWin(bets, rouletteNumber));
        rouletteNumber = new RouletteNumbers().get(0);
        //scommessa perdente verifico importo perso
        assertTrue(-amountBet == manageRoulette.calculateWin(bets, rouletteNumber));
    }
    
    //testa la paga sulla riga
    @Test
    public void testWinningRowBet() {
        double amountBet = 1;
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        bets.add(new Pair<Object, Double>(Column.FIRST, Double.valueOf(amountBet)));
        final ManageRoulette manageRoulette = new ManageRoulette();
        RouletteNumber rouletteNumber = new RouletteNumbers().get(1);
        //scommessa vincente verifico importo vinto
        assertTrue(amountBet * ManageRoulette.COLUMN_PAYOUT == manageRoulette.calculateWin(bets, rouletteNumber));
        rouletteNumber = new RouletteNumbers().get(0);
        //scommessa perdente verifico importo perso
        assertTrue(-amountBet == manageRoulette.calculateWin(bets, rouletteNumber));
    }
        
}

