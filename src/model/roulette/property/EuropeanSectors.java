package model.roulette.property;

import java.util.ArrayList;
import java.util.List;
import model.roulette.property.Property.Sector;;

/**
 * Collection that associates a sector to each property.
 */
public class EuropeanSectors {
    
    private final List<Sector> list;
    
    /**
     * Create the collection that associates a sector to each property.
     */
    public EuropeanSectors() {
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
    
    /**
     * Returns a list of sectors.
     * @return returns a list of sectors.
     */
    public List<Sector> getList() {
        return this.list;
    }

}
