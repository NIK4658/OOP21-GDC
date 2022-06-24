package model.roulette.property;

import java.util.ArrayList;
import java.util.List;

import model.roulette.property.Property.Sector;;

public class EuropeanSectors {//SISTEMARE
    
    private final List<Sector> list;
    
    public EuropeanSectors(){
        list = new ArrayList<>();
        list.add(Sector.ZERO);
        list.add(Sector.ORPHELINS);
        list.add(Sector.VOISINS);
        list.add(Sector.ZERO);
        list.add(Sector.VOISINS);
        list.add(Sector.TIER);
        list.add(Sector.ORPHELINS);
        list.add(Sector.VOISINS);
        list.add(Sector.TIER);
        list.add(Sector.ORPHELINS);
        list.add(Sector.TIER);
        list.add(Sector.TIER);
        list.add(Sector.ZERO);
        list.add(Sector.TIER);
        list.add(Sector.ORPHELINS);
        list.add(Sector.ZERO);
        list.add(Sector.TIER);
        list.add(Sector.ORPHELINS);
        list.add(Sector.VOISINS);
        list.add(Sector.VOISINS);
        list.add(Sector.ORPHELINS);
        list.add(Sector.VOISINS);
        list.add(Sector.VOISINS);
        list.add(Sector.TIER);
        list.add(Sector.TIER);
        list.add(Sector.VOISINS);
        list.add(Sector.ZERO);
        list.add(Sector.TIER);
        list.add(Sector.VOISINS);
        list.add(Sector.VOISINS);
        list.add(Sector.TIER);
        list.add(Sector.ORPHELINS);
        list.add(Sector.ZERO);
        list.add(Sector.TIER);
        list.add(Sector.ORPHELINS);
        list.add(Sector.ZERO);
        list.add(Sector.TIER);
    }
    
    public List<Sector> getList() {
        return this.list;
    }

}
