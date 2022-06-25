package roulette;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import model.roulette.Roulette;
import model.roulette.RouletteFactoryImpl;
import model.roulette.number.RouletteNumber;
import model.roulette.property.EuropeanSectors;
import model.roulette.property.Property.Column;
import model.roulette.property.Property.Parity;
import model.roulette.property.Property.Row;
import model.roulette.property.Property.Sector;
import model.roulette.property.SectorRoulette;
import model.roulette.wheel.Wheel;
import model.roulette.wheel.WheelFactoryImpl;
import model.roulette.win.Wins;
import org.junit.Before;
import org.junit.Test;
import utility.Pair;


public class EuropeanRouletteTest {
    

    private static final int TESTCASE = 1000;
    private final Roulette roulette = new RouletteFactoryImpl().createEuropeanRoulette();
    private final Wheel wheel = new WheelFactoryImpl().createBaseWheel();
    private final List<RouletteNumber> rouletteNumbers = wheel.getList();
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
        assertEquals(amountBet * 36, wins.win(bets, rouletteNumbers.get(0)));
        
        //Scommetto sullo 0 e il numero vincente è l'1, perdo la scommessa
        assertEquals(0, wins.win(bets, rouletteNumbers.get(1)));
        
        //Scommetto tutti i numeri ma solo lo 0 sarà vincente, perdo la bet 36 volte e vinco una volta la bet * 36
        for (betNumber++; betNumber < 37; betNumber++) {
            bets.add(new Pair<Object, Double>(betNumber, amountBet)); 
        }
        assertEquals(36, wins.win(bets, rouletteNumbers.get(0)));
        
        //Scommetto 2.22eur sul numero vincente
        betNumber = 0;
        amountBet = 2.22;
        bets = new LinkedList<Pair<Object, Double>>();
        bets.add(new Pair<>(betNumber, amountBet));
        assertEquals(amountBet * 36, wins.win(bets, rouletteNumbers.get(betNumber)));
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
    }
    
    //testa la paga sul settore
    @Test
    public void testSectorBet() {
        //scommetto sul settore zero ed esce il 3, vinco 36/7 volte la bet
        bets.add(new Pair<Object, Double>(Sector.ZERO, amountBet));
        RouletteNumber rouletteNumber = getEuropeanRouletteNumber(rouletteNumbers.get(3));
        assertEquals(rounding(amountBet * 36.0 / 7), wins.win(bets, rouletteNumber));
        
        //scommetto sul settore zero ed esce il 36, perdo la bet
        rouletteNumber = rouletteNumbers.get(36);
        assertEquals(0, wins.win(bets, rouletteNumber));
        
        //scommetto sul settore tier ed esce il 5, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.TIER, amountBet));
        rouletteNumber = getEuropeanRouletteNumber(rouletteNumbers.get(5));;
        assertEquals(rounding(amountBet * (36.0 / 12)), wins.win(bets, rouletteNumber));
        
        //scommetto sul settore orphelins ed esce il 34, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.ORPHELINS, amountBet));
        rouletteNumber = getEuropeanRouletteNumber(rouletteNumbers.get(34));;
        assertEquals(rounding(amountBet * (36.0 / 8)), wins.win(bets, rouletteNumber));
        
        //scommetto sul settore voisins ed esce il 22, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.VOISINS, amountBet));
        rouletteNumber = getEuropeanRouletteNumber(rouletteNumbers.get(22));;
        assertEquals(rounding(amountBet * (36.0 / 17)), wins.win(bets, rouletteNumber));
        
        //scommetto sul settore voisins ed esce lo 0, vinco la bet
        bets = new LinkedList<>();
        bets.add(new Pair<>(Sector.VOISINS, amountBet));
        rouletteNumber = getEuropeanRouletteNumber(rouletteNumbers.get(0));;
        assertEquals(rounding(amountBet * (36.0 / 17)), wins.win(bets, rouletteNumber));
    }
    
    private RouletteNumber getEuropeanRouletteNumber(final RouletteNumber rouletteNumber) {
        return new SectorRoulette(rouletteNumber) {
            @Override
            protected Sector getSector(final RouletteNumber rouletteNumber) {
                return new EuropeanSectors().getList().get(rouletteNumber.getValue());
            }
        };
    }

    
    private double rounding(final double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
    
}

