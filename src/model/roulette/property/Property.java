package model.roulette.property;

public interface Property {
    
    public enum Parity{
        EVEN, ODD, NEUTRAL
    }
    
    public enum Included{
        _1_18_, _19_36_, NOT
    }
    
    public enum Row{
        FIRST, SECOND, THIRD, NOT
    }
    
    public enum Column{
        FIRST, SECOND, THIRD, NOT
    }
    
    public enum Sector {
        TIER, ORPHELINS, VOISINS, ZERO
    }
    
}
