package roulette.numbers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import roulette.number.BaseRouletteNumber;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.EuropeanRouletteNumber.Sector;

//Modificare la classe in statica
public class EuropeanRouletteNumbers implements RouletteNumbers{
    
    public static final int NUMBERS = 37;
    
    private final List<EuropeanRouletteNumber> rouletteNumbers;
    
    public EuropeanRouletteNumbers() {
        this.rouletteNumbers = new ArrayList<>(NUMBERS);
        final List<BaseRouletteNumber> baseRouletteNumbers = new BaseRouletteNumbers().getList();
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(0), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(1), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(2), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(3), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(4), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(5), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(6), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(7), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(8), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(9), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(10), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(11), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(12), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(13), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(14), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(15), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(16), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(17), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(18), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(19), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(20), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(21), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(22), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(23), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(24), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(25), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(26), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(27), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(28), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(29), Sector.VOISINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(30), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(31), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(32), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(33), Sector.TIER));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(34), Sector.ORPHELINS));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(35), Sector.ZERO));
        this.rouletteNumbers.add(new EuropeanRouletteNumber(baseRouletteNumbers.get(36), Sector.TIER));
    }

    @Override
    public EuropeanRouletteNumber get(final int index) {
        return this.rouletteNumbers.get(index);
    }

    @Override
    public List<EuropeanRouletteNumber> getList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }
    
}
