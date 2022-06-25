package model.roulette.property;

/**
 * Define the properties of the roulette numbers.
 */
public interface Property {
    
    /**
     * The parity of a number.
     */
    enum Parity {
        EVEN, ODD, NEUTRAL
    }
    
    /**
     * Inclusion of a number.
     */
    enum Included {
        _1_18_, _19_36_, NOT
    }
    
    /**
     * Row of a number.
     */
    enum Row {
        FIRST, SECOND, THIRD, NOT
    }
    
    /**
     * Column of a number.
     */
    enum Column {
        FIRST, SECOND, THIRD, NOT
    }
    
    /**
     * Sector of a number.
     */
    enum Sector {
        TIER, ORPHELINS, VOISINS, ZERO
    }
    
}
