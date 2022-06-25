package roulette;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
import utility.Pair;

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
    
    
    //testa se la roulette ritorna un numero compreso tra 0 e 36 in 1000 casi

    @Test
    public void testValidWinningNumber() {
        for (int i = 0; i < TESTCASE; i++) {
            final int n = roulette.spin().getValue();
            assertTrue(n >= 0 && n <= 36);
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
        assertEquals(valuesWinningNumbers.size(), 37);
    }
    
    
    //testa la paga sul numero
    
    @Test
    public void testNumberBet() {
        //Scommetto sullo 0 e il numero vincente è lo zero, vinco 36eur
        Integer betNumber = 0;
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, wins.win(bets, wheel.getList().get(0)));
        
        //Scommetto sullo 0 e il numero vincente è l'1, perdo la scommessa
        assertEquals(0, wins.win(bets, wheel.getList().get(1)));
        
        //Scommetto tutti i numeri ma solo lo 0 sarà vincente vinco una volta la bet * 36
        for (betNumber++; betNumber < 38; betNumber++) {
            bets.add(new Pair<Object, Double>(betNumber, amountBet)); 
        }
        assertEquals(36, wins.win(bets, wheel.getList().get(0)));
        
        //Scommetto 2.22eur sul numero vincente
        betNumber = 0;
        amountBet = 2.22;
        bets = new LinkedList<Pair<Object, Double>>();
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, wins.win(bets, wheel.getList().get(betNumber)));
    }
    
    
    //testa la paga sul colore

    @Test
    public void testColorBet() {
        //scommetto sul rosso e testo la vincita su tutti i numeri rossi e la perdita su tutti gli altri
        final Color winProperty = Color.BLACK;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : wheel.getList()) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 2, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
        
        //scommetto sul rosso e sul nero ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(Color.RED, amountBet));
        assertEquals(0, wins.win(bets, wheel.getList().get(0)));
    }    
    

    //testa la paga sulla parità
    
    @Test
    public void testParityBet() {
        //scommetto sul pari e testo la vincita su tutti i numeri pari e la perdita su tutti gli altri
        final Parity winProperty = Parity.EVEN;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : wheel.getList()) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 2, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
        
        //scommetto sul pari e sul dispari ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(Parity.ODD, amountBet));
        assertEquals(0, wins.win(bets, wheel.getList().get(0)));
    }
    
    
    //testa la paga se numeri compresi 
    @Test
    public void testNumberIncludedBet() {
        
        //scommetto sull'intervallo 1.18 e testo le vincite e perdite di tutti i numeri
        final Included winProperty = Included._1_18_;
        bets.add(new Pair<Object, Double>(winProperty, amountBet));
        for (final RouletteNumber rouletteNumber : wheel.getList()) {
            if (rouletteNumber.isProperty(winProperty)) {
                assertEquals(amountBet * 2, wins.win(bets, rouletteNumber));
            } else {
                assertEquals(0, wins.win(bets, rouletteNumber));
            }
        }
        
        //scommetto sull'intervallo 1-18 e 19-36 ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(Included._19_36_, amountBet));
        assertEquals(0, wins.win(bets, wheel.getList().get(0)));
    }
    
   
    
    //testa la paga sulla colonna
    @Test
    public void testColumnBet() {        

        //scommetto sulla prima colonna e testo le vincite e perdite su tutti i numeri
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
    
        //scommetto sulle tre proprietà ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(Column.SECOND, amountBet));
        bets.add(new Pair<Object, Double>(Column.THIRD, amountBet));
        assertEquals(0, wins.win(bets, numbers.get(0)));
    }
    
    //testa la paga sulla riga
    @Test
    public void testRowBet() {
        
        //scommetto sulla prima riga e testo le vincite e perdite su tutti i numeri
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
    
        //scommetto sulle tre proprietà ma esce lo zero(neutro)
        bets.add(new Pair<Object, Double>(Row.SECOND, amountBet));
        bets.add(new Pair<Object, Double>(Row.THIRD, amountBet));
        assertEquals(0, wins.win(bets, numbers.get(0)));
        
        //scommetto sulle tre proprietà ma esce il doppio zero(neutro)
        assertEquals(0, wins.win(bets, numbers.get(0)));
    }


}
