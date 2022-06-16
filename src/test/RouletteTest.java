package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import roulette.Roulette;
import roulette.RouletteNumber;
import roulette.RouletteNumber.Column;
import roulette.RouletteNumber.Included;
import roulette.RouletteNumber.Parity;
import roulette.RouletteNumber.Row;
import roulette.RouletteNumber.Sector;
import roulette.RouletteNumbers;
import roulette.manageRoulette.ManageRoulette;
import utility.Pair;


public class RouletteTest {
    

    private static final int TESTCASE = 1000;
    private static final int NUMBERS_ROULETTE = 37;
    private final Roulette roulette = new Roulette();
    private final ManageRoulette manageRoulette = new ManageRoulette();
    private List<Pair<Object, Double>> bets;
    private double amountBet;
    
    @Before
    public void initRoulette() {
        bets = new LinkedList<>();
        amountBet = 1.00;
    }
    
    
    //testa se la roulette ritorna un numero compreso tra 0 e 36 in 1000 casi

    @Test
    public void testValidWinningNumber() {
        for (int i = 0; i < TESTCASE; i++) {
            final int n = roulette.spin().getValue();
            assertTrue(n >= 0 && n <= 36);
        }
        
    }

    //testa se la roulette ritorna tutti e 37 i numeri in 1000 casi
    
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
    
    
    //testa la paga sul numero
    
    @Test
    public void testNumberBet() {
        //Scommetto sullo 0 e il numero vincente è lo zero, vinco 36eur
        Integer betNumber = Integer.valueOf(0);
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, manageRoulette.calculateWin(bets, new RouletteNumbers().get(0)));
        
        //Scommetto sullo 0 e il numero vincente è l'1, perdo la scommessa
        assertEquals(-amountBet, manageRoulette.calculateWin(bets, new RouletteNumbers().get(1)));
        
        //Scommetto tutti i numeri ma solo lo 0 sarà vincente, perdo la bet 36 volte e vinco una volta la bet * 36
        for (betNumber++; betNumber < NUMBERS_ROULETTE; betNumber++) {
            bets.add(new Pair<Object, Double>(betNumber, amountBet)); 
        }
        assertEquals(0, manageRoulette.calculateWin(bets, new RouletteNumbers().get(0)));
        
        //Scommetto 2.22eur sul numero vincente
        betNumber = 0;
        amountBet = 2.22;
        bets = new LinkedList<Pair<Object, Double>>();
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, manageRoulette.calculateWin(bets, new RouletteNumbers().get(betNumber)));
    }
    
    
    //testa la paga sul colore

    @Test
    public void testColorBet() {
        //scommetto sul rosso e testo la vincita su tutti i numeri rossi e la perdita su tutti gli altri
        //scommetto sul nero e sul rosso ma esce lo zero(verde)
        testPairPropertyBet(Color.BLACK, Color.RED, amountBet);
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
        testPairPropertyBet(Included._1_18_, Included._19_36_, amountBet);
    }
    
    
    private void testPairPropertyBet(final Object property1, final Object property2, final double amountBet) {
        //scommetto sulla prima proprietà e testo la vincita e la perdita su tutti i numeri
        bets.add(new Pair<Object, Double>(property1, amountBet));
        for (final RouletteNumber rouletteNumber : new RouletteNumbers().getList()) {
            if (rouletteNumber.getProperty(property1.getClass()) == property1) {
                assertEquals(amountBet * 2, manageRoulette.calculateWin(bets, rouletteNumber));
            } else {
                assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
            }
        }
        
        //scommetto sulle due proprietà ma esce lo zero(neutro)       
        bets.add(new Pair<Object, Double>(property2, amountBet));
        assertEquals(-amountBet * 2, manageRoulette.calculateWin(bets, new RouletteNumbers().get(0)));
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
        for (final RouletteNumber rouletteNumber : new RouletteNumbers().getList()) {
            if (rouletteNumber.getProperty(property1.getClass()) == property1) {
                assertEquals(amountBet * 3, manageRoulette.calculateWin(bets, rouletteNumber));
            } else {
                assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
            }
        }
        
        //scommetto sulle tre proprietà ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(property2, amountBet));
        bets.add(new Pair<Object, Double>(property3, amountBet));
        assertEquals(-amountBet * 3, manageRoulette.calculateWin(bets, new RouletteNumbers().get(0)));
    }
    
    //testa la paga sulla riga
    @Test
    public void testRowBet() {
        //scommetto sulla prima colonna e testo le vincite e perdite 
        //scommetto su tutte le colonne ma esce lo zero
        testTripletPropertyBet(Row.FIRST, Row.SECOND, Row.THIRD, amountBet);
    }
    
    //testa la paga sul settore
    @Test
    public void testSectorBet() {
        //scommetto sul settore zero ed esce il 3, vinco 36/7 volte la bet
        bets.add(new Pair<Object, Double>(Sector.ZERO, Double.valueOf(amountBet)));
        RouletteNumber rouletteNumber = new RouletteNumbers().get(3);
        assertTrue(rounding(amountBet * ManageRoulette.ZERO_PAYOUT)
                == manageRoulette.calculateWin(bets, rouletteNumber));
        
        //scommetto sul settore zero ed esce il 36, perdo la bet
        rouletteNumber = new RouletteNumbers().get(36);
        assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
    }
    
    private double rounding(final double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

}

