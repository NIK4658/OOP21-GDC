package test;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import roulette.Table;


public class RouletteTest {
    
    private Table roulette;
    
    @Before
    public void initRoulette() {
        roulette = new Table();
    }
    
    //testa se giocato numero compreso tra 0 e 36
    @Test
    public void testValidNumberPlayed () {
        int numberPlayed;
        for (int i=0; i<100; i++) {
            numberPlayed = roulette.numberPlayed();
            assertTrue(numberPlayed >= 0 && numberPlayed <= 36);
        }
    }
    
    //testa se uscito numero compreso tra 0 e 36
    @Test
    public void testValidNumberCameOut() {
        int numberCameOut;
        for (int i=0; i<100; i++) {
            numberCameOut = roulette.numberCameOut();
            assertTrue(numberCameOut >= 0 && numberCameOut <= 36);
        }
    }
    
    //testa se uscito lo stesso numero
//    @Test
//    public void testSameNumber() {
//        
//    }    
    
    
    
    
        
}

