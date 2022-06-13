package roulette;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import roulette.RouletteNumber.Sector;

//Modificare la classe in statica
public class RouletteNumbers {
    
    private final List<RouletteNumber> rouletteNumbers;
    
    public RouletteNumbers() {
        this.rouletteNumbers = new ArrayList<>();
        this.rouletteNumbers.add(new RouletteNumber(0, Color.GREEN, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(1, Color.RED, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(2, Color.BLACK, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(3, Color.RED, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(4, Color.BLACK, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(5, Color.RED, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(6, Color.BLACK, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(7, Color.RED, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(8, Color.BLACK, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(9, Color.RED, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(10, Color.BLACK, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(11, Color.BLACK, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(12, Color.RED, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(13, Color.BLACK, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(14, Color.RED, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(15, Color.BLACK, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(16, Color.RED, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(17, Color.BLACK, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(18, Color.RED, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(19, Color.RED, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(20, Color.BLACK, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(21, Color.RED, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(22, Color.BLACK, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(23, Color.RED, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(24, Color.BLACK, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(25, Color.RED, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(26, Color.BLACK, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(27, Color.RED, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(28, Color.BLACK, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(29, Color.BLACK, Sector.VOISINS));
        this.rouletteNumbers.add(new RouletteNumber(30, Color.RED, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(31, Color.BLACK, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(32, Color.RED, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(33, Color.BLACK, Sector.TIER));
        this.rouletteNumbers.add(new RouletteNumber(34, Color.RED, Sector.ORPHELINS));
        this.rouletteNumbers.add(new RouletteNumber(35, Color.BLACK, Sector.ZERO));
        this.rouletteNumbers.add(new RouletteNumber(36, Color.RED, Sector.TIER));
    }
    
    public RouletteNumber get(final int index) {
        return this.rouletteNumbers.get(index);
    }
    
    public List<RouletteNumber> getList(){
        return Collections.unmodifiableList(this.rouletteNumbers);
    }
    
}
