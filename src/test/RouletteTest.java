package test;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import roulette.Roulette;
import roulette.RouletteNumber;


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
    
    //testa se uscito numero compreso tra 0 e 36
    @Test
    public void testValidNumberCameOut() {
//        int numberCameOut;
//        for (int i=0; i<100; i++) {
//            numberCameOut = roulette.numberCameOut();
//            assertTrue(numberCameOut >= 0 && numberCameOut <= 36);
//        }
    }
    
    //testa se uscito lo stesso numero
//    @Test
//    public void testSameNumber() {
//        
//    }    
    
    
    
    
        
}

