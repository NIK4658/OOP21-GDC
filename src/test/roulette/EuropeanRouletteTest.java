package test.roulette;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import roulette.EuropeanRoulette;
import roulette.manageRoulette.ManageRoulette;
import roulette.number.BaseRouletteNumber.Column;
import roulette.number.BaseRouletteNumber.Included;
import roulette.number.BaseRouletteNumber.Parity;
import roulette.number.BaseRouletteNumber.Row;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.EuropeanRouletteNumber.Sector;
import roulette.numbers.EuropeanRouletteNumbers;
import utility.Pair;


public class EuropeanRouletteTest {
    

    private static final int TESTCASE = 1000;
    private final EuropeanRoulette roulette = new EuropeanRoulette();
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
        assertEquals(amountBet * 36, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(0)));
        
        //Scommetto sullo 0 e il numero vincente è l'1, perdo la scommessa
        assertEquals(-amountBet, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(1)));
        
        //Scommetto tutti i numeri ma solo lo 0 sarà vincente, perdo la bet 36 volte e vinco una volta la bet * 36
        for (betNumber++; betNumber < EuropeanRouletteNumbers.NUMBERS; betNumber++) {
            bets.add(new Pair<Object, Double>(betNumber, amountBet)); 
        }
        assertEquals(0, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(0)));
        
        //Scommetto 2.22eur sul numero vincente
        betNumber = 0;
        amountBet = 2.22;
        bets = new LinkedList<Pair<Object, Double>>();
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(betNumber)));
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
        for (final EuropeanRouletteNumber rouletteNumber : new EuropeanRouletteNumbers().getEuropeanList()) {
            if (rouletteNumber.isProperty(property1)) {
                assertEquals(amountBet * 2, manageRoulette.calculateWin(bets, rouletteNumber));
            } else {
                assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
            }
        }
        
        //scommetto sulle due proprietà ma esce lo zero(neutro)       
        bets.add(new Pair<Object, Double>(property2, amountBet));
        assertEquals(-amountBet * 2, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(0)));
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
        for (final EuropeanRouletteNumber rouletteNumber : new EuropeanRouletteNumbers().getEuropeanList()) {
            if (rouletteNumber.isProperty(property1)) {
                assertEquals(amountBet * 3, manageRoulette.calculateWin(bets, rouletteNumber));
            } else {
                assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
            }
        }
        
        //scommetto sulle tre proprietà ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(property2, amountBet));
        bets.add(new Pair<Object, Double>(property3, amountBet));
        assertEquals(-amountBet * 3, manageRoulette.calculateWin(bets, new EuropeanRouletteNumbers().get(0)));
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
        bets.add(new Pair<Object, Double>(Sector.ZERO, amountBet));
        EuropeanRouletteNumber rouletteNumber = new EuropeanRouletteNumbers().get(3);
        assertEquals(rounding(amountBet * ManageRoulette.ZERO_PAYOUT),
                manageRoulette.calculateWin(bets, rouletteNumber));
        
        //scommetto sul settore zero ed esce il 36, perdo la bet
        rouletteNumber = new EuropeanRouletteNumbers().get(36);
        assertEquals(-amountBet, manageRoulette.calculateWin(bets, rouletteNumber));
        
        //scommetto sul settore tier ed esce il 5, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.TIER, amountBet));
        rouletteNumber = new EuropeanRouletteNumbers().get(5);
        assertEquals(rounding(amountBet * (36.0 / 12)), manageRoulette.calculateWin(bets, rouletteNumber));
        
        //scommetto sul settore orphelins ed esce il 34, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.ORPHELINS, amountBet));
        rouletteNumber = new EuropeanRouletteNumbers().get(34);
        assertEquals(rounding(amountBet * (36.0 / 8)), manageRoulette.calculateWin(bets, rouletteNumber));
        
        //scommetto sul settore voisins ed esce il 22, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.VOISINS, amountBet));
        rouletteNumber = new EuropeanRouletteNumbers().get(22);
        assertEquals(rounding(amountBet * (36.0 / 17)), manageRoulette.calculateWin(bets, rouletteNumber));
        
        //scommetto sul settore voisins ed esce lo 0, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.VOISINS, amountBet));
        rouletteNumber = new EuropeanRouletteNumbers().get(0);
        assertEquals(rounding(amountBet * (36.0 / 17)), manageRoulette.calculateWin(bets, rouletteNumber));
    }
    
    private double rounding(final double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

}

