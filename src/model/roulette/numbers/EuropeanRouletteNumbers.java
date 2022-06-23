package model.roulette.numbers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import model.roulette.number.BaseRouletteNumber;
import model.roulette.number.EuropeanRouletteNumber;
import model.roulette.number.RouletteNumber;
import model.roulette.number.EuropeanRouletteNumber.Sector;

//Modificare la classe in statica
public class EuropeanRouletteNumbers implements RouletteNumbers{
    
    public static final int NUMBERS = BaseRouletteNumbers.NUMBERS;
    
    private final List<EuropeanRouletteNumber> rouletteNumbers;
    
    public EuropeanRouletteNumbers() {
        this.rouletteNumbers = new ArrayList<>(NUMBERS);
        final List<RouletteNumber> rouletteNumbers = new BaseRouletteNumbers().getList();
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(0), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(1), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(2), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(3), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(4), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(5), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(6), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(7), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(8), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(9), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(10), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(11), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(12), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(13), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(14), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(15), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(16), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(17), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(18), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(19), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(20), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(21), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(22), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(23), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(24), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(25), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(26), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(27), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(28), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(29), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(30), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(31), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(32), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(33), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(34), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(35), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(rouletteNumbers.get(36), Sector.TIER));
    }

    @Override
    public EuropeanRouletteNumber get(final int index) {
        return this.rouletteNumbers.get(index);
    }

    @Override
    public List<RouletteNumber> getList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }
    
}
