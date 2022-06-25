package model.roulette.property;

/**
 * Define the properties of the roulette numbers.
 */
public interface Property {
    
    /**
     * The parity of a number.
     */
    public enum Parity{
        EVEN, ODD, NEUTRAL
    }
    
    /**
     * Inclusion of a number.
     */
    public enum Included{
        _1_18_, _19_36_, NOT
    }
    
    /**
     * Row of a number.
     */
    public enum Row{
        FIRST, SECOND, THIRD, NOT
    }
    
    /**
     * Column of a number.
     */
    public enum Column{
        FIRST, SECOND, THIRD, NOT
    }
    
    /**
     * Sector of a number.
     */
    public enum Sector {
        TIER, ORPHELINS, VOISINS, ZERO
    }
    
}
