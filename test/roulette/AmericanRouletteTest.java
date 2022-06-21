package roulette;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import roulette.AmericanRoulette;
import roulette.EuropeanRoulette;
import roulette.manageRoulette.ManageRoulette;
import roulette.number.AmericanRouletteNumber;
import roulette.number.BaseRouletteNumber.Column;
import roulette.number.BaseRouletteNumber.Included;
import roulette.number.BaseRouletteNumber.Parity;
import roulette.number.BaseRouletteNumber.Row;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.EuropeanRouletteNumber.Sector;
import roulette.numbers.AmericanRouletteNumbers;
import roulette.numbers.EuropeanRouletteNumbers;
import utility.Pair;


public class AmericanRouletteTest {

    private static final int TESTCASE = 1000;
    private final AmericanRoulette roulette = new AmericanRoulette();
    private final ManageRoulette manageRoulette = new ManageRoulette();
    private List<Pair<Object, Double>> bets;
    private double amountBet;
    
    @Before
    public void initRoulette() {
        bets = new LinkedList<>();
        amountBet = 1.00;
    }
    
    
    //testa se la roulette ritorna un numero compreso tra 0 e 37 in 1000 casi

    @Test
    public void testValidWinningNumber() {
        for (int i = 0; i < TESTCASE; i++) {
            final int n = roulette.spin().getValue();
            assertTrue(n >= 0 && n <= 37);
        }
        
    }

    //testa se la roulette ritorna tutti e 38 i numeri in 1000 casi
    
    @Test
    public void testValidIntervalWinningNumber() {
        final List<Integer> valuesWinningNumbers = new LinkedList<>();
        for (int i = 0; i < TESTCASE; i++) {
            final int n = roulette.spin().getValue();
            if (!valuesWinningNumbers.contains(n)) {
                valuesWinningNumbers.add(n);
            }   
        }
        assertEquals(valuesWinningNumbers.size(), 38);
    }
    
    
    //testa la paga sul numero
    
    @Test
    public void testNumberBet() {
        //Scommetto sullo 0 e il numero vincente è lo zero, vinco 36eur
        Integer betNumber = 0;
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(0)));
        
        //Scommetto sullo 0 e il numero vincente è l'1, perdo la scommessa
        assertEquals(-amountBet, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(1)));
        
        //Scommetto tutti i numeri ma solo lo 0 sarà vincente, perdo la bet 37 volte e vinco una volta la bet * 36
        for (betNumber++; betNumber < AmericanRouletteNumbers.NUMBERS; betNumber++) {
            bets.add(new Pair<Object, Double>(betNumber, amountBet)); 
        }
        assertEquals(-1, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(0)));
        
        //Scommetto 2.22eur sul numero vincente
        betNumber = 0;
        amountBet = 2.22;
        bets = new LinkedList<Pair<Object, Double>>();
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, manageRoulette.calculateWin(bets, new AmericanRouletteNumbers().get(betNumber)));
    }
    
    
    //testa la paga sul colore

    @Test
    public void testColorBet() {
        //scommetto sul rosso e testo la vincita su tutti i numeri rossi e la perdita su tutti gli altri
        //scommetto sul nero e sul rosso ma esce lo zero(verde)
        testPairPropertyBet(Color.BLACK, Color.RED, amountBet);//Non usare funzione esterne il JUnit non rileva i test non corretti poi.
    }    
    

    //testa la paga sulla parità
    
    @Test
    public void testParityBet() {
        //scommetto sul pari e testo la vincita su tutti i numeri pari e la perdita su tutti gli altri 
        //scommetto sul nero e sul rosso ma esce lo zero(neutro)
        testPairPropertyBet(Parity.EVEN, Parity.ODD, amountBet);
    }
    
    
    //testa la paga se numeri compresi 
    @Test
    public void testNumberIncludedBet() {
        //scommetto sull'intervallo 1-18 e testo le vincite e perdite 
        //scommetto sull'intervallo 1-18 e 19-36 ma esce lo zero(neutro)
        System.out.println("check");
        testPairPropertyBet(Included._1_18_, Included._19_36_, amountBet);
    }
    
    
    private void testPairPropertyBet(final Object property1, final Object property2, final double amountBet) {
        //scommetto sulla prima proprietà e testo la vincita e la perdita su tutti i numeri
        bets.add(new Pair<Object, Double>(property1, amountBet));
        for (final AmericanRouletteNumber rouletteNumber : new AmericanRouletteNumbers().getAmericanList()) {
            if (rouletteNumber.isProperty(property1)) {
                assertEquals(amountBet * 2, manageRoulette.calculateWin(bets, rouletteNumber));
            } else {
                assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
            }
        }
        
        //scommetto sulle due proprietà ma esce lo zero(neutro)       
        bets.add(new Pair<Object, Double>(property2, amountBet));
        assertEquals(-amountBet * 2, manageRoulette.calculateWin(bets, new AmericanRouletteNumbers().get(0)));
        //scommetto sulle due proprietà ma esce il doppio zero(neutro) 
        assertEquals(-amountBet * 2, manageRoulette.calculateWin(bets, new AmericanRouletteNumbers().get(37)));
    }
    
    
    //testa la paga sulla colonna
    @Test
    public void testColumnBet() {
        //scommetto sulla prima colonna e testo le vincite e perdite 
        //scommetto su tutte le colonne ma esce lo zero
        testTripletPropertyBet(Column.FIRST, Column.SECOND, Column.THIRD, amountBet);
    }
    
    private void testTripletPropertyBet(final Object property1, final Object property2,
            final Object property3, final double amountBet) {
        //scommetto sulla prima proprietà e testo la vincita e la perdita su tutti i numeri
        bets.add(new Pair<Object, Double>(property1, amountBet));
        for (final AmericanRouletteNumber rouletteNumber : new AmericanRouletteNumbers().getAmericanList()) {
            if (rouletteNumber.isProperty(property1)) {
                assertEquals(amountBet * 3, manageRoulette.calculateWin(bets, rouletteNumber));
            } else {
                assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
            }
        }
        
        //scommetto sulle tre proprietà ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(property2, amountBet));
        bets.add(new Pair<Object, Double>(property3, amountBet));
        assertEquals(-amountBet * 3, manageRoulette.calculateWin(bets, new AmericanRouletteNumbers().get(0)));
    }
    
    //testa la paga sulla riga
    @Test
    public void testRowBet() {
        //scommetto sulla prima colonna e testo le vincite e perdite 
        //scommetto su tutte le colonne ma esce lo zero
        testTripletPropertyBet(Row.FIRST, Row.SECOND, Row.THIRD, amountBet);
    }
    
    private double rounding(final double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

}

